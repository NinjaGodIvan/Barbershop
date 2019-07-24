package barber;
import java.util.Scanner;

public class Customer extends Thread {
	
	//Customer's appointment date, their information, and barber shop
	public Appointment appt;
	private String barberShop;
	//private CustomerInfo custInfo;
	public CustomerInfo custInfo; /* Testing purposes */
	private GetServer server;
	
	//Testing the interface method
	public static void main(String [] args) {
		
		Scanner in = new Scanner(System.in);
		
		Customer cust = new Customer();
		BarberInfo barbInfo = null;
				
		while(true) {
				
			int choice;
		
			System.out.println("------MAIN MENU------");
			System.out.println("1. Make your appointment");
			System.out.println("2. Check your appointment");
			System.out.println("3. Cancel your appointment");
			System.out.println("4. Exit");
			System.out.print("Enter choice (1-3): ");
			choice = in.nextInt();
		
			if(choice == 1){
					//Creates an appointment for the customer
					System.out.print("Enter day: ");
					int day = in.nextInt();
					System.out.print("Enter month: ");
					int mon = in.nextInt();
					System.out.print("Enter year: ");
					int year = in.nextInt();
					System.out.print("Enter min: ");
					int min = in.nextInt();
					System.out.print("Enter hour: ");
					int hr = in.nextInt();
					System.out.print("Enter 1 (AM) or 2 (PM): "); 
					int am_pm = in.nextInt();
					cust.appt = new Appointment();
					cust.makeAppointment(barbInfo, cust, day, mon, year, min, hr, am_pm);
			}
			else if(choice == 2){
				if(cust.appt == null)
					System.out.println("You do not have an appointment to check. Please make one.");
				else
					cust.appt.checkAppointment(cust.custInfo);
			}
			else if(choice == 3){
				if(cust.appt == null)
					System.out.println("You do not have an appointment to cancel. Please make one.");
				else {
					cust.appt.cancelAppointment(cust);
					System.out.println("Your appointment has been canceled.");
					cust.appt = null;
				}
			}
			else {
				System.out.println("Thank you for using us!");
				in.close();
				System.exit(0);
			}
		}
	}
	
	//Interface Representation method (for presentation purposes [delete this soon])
	public static void CustomerAppointmentMenuDemo(String [] args) {
		
		Scanner in = new Scanner(System.in);
		
		Customer cust = new Customer();
		BarberInfo barbInfo = null;
				
		while(true) {
				
			int choice;
		
			System.out.println("------MAIN MENU------");
			System.out.println("1. Make your appointment");
			System.out.println("2. Check your appointment");
			System.out.println("3. Cancel your appointment");
			System.out.println("4. Exit");
			System.out.print("Enter choice (1-3): ");
			choice = in.nextInt();
		
			if(choice == 1){
					//Creates an appointment for the customer
					System.out.print("Enter day: ");
					int day = in.nextInt();
					System.out.print("Enter month: ");
					int mon = in.nextInt();
					System.out.print("Enter year: ");
					int year = in.nextInt();
					System.out.print("Enter min: ");
					int min = in.nextInt();
					System.out.print("Enter hour: ");
					int hr = in.nextInt();
					System.out.print("Enter 1 (AM) or 2 (PM): "); 
					int am_pm = in.nextInt();
					cust.appt = new Appointment();
					cust.makeAppointment(barbInfo, cust, day, mon, year, min, hr, am_pm);
			}
			else if(choice == 2){
				if(cust.appt == null)
					System.out.println("You do not have an appointment to check. Please make one.");
				else
					cust.appt.checkAppointment(cust.custInfo);
			}
			else if(choice == 3){
				if(cust.appt == null)
					System.out.println("You do not have an appointment to cancel. Please make one.");
				else {
					cust.appt.cancelAppointment(cust);
					System.out.println("Your appointment has been canceled.");
					cust.appt = null;
				}
			}
			else {
				System.out.println("Thank you for using us!");
				in.close();
				System.exit(0);
			}
		}

	}
	
	//Empty Customer constructor
	public Customer() {
		/* Testing Purposes */
		custInfo = new CustomerInfo("Ivan", "Ninja God Ivan", "ivan123@yahoo.com", 5554412, "abc123");
		barberShop = "Ultimate Barber Shop";
	}
	
	public void makeAppointment(int day, int mon, int year, int min, int hr, int am_pm) {
		
		/* Testing Purposes */
		BarberInfo barbInfo = new BarberInfo("Barber God 107", "Mike", "barber333@gmail.com", 4520918);
		appt.makeAppointment(barbInfo, custInfo, day, mon, year, min, hr, am_pm);
	}
	
	//Override Method for presentation (delete this soon)
	public void makeAppointment(BarberInfo barbInfo, Customer cust, int day, int mon, int year, int min, int hr, int am_pm) {
		
		/* Testing Purposes */
		barbInfo = new BarberInfo("Barber God 107", "Mike", "barber333@gmail.com", 4520918);
		cust.appt.makeAppointment(barbInfo, cust.custInfo, day, mon, year, min, hr, am_pm);
	}
	
	public Appointment getAppointment() {
		return appt;
	}
		
	public void updateCustomer(CustomerInfo custInfo){ 
		this.custInfo = custInfo;
	}
	
	//public void sendCustomer() // updates server customerInfo*/
	
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
		custInfo.setPhone(phoneNum);
		server.giveCustomerInfo(this.custInfo);
	}
	
	/** Function that changes the customer's phone number */
	public void changeEmail(String email) {
		custInfo.setEmail(email);
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
 
