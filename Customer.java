/* Implementation is incomplete. Still need to put more */

package barber;
import java.util.Calendar;
import java.util.Scanner;
public class Customer {
	
	//Customer's appointment date, their information, and barber shop
	private Calendar appointment;
	private String barberShop;
	private CustomerInfo custInfo;
	
	public Customer(String userName, String name, String email, String phoneNum, int numVisits) {
		custInfo = new CustomerInfo(userName,name,email,phoneNum,numVisits);
	}
	
	//Testing driver (delete this soon)
	public static void main(String [] args) {
		
		Customer cust = new Customer("NinjaGodIvan", "Ivan", "ivan4ubc@yahoo.com","FUK-BICH", 10);
		cust.changePhone("555-5555");
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
	}
	
	/** Function that changes the customer's phone number */
	public void changeEmail(String email) {
		custInfo.setCustomerEmail(email);
	}
	
	/** Function that works with the Appointment class.
	 *  Used to have the customer make an appointment
	 */
	public void makeAppointment() {
		
		//Get accessed to Appointment class
		Appointment a = new Appointment();
		//Testing values (remove them soon)
		a.makeAppointment(appointment, 12, 8, 2019, 30, 4);
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
