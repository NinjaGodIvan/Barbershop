import java.util.Scanner;
//package userInfo;


// Chao's credit: From this line to the end of the toString function
public class Barber
{
	
	private String userName;
	private String password;
	private String barberName;
	private String barberEmail;
	private int barberPhone;
	// Phil credit: These 2 lines
	boolean availability;
	String status;
	
	
	public Barber(String userName, String barberName, String barberEmail, int barberPhone, boolean availability, String status) 
	{	
		this.userName = userName;
		this.password = userName;
		this.barberName = barberName;
		this.barberEmail = barberEmail;
		this.barberPhone = barberPhone;
		this.availability = availability;
		this.status = status;
		BarberInfo(this.username, this.barberName, this.barberEmail, this.barberPhone);
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getBarberName() 
	{
		return barberName;
	}

	public void setBarberName(String barberName) 
	{
		this.barberName = barberName;
	}

	public String getBarberEmail() 
	{
		return barberEmail;
	}

	public void setBarberEmail(String barberEmail) {
		this.barberEmail = barberEmail;
	}

	public int getBarberPhone() {
		return barberPhone;
	}

	public void setBarberPhone(int barberPhone) {
		this.barberPhone = barberPhone;
	}

	@Override
	public String toString() {
		return "BarberInfo [userName=" + userName + ", barberName=" + barberName + ", barberEmail=" + barberEmail
				+ ", barberPhone=" + barberPhone + "]";
	}
	
	// Phil credit: for all of the rest
	public void nextCustomerFromWaitlist()
	{
		String tes = null;
		WaitList waitlist = new WaitList(tes);
		System.out.println("The next person up is: " + waitlist.waitListPop());
	}
	
	public void takeBreak() 
	{
		this.availability = false;
		this.status = "On break";
		//System.out.println()
	}
	
	public void clockOut() 
	{
		this.availability = false;
		this.status = "Clocked out";
	}
	
	public void menu()
	{
		// Eventually change this to a prompt with buttons
		Scanner in = new Scanner(System.in);
		System.out.println("Please choose from the following:");
		System.out.println("1. Next Customer from Waitlist");
		System.out.println("2. Take a break");
		System.out.println("3. Clock out");
		
		int choice = in.nextInt();
		
		if (choice == 1) 
		{
			nextCustomerFromWaitlist();
		} else if (choice == 2) 
		{
			takeBreak();
		} else if (choice == 3)
		{ 
			clockOut();
		} else 
		{
			System.out.println("That is not a choice! Try again");
			menu();
		}
	}
	
	public void changePassword() 
	{
		// input from user turns into new password, sent to database
		Scanner pass = new Scanner(System.in);
		System.out.println("Please enter new Password");
		String newPass = pass.next();
		System.out.println("Please confirm new Password");
		String newPass2 = pass.next();
		if (newPass.equals(newPass2)) {
			this.password = newPass;
//			GetServer server = new GetServer();
//			server.giveDataBarber(this);
		}
	}
	
	public void changePhone() 
	{
		Scanner phone = new Scanner(System.in);
		System.out.println("Please enter new Phone Number");
		int newPhone = phone.nextInt();
		this.barberPhone = newPhone;
//		GetServer server = new GetServer();
//		server.giveDataBarber(this);
	}
	
	public void changeEmail() 
	{
		Scanner email = new Scanner(System.in);
		System.out.println("Please enter new Email");
		String newEmail = email.next();
		this.barberEmail = newEmail;
//		GetServer server = new GetServer();
//		server.giveDataBarber(this);
	}
	
	public void banCustomer() {
		// pulls customer, finds him/her in database, sets their ban list property to true
		
		
	}
	
	public void makeAppointment()
	{
		
		
	}
	
	}
	
	
