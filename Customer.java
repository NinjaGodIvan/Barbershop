/* Implementation is incomplete. Still need to put more */

package barber;
import java.util.Calendar;
import java.util.Scanner;
import java.io.Serializable;

public class Customer extends Thread {
	
	//Customer's appointment date, their information, and barber shop
	private Appointment appt;
	private String barberShop;
	private CustomerInfo custInfo;
	private GetServer server;
	
	public Customer(String userName, String name, String email, String phoneNum)//-- {
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNum;
		custInfo = server.getCustomerInfo(); //--
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
	public void changePhone(String phoneNum) {
		custInfo.setCustomerPhone(phoneNum);
		server.giveCustomerInfo(this.custInfo);
	}
	
	/** Function that changes the customer's phone number */
	public void changeEmail(String email) {
		custInfo.setCustomerEmail(email);
		server.giveCustomerInfo(this.custInfo);
	}
	
	/** Function that works with the Appointment class.
	 *  Used to have the customer make an appointment
	 */
	public void getAppointment() {
		
	}
	
	/** Function that gets the account from the server */
	public void getAccount() {
		//Code this
	}
	public 
	
	/** Function that changes the customer's barber shop name */
	public void changeBarber(String barberShop) {
		this.barberShop = barberShop;
	}

	/** Function that gets the name of the customer's barbershop name */
	public String getBarber() {
		return this.barberShop;
	}
	
}
