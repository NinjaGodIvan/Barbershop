
import java.util.Calendar;
import java.util.Scanner;
import java.io.Serializable;

public class Customer extends Thread {
	
	//Customer's appointment date, their information, and barber shop
	public Appointment appt;
	private String barberShop;
	private CustomerInfo custInfo;
	private GetServer server;
	
	public Customer(CustomerInfo info) {
		this.custInfo = info;
	}
	
	/*
	public Customer(String userName, String name, String email, String phoneNum)
		/* All this information except the last line should be only stored in the CustomerInfo class 
		this.userName = userName; 
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNum;
		custInfo = server.getCustomerInfo(userName);
	}
*/
	
	public void run() {
		while (true) {
			try {
				CustomerInfo temp = server.getCustomerInfo(custInfo.getUserName());
				if (temp != null)
					this.custInfo = temp; 
				sleep(15000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
	}

	
	public void makeAppointment() {
		
	}
		
//	public Appointment findAppointment() {
//	}
		
	public void updateCustomer()  { // updates customerInfo object by asking for new one from server
		
	}
	
	public void sendCustomer() {// updates server customerInfo
		
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
}
 
