import java.util.Scanner;

public class RunProgram {
	
	public static void main(String[] args) {
		try {
//		Request request;
		TempRunProgram temp = new TempRunProgram();	
/*		GetServer getServer = new GetServer();
		try {
			Scanner keyboard = new Scanner(System.in);
			while (true) {
			System.out.println("Enter 1 to enter Customer, 2 to get a customer: ");
			int value = keyboard.nextInt();
			keyboard.nextLine();
			if (value == 1) {
				System.out.println("Please Enter A Name to put in server: ");
				String name = keyboard.nextLine();
				getServer.sendInfo(new Packet((new CustomerInfo(name)),RequestEnum.Request.giveData));
			} else {
				System.out.println("Please Enter A Name to get from server: ");
				String name = keyboard.nextLine();
				getServer.getInfo(new Packet((new CustomerInfo(name)),RequestEnum.Request.getData));
			}
				
			} */
			InterFace ainterface = new InterFace();
		} catch (Exception e) {
			
		}

	}
}

class TempRunProgram extends Thread {
	TempRunProgram() {		
		this.start();
	}
	
	public void run() {
		Server server = new Server();		
	}
}

/*
class Customer {

	GetServer server;
	CustomerInfo info;
	BarberShopInfo barberShopInfo;
	Customer() {
		server = new GetServer();
	}
		
	public void giveBarberShopInfo() {
		server.sendInfo(new Packet((new BarberShopInfo("SuperCuts")),RequestEnum.Request.giveData)); // the false means not to send back
	}

	public Packet getBarberShopInfo() {
		return server.getInfo(new Packet((new BarberShopInfo("SuperCuts")),RequestEnum.Request.getData));
		
	}
}
/*

class BarberShop {

	GetServer server;
	CustomerInfo info;
	BarberShop() {
		server = new GetServer();
	}
		
	public void giveCustomerInfo(String name) {
		server.sendInfo(new Packet((new CustomerInfo(name)),RequestEnum.Request.giveData)); // the false means not to send back
	}

	public Packet getCustomerInfo(String name) {
		return server.getInfo(new Packet((new CustomerInfo(name)),RequestEnum.Request.getData));
		
	}

}
*/


