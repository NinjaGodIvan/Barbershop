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
//	CircularBuffer<AddData> addDataBuffer;
	AddData addData;
	CircularBuffer<ReturnData> sendDataBuffer;
	CircularBuffer<GetData> getDataBuffer;
	CircularBuffer<Packet> addPacket;
	
	Server() {	
		try {
//			addDataBuffer = new CircularBuffer<>(AddData[].class,5); // add data to server
			addData = new AddData();
			addData.start();
			sendDataBuffer = new CircularBuffer<>(ReturnData[].class,100);
			getDataBuffer = new CircularBuffer<>(GetData[].class,100);
			cleanBuffer = new CleanBuffer();
			addPacket = new CircularBuffer<>(Packet[].class,200);
			startServer();
			int i = 0;
			while (true) {
				getDataBuffer.saveThread(new GetData(server.accept()));	
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
	


/* manages the getData buffer and sendData buffer
 * 
 */
class CleanBuffer extends Thread {
	private Semaphore semaphore;
	
	CleanBuffer() {
		this.semaphore = new Semaphore(0);
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
	Socket socket;
	
	public boolean isDone() {
		return done;
	}
	
	GetData(Socket socket) { // not proud of this
		this.socket = socket;
		this.start();
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
				addPacket.saveThread(packet);	
				addData.startThread();
				fromConnection.close();				
			} else {
				sendDataBuffer.saveThread(new ReturnData(packet));
				fromConnection.close();
			}
			fromConnection.close();
		} catch (Exception e) {
			System.out.println("DIDNT GET DATA");
		}
	}	
}


/* Sends back data to GetServer class
 * 
 */
class ReturnData extends Thread {
	boolean done;
	Packet packet;
	
	public boolean isDone() {
		return done;
	}
	
	ReturnData(Packet packet) {
		this.packet = packet;
		this.start();
	}
	
	public void run() {
		this.sendBackData(packet);
		this.done = true;
		cleanBuffer.doWork();
	}
	
	public void sendBackData(Packet packet) {
		try {
//			System.out.println("test");																// COMMENT
			 
			Socket s = exportPort.accept();
			ObjectOutputStream backToUser = new ObjectOutputStream(s.getOutputStream());
			if (packet.getCustomer() != null) {
				if (packet.request == RequestEnum.Request.getData) {
					packet.infoC = tree.returnInfo(packet.getCustomer());
					packet.infoC.giveList(barberShopTree.getBarberShopList()); // Give list of barbers for customer, Not very efficient but for our purposes works
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					boolean inTree = tree.inTree(packet.getCustomer());
					if (inTree) {
						packet.infoC = tree.returnInfo(packet.getCustomer());
					} else {
						packet.infoC = null;
					}
				}
			} else  if (packet.getBarberShop() != null) {
				if (packet.request == RequestEnum.Request.getData) {
					packet.infoB = barberShopTree.returnInfo(packet.getBarberShop());
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					boolean inTree = barberShopTree.inTree(packet.getBarberShop());
					if (inTree) {
						packet.infoB = barberShopTree.returnInfo(packet.getBarberShop());
					} else {
						packet.infoB = null; // if password or userName does not match
					}
				}				
			}			
			backToUser.writeObject(packet); // writes state of object
			backToUser.close();
		} catch (Exception e) {
			System.out.println("Didnt send info");
		}
	}
}

/* adding data has to be synchronized
 * since adding two things to tree at once causes problems
 */
class AddData extends Thread {
	Semaphore semaphore;
	
	AddData() {
		this.semaphore = new Semaphore(0);
	}
	
	public void startThread() {
		this.semaphore.release();
	}
		
		
	public void run() {
		try {
			while (true) {
				semaphore.acquire();
				while (!addPacket.isEmpty()) {
					getData(addPacket.consumeThread());
	//				System.out.println("ThreadID " + threadID);
				}
				semaphore.drainPermits();
			}
		} catch (Exception e) {
			
		}
	}
	// 																													ADDING NODES TO BARBER TREE
	private void getData(Packet packet) {
		try {
			if (packet.getCustomer() != null) {
				if (packet.request == RequestEnum.Request.giveData) {
					packet.infoC.giveList(barberShopTree.getBarberShopList());					
					tree.addNode(packet.getCustomer());
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					tree.removeNode(packet.getCustomer());
				}
			} else  if (packet.getBarberShop() != null) {
				if (packet.request == RequestEnum.Request.giveData) {
					barberShopTree.addNode(packet.getBarberShop());
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					barberShopTree.removeNode(packet.getBarberShop());
				}
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
		this.d = 0; // index used to loop through array without removing anything
		this.e = 0; // used to iterate through buffer
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
}
