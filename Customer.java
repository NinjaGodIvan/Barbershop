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
	
	//Testing driver
	public static void main(String [] args) {
		
		Customer cust = new Customer("NinjaGodIvan", "Ivan", "ivan4ubc@yahoo.com","111-1111", 10);
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
	
	/** Function that gets the account from the server */
	public void getAccount() {
		
	}
	
	/** Function that changes the customer's barber shop */
	public void changeBarber(String barberShop) {
		this.barberShop = barberShop;
	}

}
