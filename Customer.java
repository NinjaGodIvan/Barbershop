//Package
import java.util.Scanner;

public class Customer extends Thread {
	
	//Customer's appointment date, their information, and barber shop
	public Appointment appt;
	private String barberShop;
	public CustomerInfo custInfo;
	private GetServer server;
	
	public Customer(CustomerInfo info) {
		this.custInfo = info;
		new AppointmentUI(this); //Opens the Appointment UI when Customer is called
	}
	
	public Appointment getAppointment() {
		return appt;
	}
		
	public void updateCustomer(CustomerInfo custInfo){ 
		this.custInfo = custInfo;
	}
		
	/** Function that asks user for the name of the barbershop and sets it */
	public void setBarberShop() {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name of your barber shop:");
		in.nextLine();
		String barberShop = in.nextLine();
		in.close();
		this.barberShop = barberShop;
		
	}
	
	/** Function that changes the customer's phone number */
	public void changePhone(long phoneNum) {
		custInfo.setCustomerPhone(phoneNum);
		server.giveCustomerInfo(this.custInfo);
	}
	
	/** Function that changes the customer's phone number */
	public void changeEmail(String email) {
		custInfo.setCustomerEmail(email);
		server.giveCustomerInfo(this.custInfo);
	}
	
	/** Function that changes the customer's username */
	public void changeUserName(String username) {
		custInfo.setUserName(username);
		server.giveCustomerInfo(this.custInfo);
	}
	
	/** Function that gets the account from the server */
	public void getAccount() {
		//Code this
	}
	
	/** Function that changes the customer's barber shop name */
	public void changeBarber(String barberShop) {
		this.barberShop = barberShop;
	}

	/** Function that gets the name of the customer's barbershop name */
	public String getBarber() {
		return this.barberShop;
	}
	
	/** Function that returns the customer's info */
	public CustomerInfo getCustomerInfo() {
		return this.custInfo;
	}
}
