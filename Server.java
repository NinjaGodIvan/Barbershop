import java.net.*;
import java.io.*;
import java.util.concurrent.Semaphore;
import java.util.EmptyStackException;
import java.lang.reflect.Array;
import java.io.Serializable;


/* I use different classes so that i can support multithreading for different
 * jobs, Server gets connections from GetServer class, start server and launch all
 *  other classes that have to do with server. Including launching and 
 *  creating layer 1 objects.
 * 
 * 
 */
public class Server extends Thread {
	CleanBuffer cleanBuffer;
	ServerSocket server;
	ServerSocket exportPort; // used for sending messages back to client		
//	CircularBuffer<AddData> addDataBuffer;
	AddData addData;
	BarberShopTree globalBarberShopTree;
	CircularBuffer<ReturnData> sendDataBuffer;
	CircularBuffer<GetData> getDataBuffer;
	Hash hash;
	
	/*  initializes all data variables. calls StartServer
	 */
	Server() {	
		try {
			hash = new Hash(5);
			globalBarberShopTree = new BarberShopTree();
//			addDataBuffer = new CircularBuffer<>(AddData[].class,5); // add data to server
			sendDataBuffer = new CircularBuffer<>(ReturnData[].class,100);
			getDataBuffer = new CircularBuffer<>(GetData[].class,100);
			cleanBuffer = new CleanBuffer();
			startServer();
			int i = 0;
			while (true) {
				getDataBuffer.saveThread(new GetData(server.accept()));	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/* initializes tree and barberTree along with initializing ServerSockets
	 * @param none
	 * @return none
	 */
	private void startServer() {
		try {
			this.server = new ServerSocket(4000); // 0 means finds some local port	
			this.exportPort = new ServerSocket(4001); // USED to relay client objects
		} catch (Exception e) {
			System.out.println("Failed To Connect");
		}
	}
	


/* manages the getData buffer and sendData buffer
 * Will be used to remove Threads that have completed execution in the 
 * GetData, AddData CircularBuffer in order to make room for new Threads.
 */
class CleanBuffer extends Thread {
	private Semaphore semaphore; /* will be responsible for pausing 
	execution of CleanBuffer if the CircularBuffers are empty. */
	
	/* instantiates semaphore, starts itself
	 * @param NONE
	 * @return NONE
	 */
	CleanBuffer() {
		this.semaphore = new Semaphore(0);
		this.start();
	}
	
	/* will loop over and over again until the two CircularBuffers are 
	 * empty (ie all jobs have completed execution). Will remove a job from
	 * the correct CircularBuffer if it is done.
	 * @param NONE
	 * @return NONE
	 */
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
	
	/* will release the semaphore lock, called by GetData and ReturnData
	 * when an instance of them gets created
	 * @param NONE
	 * @return NONE
	 */
	public void doWork() {
		semaphore.release();
	}	
}

/* Will get all the requests sent to server and choose whether to send data
 * back or to give data to an AddData object by putting it in the AddData hash
 */
class GetData extends Thread {
	boolean done; // if execution is completed for thread will be marked true
	Socket socket;
	
	/* @param NONE
	 * @return true if object completed run method, false if not
	 */
	public boolean isDone() {
		return done;
	}
	
	/* Will instantiate data types. Will start the run method via Thread 
	 * interface start()
	 * @param NONE
	 * @return NONE
	 */
	GetData(Socket socket) { // not proud of this
		this.socket = socket;
		this.start();
	}
	
	/* will call method getData, will start execution upon completion of object creation
	 * will release CleanBuffers semaphore to start it if it isnt already running
	 * @param NONE
	 * @return NONE
	 */
	public void run() {
		this.getData(socket);
		this.done = true;
		cleanBuffer.doWork();
	}
	
	/* based on the kind of data it receives it will either give it to hash
	 * so it can be stored in a data structure or it will create a new 
	 * ReturnData object so that it can be returned to user
	 * @param socket, will be used to get the packet data
	 * @return NONE
	 */
	private void getData(Socket socket) {
		try {
			// getInputStream returns an input stream
			
			ObjectInputStream fromConnection = new ObjectInputStream(socket.getInputStream());				
			Packet packet = (Packet)fromConnection.readObject();
			if (!packet.sendBack()) {			
				hash.putInHash(packet);
				hash.startThread(packet);	
//				addPacket.saveThread(packet);	
//				addData.startThread();
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
	boolean done;// if job method is done it will be marked true
	Packet packet;
	
	/* @param NONE
	 * @return will return true if run method is done else false
	 */
	public boolean isDone() {
		return done;
	}
	
	/* recieves a data packet which will have the data for the object 
	 * that has to be found in data structure
	 * @param packet that stores data on what needs to be returned
	 * @return NONE
	 */
	ReturnData(Packet packet) {
		this.packet = packet;
		this.start();
	}
	
	/* will be responsible for calling method sendBackData. Runs when 
	 * ReturnData object is instantiated
	 * @param NONE
	 * @return NONE
	 */
	public void run() {
		this.sendBackData(packet);
		this.done = true;
		cleanBuffer.doWork();
	}
	
	/*  will get a Packet as input and will send the correct data back to 
	 * user based on what is inside of the packet
	 * @param packet which stores what data to send back
	 * @return NONE
	*/
	public void sendBackData(Packet packet) {
		try {
			 
			Socket s = exportPort.accept();
			ObjectOutputStream backToUser = new ObjectOutputStream(s.getOutputStream());
			if (packet.getCustomer() != null) {
				if (packet.request == RequestEnum.Request.getData) {
					packet.infoC = hash.getInfoC(packet.getCustomer());
					packet.infoC.giveList(globalBarberShopTree.getBarberShopList()); // Give list of barbers for customer, Not very efficient but for our purposes works
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					boolean inTree = hash.inHash(packet.infoC);					
					if (inTree) {
						packet.infoC = hash.getInfoC(packet.getCustomer());
						packet.infoC.giveList(globalBarberShopTree.getBarberShopList());						
					} else {
						packet.infoC = null;
					}
				}
			} else  if (packet.getBarberShop() != null) {
				if (packet.request == RequestEnum.Request.getData) {
					packet.infoB = hash.getInfoB(packet.getBarberShop());
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					boolean inTree = hash.inHash(packet.infoB);
					if (inTree) {
						packet.infoB = hash.getInfoB(packet.getBarberShop());
					} else {
						packet.infoB = null; // if password or userName does not match
					}
				}				
			}			
			backToUser.writeObject(packet); // writes state of object
			backToUser.close();
		} catch (Exception e) {
			System.out.println("Didnt send info1");
		}
	}
}

/* Will add data to BarberShopTree or tree depending on packet data it reads
 * Packet data stored in a circularBuffer
 * since adding two things to tree at once causes problems
 */
class AddData extends Thread {
	Semaphore semaphore;
	Tree tree;
	BarberShopTree barberShopTree;
	SaveAndLoad saveNLoad;
	
	
	CircularBuffer<Packet> addPacket;	
	
	/* initializes structures tree and barberShopTree by loading them from
	 * file if they have been stored in a file
	 * will create a circularBuffer for storing packets it recieves
	 * @param NONE
	 * @return NONE
	 */
	AddData() {
		this.saveNLoad = new SaveAndLoad("storage");
		this.semaphore = new Semaphore(0);
		this.tree = saveNLoad.LoadTreeData();
		if (tree == null) 
			this.tree = new Tree();
		this.barberShopTree = saveNLoad.LoadBarberShopTreeData();
		if (barberShopTree == null) 
			this.barberShopTree = new BarberShopTree();
		this.addPacket = new CircularBuffer<>(Packet[].class,200);		
	}
	
	/* will return tree
	 * @param NONE
	 * @return a tree
	 */
	public Tree getTree() {
		return tree;
	}
	
	/* checks to see if CustomerInfo object is in tree
	 * @param a customerInfo object
	 * @return true if in tree false if not
	 */
	public boolean inTree(CustomerInfo info) {
		return tree.inTree(info);
	}

	/* checks to see if BarberShopInfo object is in tree
	 * @param a BarberShopInfo object
	 * @return true if in tree false if not
	 */	
	public boolean inBarberShopTree(BarberShopInfo info) {
		return barberShopTree.inTree(info);
	}
	
	/* will return barberShopTree
	 * @param NONE
	 * @return a barberShopTree
	 */
	public BarberShopTree getBarberShopTree() {
		return barberShopTree;
	}
	
	/* puts a packet in the circularBuffer
	 * @param packet that will be stored
	 * @return NONE
	 */
	public void givePacket(Packet packet) {
		addPacket.saveThread(packet);
	}
	
	public void startThread() {
		this.semaphore.release();
	}
		
	/* will go through CircularBuffer of packets and store them one by one
	 * by calling getData method and passing it the packet
	 * @param NONE
	 * @return NONE
	 */	
	public void run() {
		try {
			while (true) {
				semaphore.acquire();
				while (!addPacket.isEmpty()) {
					getData(addPacket.consumeThread());
				}
				semaphore.drainPermits();
			}
		} catch (Exception e) {
			
		}
	}
	//																									ADDING NODES TO BARBER TREE
	/* Will take a packet and put the data inside it in the correct tree structure
	 * or remove it from correct tree structure
	 * @param excepts a packet from circularBuffer
	 * @return NONE
	 */
	private void getData(Packet packet) {
		try {
			if (packet.getCustomer() != null) {
				if (packet.request == RequestEnum.Request.giveData) {
					packet.infoC.giveList(barberShopTree.getBarberShopList());					
					tree.addNode(packet.getCustomer());
					saveNLoad.SaveData(tree); // saves to file
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					tree.removeNode(packet.getCustomer());
				}
			} else  if (packet.getBarberShop() != null) {
				if (packet.request == RequestEnum.Request.giveData) {
					barberShopTree.addNode(packet.getBarberShop());
					globalBarberShopTree.addNode(packet.getBarberShop());
					saveNLoad.SaveData(barberShopTree);		// saves to file			
				} else { // IF USE FIND DATA, only used WHEN entering an account because it checks if userName and password are the same
					barberShopTree.removeNode(packet.getBarberShop());
					globalBarberShopTree.removeNode(packet.getBarberShop());					
				}
			}						
		} catch (Exception e) {
			System.out.println("DIDNT GET DATA");
		}
	}			
}

/* A hash of threads is used for the purpose of making inputing data faster for lots of users
 * that this application will never have. Each thread is responsible for adding data to one bucket
 * For this server there will be 5 buckets so 5 threads putting in data into each Tree
 */

class Hash {
	
	public AddData[] Buckets;
	private int numBuckets;

	
	Hash(int numBuckets) {
		this.Buckets = new AddData[numBuckets];
		this.numBuckets = numBuckets;
		this.initializeBuckets();
		
	}


	private int getIndex(String userName) {
		int index = 0;
		for (int i = 0; i < userName.length(); i++) {
			index = index + userName.charAt(i);
		}
		
		do {
			index = index % numBuckets;
		} while (index > (numBuckets - 1));
		
		return index;
	}
	
	/* Starts thread based on info in packet
	 * 
	 */
	public void startThread(Packet packet) {
		if (packet.infoC != null) {
			int index = getIndex(packet.infoC.getUserName());
			Buckets[index].startThread();
		} else if (packet.infoB != null) {
			int index = getIndex(packet.infoB.getUserName());
			Buckets[index].startThread();			
		}
		
	}
	
	
	/* gives to the correct instance of addData the packet based on userName
	 *  of account in packet
	 */
	public void putInHash(Packet packet) {
		int index = -1;
		if (packet.infoC != null) {
			index = getIndex(packet.infoC.getUserName());
		} else if (packet.infoB != null) {
			index = getIndex(packet.infoB.getUserName());
		}
		if (index != -1) {
			Buckets[index].givePacket(packet);
		}
	}
	
	
	public BarberShopInfo getInfoB(BarberShopInfo info) {
		int index = getIndex(info.getUserName());
		return Buckets[index].getBarberShopTree().returnInfo(info);		
	}
	
	public CustomerInfo getInfoC(CustomerInfo info) {
		int index = getIndex(info.getUserName());
		return Buckets[index].getTree().returnInfo(info);
	}
	
	
	/* checks if in hask
	 * @return true if found, false if not
	 */
	public boolean inHash(CustomerInfo infoC) {
		if (infoC != null) {
			int index = getIndex(infoC.getUserName());	
			return Buckets[index].inTree(infoC);
		} else {
			return false;
		}
	}
	
	public boolean inHash(BarberShopInfo info) {
		if (info != null) {
			int index = getIndex(info.getUserName());
			return Buckets[index].inBarberShopTree(info);
		} else {
			return false;
		}
	}	
	
	private void initializeBuckets() {
		for (int i = 0; i < numBuckets; i++) {
			Buckets[i] = new AddData();
			Buckets[i].start();
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
