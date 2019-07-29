
public class Customer extends Thread {
	
	public Appointment appt; //Customer's appointment date, their information, and barber shop
	private String barberShop;
	public CustomerInfo custInfo;
	private GetServer server;
	
	public Customer(CustomerInfo info) {
		this.custInfo = info;
		new AppointmentUI(this); //Opens the Appointment UI when Customer is called
	}
	
	public Appointment getAppointment() { /** Gets the appointment object */
		return appt;
	}
		 
	public void updateCustomer(CustomerInfo custInfo){ /** Updates customer's information */
		this.custInfo = custInfo;
	}
		
	public void setBarberShop(String barberShop) { 	/** Function that asks user for the name of the barbershop and sets it */
		
		this.barberShop = barberShop;
	}
	
	public void changePhone(long phoneNum) { 	/** Function that changes the customer's phone number */
		custInfo.setCustomerPhone(phoneNum);
		server.giveCustomerInfo(this.custInfo);
	}
	
	public void changeEmail(String email) { 	/** Function that changes the customer's phone number */
		custInfo.setCustomerEmail(email);
		server.giveCustomerInfo(this.custInfo);
	}
	
	public void changeUserName(String username) {	/** Function that changes the customer's username */
		
		custInfo.setUserName(username);
		server.giveCustomerInfo(this.custInfo);
	}
	
	public void changeBarber(String barberShop) {	/** Function that changes the customer's barber shop name */
		this.barberShop = barberShop;
	}

	public String getBarber() { 	/** Function that gets the name of the customer's barbershop name */
		return this.barberShop;
	}
	
	public CustomerInfo getCustomerInfo() {	/** Function that returns the customer's info */
		return this.custInfo;
	}
}
