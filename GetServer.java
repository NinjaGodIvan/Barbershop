import java.net.*;
import java.io.*;

public class GetServer {
	InetAddress ip;
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
	
	public void sendInfo(Packet info) {
		try {
			Socket socket = this.connectToServer();
			
			// serializes some object and sends
			// getOutputStream returns an output stream from socket
			ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(info); // writes state of object
			toServer.close();
		} catch (Exception e) {
			System.out.println("Didnt send info");
		}
	}
		
	public Packet getInfo(Packet info) {
		Packet packet = null;
		try {
			this.sendInfo(info);
			// serializes some object and sends
			// getOutputStream returns an output stream from socket
			Socket socket = new Socket(ip.getHostName(),4001);
			ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
			packet = (Packet)fromServer.readObject(); // writes state of object
			if (packet.getCustomer() != null) {
				System.out.println(packet.getCustomer());
			}
			fromServer.close();
		} catch (Exception e) {
			System.out.println("Didnt send info2");
		}		
		return packet;
	}
	
}

