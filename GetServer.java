import java.net.*;
import java.io.*;

public class GetServer {
	InetAddress ip;
	InetAddress ip2;
 //   ObjectOutputStream toServer; // serializes them
 //   ObjectInputStream fromServer; // deserializes data and objects
	
	
	/* socket: endpoint of communcication between two machines
	 * 		Needs IP and Port
	 * ObjectOutPutStream: serializes objects
	 * getOutputStream() returns output stream
	 * ObjectInputStream: deserializes objects
	 * getInputStream() returns input stream 
	 */
	GetServer() {
	}
// CUSTOMER INTERFACE CLASSES YOU CAN USE	
	/*
		if changes made to customeraccount must pass it back to server by say
	*/
	public boolean giveCustomerInfo(CustomerInfo info) {
		return this.sendInfo(new Packet(info,RequestEnum.Request.giveData));
	}
	
	/*
		@return will return customerinfo class if found and null if no account
	*/	
	
	public CustomerInfo getCustomerInfo(String userName) {
		Packet packet = new Packet((new CustomerInfo(userName)),RequestEnum.Request.getData);
		packet = this.getInfo(packet);
		return packet.getCustomer();
	}
	
	/*
		@return will return customerinfo class if found and null if no account
		(COMPARES PASSWORD)
	*/
	
	public CustomerInfo getCustomerInfo(String userName, String password) {
		return this.findCustomerInfo(userName,password);
	}	
	
	public CustomerInfo getCustomerInfo(CustomerInfo info) {
		return this.findCustomerInfo(info.getUserName(),info.getPassword());
	}	
	
	/*
		@return returns true if created account false if account already exists
	*/
	public boolean createCustomerInfoAccount(CustomerInfo info) {
		if (this.findCustomerInfo(info.getUserName(),info.getPassword()) == null) {
			this.sendInfo(new Packet(info,RequestEnum.Request.giveData));
			return true;
		} else {
			System.out.println("ACCOUNT EXISTS");
		}		
		return false;
	}
	
		
	
// BARBERSHOP INTERFACE CLASSES
	public void giveBarberShopInfo(BarberShopInfo info) {
		this.sendInfo(new Packet(info,RequestEnum.Request.giveData));
	}
	
	public BarberShopInfo getBarberShopInfo(String userName) {
		Packet packet = new Packet((new BarberShopInfo(userName)),RequestEnum.Request.getData);
		packet = this.getInfo(packet);
		return packet.getBarberShop();
	}	
	
	/*
		@return returns account if it found it based on username or password
		Will Return null if it doesnt find account
	*/
	public BarberShopInfo getBarberShopInfo(String userName, String password) {
		return this.findBarberShopInfo(userName,password);
	}	
	
	public BarberShopInfo getBarberShopInfo(BarberShopInfo info) {
		return this.findBarberShopInfo(info.getUserName(),info.getPassword());
	}
	
	
	/*
		@return returns true if created account false if account already exists
	*/	
	public boolean createBarberShopAccount(BarberShopInfo info) {
		if (this.findBarberShopInfo(info.getUserName(),info.getPassword()) == null) {
			return this.sendInfo(new Packet(info,RequestEnum.Request.giveData));
		} else {
			System.out.println("ACCOUNT EXISTS");
		}		
		return false;
	}	
	
	

// DONT WORRY ABOUT THESE CLASSES

	private CustomerInfo findCustomerInfo(String userName, String password) {
		Packet packet = new Packet((new CustomerInfo(userName,password)),RequestEnum.Request.findData);
		return this.getInfo(packet).getCustomer();
	}	
	
	private BarberShopInfo findBarberShopInfo(String userName, String password) {
		Packet packet = new Packet((new BarberShopInfo(userName,password)),RequestEnum.Request.findData);
		return this.getInfo(packet).getBarberShop();
	}
	
	/* returns true of connected returns false otherwise
	 * 
	 */
	private Socket connectToServer() {
		Socket socket;
		try {
			this.ip = InetAddress.getLocalHost();
			socket = new Socket(ip.getHostName(),4000);
			return socket;		
		} catch(Exception e) {
			System.out.println("Couldn't Connect");
		}		
		return null;
	}
	
	private boolean sendInfo(Packet info) {
		try {
			Socket socket = this.connectToServer();
			
			// serializes some object and sends
			// getOutputStream returns an output stream from socket
			ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(info); // writes state of object
			toServer.close();
			return true;
		} catch (Exception e) {
			System.out.println("Didnt send info");
			return false;
		}
	}
		
	private Packet getInfo(Packet info) {
		Packet packet = null;
		try {
			this.sendInfo(info);
			// serializes some object and sends
			// getOutputStream returns an output stream from socket
			this.ip2 = InetAddress.getLocalHost();
			Socket socket = new Socket(ip2.getHostName(),4001);
			ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
			packet = (Packet)fromServer.readObject(); // writes state of object
			fromServer.close();
		} catch (Exception e) {
			System.out.println("Didnt send info2");
		}		
		return packet;
	}
	
}
