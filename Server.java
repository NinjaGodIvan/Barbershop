import java.net.*;
import java.io.*;
import java.util.concurrent.Semaphore;
import java.util.EmptyStackException;
import java.lang.reflect.Array;
import java.io.Serializable;

/* I use different classes so that i can support multithreading for different
 * jobs
 * 
 * 
 */
public class Server extends Thread {
	CleanBuffer cleanBuffer;
	ServerSocket server;
	ServerSocket exportPort; // used for sending messages back to client
	Tree tree;
	BarberShopTree barberShopTree;		
	CircularBuffer<AddData> addDataBuffer;
	CircularBuffer<ReturnData> sendDataBuffer;
	CircularBuffer<GetData> getDataBuffer;
	SynchronizeData synchronizeData;
	
	Server() {	
		try {
			addDataBuffer = new CircularBuffer<>(AddData[].class,100);
			sendDataBuffer = new CircularBuffer<>(ReturnData[].class,100);
			getDataBuffer = new CircularBuffer<>(GetData[].class,100);
			cleanBuffer = new CleanBuffer(getDataBuffer,sendDataBuffer);
			synchronizeData = new SynchronizeData(addDataBuffer);
			startServer();
			synchronizeData.start();
			int i = 0;
			while (true) {
				getDataBuffer.saveThread(new GetData(cleanBuffer,tree, barberShopTree, server.accept(),synchronizeData, addDataBuffer,sendDataBuffer,exportPort));	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void startServer() {
		try {
			tree = new Tree();
			barberShopTree = new BarberShopTree();
			this.server = new ServerSocket(4000); // 0 means finds some local port	
			this.exportPort = new ServerSocket(4001); // USED to relay client objects
		} catch (Exception e) {
			System.out.println("Failed To Connect");
		}
	}
	
}

/* manages the getData buffer and sendData buffer
 * 
 */
class CleanBuffer extends Thread {
	private CircularBuffer<GetData> getDataBuffer;
	private CircularBuffer<ReturnData> sendDataBuffer;	
	private Semaphore semaphore;
	
	CleanBuffer(CircularBuffer<GetData> getDataBuffer, CircularBuffer<ReturnData> sendDataBuffer) {
		this.semaphore = new Semaphore(0);
		this.getDataBuffer = getDataBuffer;
		this.sendDataBuffer = sendDataBuffer;
		this.start();
	}
	
	public void run() {
		while (true) {
			try {
				semaphore.acquire();
				
				if (!getDataBuffer.isEmpty())
					if (getDataBuffer.getThread().isDone() == true)
						getDataBuffer.consumeThread();
				if (!sendDataBuffer.isEmpty())
					if (sendDataBuffer.getThread().isDone() == true)
						sendDataBuffer.consumeThread();
						
				semaphore.drainPermits();	
			} catch (Exception e) {
				
			}
		}
	}
	
	public void doWork() {
		semaphore.release();
	}	
}


class GetData extends Thread {
	boolean done;
	CleanBuffer cleanBuffer;
	CircularBuffer<AddData>  addDataBuffer;
	CircularBuffer<ReturnData> sendDataBuffer;
	SynchronizeData synchronizeData;
	Socket socket;
	Tree tree;
	BarberShopTree barberShopTree;
	ServerSocket server;
	
	public boolean isDone() {
		return done;
	}
	
	GetData(CleanBuffer cleanBuffer, Tree tree,BarberShopTree barberShopTree, Socket socket, SynchronizeData synchronizeData, CircularBuffer<AddData> addDataBuffer, CircularBuffer<ReturnData> sendDataBuffer,ServerSocket server) { // not proud of this
		this.cleanBuffer = cleanBuffer;
		this.barberShopTree = barberShopTree;
		this.tree = tree;
		this.socket = socket;
		this.addDataBuffer = addDataBuffer;
		this.sendDataBuffer = sendDataBuffer;
		this.synchronizeData = synchronizeData;
		this.done = false;
		this.start();
		this.server = server;
	}
	
	public void run() {
		this.getData(socket);
		this.done = true;
		cleanBuffer.doWork();
	}
	
	private void getData(Socket socket) {
		try {
			// getInputStream returns an input stream
			ObjectInputStream fromConnection = new ObjectInputStream(socket.getInputStream());
			Packet packet = (Packet)fromConnection.readObject();
			if (!packet.sendBack()) {
				addDataBuffer.saveThread(new AddData(tree,barberShopTree,packet,synchronizeData));
				fromConnection.close();				
			} else {
				sendDataBuffer.saveThread(new ReturnData(cleanBuffer, tree,barberShopTree, packet,server));
				fromConnection.close();
			}
			fromConnection.close();
		} catch (Exception e) {
			System.out.println("DIDNT GET DATA");
		}
	}	
}


class ReturnData extends Thread {
	CleanBuffer cleanBuffer;
	BarberShopTree barberShopTree;
	Tree tree;
	Packet packet;
	Socket socket;
	boolean done;
	ServerSocket server;
	
	public boolean isDone() {
		return done;
	}
	
	ReturnData(CleanBuffer cleanBuffer, Tree tree,BarberShopTree barberShopTree, Packet packet, ServerSocket server) {
		this.barberShopTree = barberShopTree;
		this.cleanBuffer = cleanBuffer;
		this.tree = tree;
		this.packet = packet;
		this.done = false;
		this.start();
		this.server = server;
	}
	
	public void run() {
		this.sendBackData(packet);
		this.done = true;
		cleanBuffer.doWork();
	}
	
	public void sendBackData(Packet packet) {
		try { 
			Socket s = server.accept();
			ObjectOutputStream backToUser = new ObjectOutputStream(s.getOutputStream());
			if (packet.getCustomer() != null) {
				packet.infoC = tree.returnInfo(packet.getCustomer());
			} else {
				packet.infoB = barberShopTree.returnInfo(packet.getBarberShop());
			}			
			backToUser.writeObject(packet); // writes state of object
			backToUser.close();
		} catch (Exception e) {
			System.out.println("Didnt send info");
		}
	}
}


class SynchronizeData extends Thread {
	Semaphore semaphore;
	Semaphore waitSem;
	CircularBuffer<AddData> addDataBuffer;
	
	SynchronizeData(CircularBuffer<AddData> addDataBuffer) {
		this.addDataBuffer = addDataBuffer;
		this.semaphore = new Semaphore(0);
		this.waitSem = new Semaphore(0);
	}
	
	public void runThread() {
		waitSem.release();
	}
	
	public void doneWithWork() { // used by synchronized class
		semaphore.release();
	}
	
	public void stopThread() {
		waitSem.drainPermits();
	}
	
	public void run() {
		try {
			while (true) {
				waitSem.acquire();
				
				AddData addData = addDataBuffer.consumeThread();
				
				addData.start(); // start thread
				addData.startThread(); // release semaphore
				semaphore.acquire(); // wait for addData thread to finsih
				semaphore.drainPermits(); // reset
				if (addDataBuffer.isEmpty())
					this.stopThread();
			}
		} catch (Exception e) {
			System.out.println("something bad happened");
		}
	}
	
}

/* adding data has to be synchronized
 * since adding two things to tree at once causes problems
 */
class AddData extends Thread {
	Packet packet;
	SynchronizeData synchronizeData;
	Semaphore semaphore;
	BarberShopTree barberShopTree;
	Tree tree;
	static int customers = 0;
	int threadID;
	static int hold = 0;
	
	AddData(Tree tree, BarberShopTree barberShopTree, Packet packet,SynchronizeData synchronizeData) {
		this.barberShopTree = barberShopTree;
		this.tree = tree;
		this.packet = packet;
		semaphore = new Semaphore(0);
		this.synchronizeData = synchronizeData;
		this.synchronizeData.runThread(); // run if off
		this.threadID = this.hold;
		this.hold++;
//		this.start();
	}
	
	public void startThread() {
		semaphore.release();
	}	
		
	public void run() {
		try {
			semaphore.acquire();
			
			getData(packet);
			System.out.println("ThreadID " + threadID);
			synchronizeData.doneWithWork();
			
			semaphore.drainPermits();
		} catch (Exception e) {
			
		}
	}
	// 																													ADDING NODES TO BARBER TREE
	private void getData(Packet packet) {
		try {
			// getInputStream returns an input stream
			if (packet.getCustomer() != null) {
				tree.addNode(packet.getCustomer());
			} else {
				barberShopTree.addNode(packet.getBarberShop());
			}
			customers++;
			if ((customers % 5) == 0) {
				tree.printInOrder();
			}
		} catch (Exception e) {
			System.out.println("DIDNT GET DATA");
		}
	}			
}


/* this is the critical section/region used by the two threads
 * it is a circular buffer where producer and consumer both move down
 * the array going to the right or higher memory location until
 * they reach max size where they start over at location array[0]
 */
class CircularBuffer<T> {
	int p; // location of next spot for producer to place character
	int c; // location of the spot of next char to be consumed
	int d;
	int e;
	int size; // size is the size of the buffer
	boolean done; // tells if producer has went through entire file
	T[] circularBuffer;
	
	CircularBuffer(Class<T[]> test, int m) {
		this.done = false;
		this.p = 0; // end
		this.c = 0; // start
		this.d = 0;
		this.e = 0;
		this.size = m;		
		circularBuffer = test.cast(Array.newInstance(test.getComponentType(), m));
	}
		
	// adds elements to array
	public boolean saveThread(T val) {
		if (isFull()) {
			return false; // if full cant put anymore
		} else {
			this.circularBuffer[this.p] = val; // still have room for 1
			this.p = (this.p + 1) % size;
			return true;
		}
	}
		
	/* removes elements from array and returns that element
	 * increments c location accordingly,
	 * throws exception when empty to prevent any false values from
	 * being returned
	 */
	public T consumeThread() throws EmptyStackException {
		T val;
		if (isEmpty()) {
			throw new EmptyStackException(); // throws exception if empty
		} else {
			val = this.circularBuffer[this.c]; // still have room for 1
			this.c = (this.c + 1) % size;
			return val;
		}
			
	}
		
	public T getThread() {
			T val;
			if (isEmpty()) {
				throw new EmptyStackException(); // throws exception if empty
			} else {
				this.e++;
				this.d = (this.c + this.e) % Math.abs(this.c - this.p);
//				System.out.println("THE d: " + this.d);
				val = this.circularBuffer[this.d]; // still have room for 1
				return val;
			}
		}
	// tells if buffer is full
	public boolean isFull() {
		/* is plus 1 to avoid overlap of placement
		 */
		if (((this.p + 1) % this.size) == this.c) {
			return true;
		}
		return false;
	}
	/* tells if buffer is empty, not plus one since its empty when
	 * consumer reaches producer place location, its at that point 
	 * when consumer cant consume any values because producer hasnt
	 * placed any yet
	 */
	public boolean isEmpty() {
		if (this.c == this.p) {
			return true;
		}
		return false;
	}
		
	public boolean notDone() {
		return !(this.done);
	}
		
	/* The producer can use setDone function to tell consumer later
	 * on, or the buffer that it has consumed all characters inside
	 * the file
	 */
	public void setDone(boolean d) {
		this.done = d;
	}
}

class Packet  implements Serializable {
	CustomerInfo infoC;
	BarberShopInfo infoB;
	boolean back; // value that states if it is or isnt going to send data back
	
	Packet(CustomerInfo info, boolean back) {
		this.infoC = info;
		this.infoB = null;
		this.back = back;
	}
	
	Packet(BarberShopInfo info,boolean back) {
		this.infoB = info;
		this.infoC = null;
		this.back = back;
	}
	
	public CustomerInfo getCustomer() {
		return this.infoC;
	}
	
	public BarberShopInfo getBarberShop() {
		return this.infoB;
	}
	
	public boolean sendBack() {
		return back;
	}
}
