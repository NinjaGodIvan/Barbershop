import java.io.Serializable;

public class BarberShop extends Thread {
	String shopName;
	String shopAddr; // Address of location
	String shopOwner;
	long shopPhone; // phone # for contact
	String email;
	String webAddr; // website url
	String shopUserName; // user name of application shop account
	CustomerInfo[] barberStaff; // for unambiguity, will change data type to barberId
	BarberShopInfo shopInfo;
	GetServer getServer;
	
	
	/* to add to constructor::
	 * BarberID[] :: array of barbers who work for barber shop
	 *
	 */
	BarberShop(BarberShopInfo shopInfo) {
		this.shopName = shopInfo.getName();
		this.shopAddr = shopInfo.getAddress();
		this.shopPhone = shopInfo.getPhone();
		this.email = shopInfo.getEmail();
		this.shopUserName = shopInfo.getUserName();
		this.barberStaff = null;
		this.shopInfo = shopInfo;
		this.getServer = new GetServer();
		this.start();
	}
	
	/* get barberShopInfo from server to update it if some customer
	 * made changes to it
	 * 
	 * 
	 */
	public void run() {
		while (true) {
			try {
				BarberShopInfo temp = getServer.getBarberShopInfo(shopUserName);
				if (temp != null)
					this.shopInfo = temp; 
				sleep(15000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
	}
	
	public void upgradeCustomer(String userName) {
		try {
			CustomerInfo customer = getServer.getCustomerInfo(userName); // get a customer account from server
			customer.makeBarber(this.shopInfo); // upgrade to a barber account
			while (!getServer.giveCustomerInfo(customer)) {
				System.out.println("error not connected");
				this.sleep(5000);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	//setters
	public void setShopName(String name) {
		this.shopName = name;
	}
	public void setAddr(String addr) {
		this.shopAddr = addr;
	}

	public void setPhone(long phone) {
		this.shopPhone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String userName) {
		this.shopUserName = userName;
	}
	//getters 

	public String getShopAddr() {
		return shopAddr;
	}

	public long getShopPhone() {
		return shopPhone;
	}
	public String getShopUserName() {
		return shopUserName;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Barbershop [shopName=" + shopName + ", shopAddr=" + shopAddr
				+ ", shopPhone=" + shopPhone + ", email=" + email + ", shopUserName="
				+ shopUserName + "]";
	}
	
	public String getShopContactInfo() {
		return this.shopInfo.toString();
	}
	
	public void addBarberToStaff(CustomerInfo info) {
		if(barberStaff == null) {
			barberStaff = new CustomerInfo[1];
			barberStaff[0] = info;
		} else {
			CustomerInfo[] temp = new CustomerInfo[barberStaff.length +1];
			barberStaff = temp;
			barberStaff[barberStaff.length] = info;
		}
	}
	
	public void removeBarberFromStaff(String userName) {
		for(int i = 0; i<barberStaff.length; i++) {
			/*
			 * if barber to be removed is not the last entry in the array
			 */
			if((userName == barberStaff[i].getUserName()) && i<(barberStaff.length - 1)) {
				for(int j=i+1; j<(barberStaff.length); j++, i++)
					barberStaff[i] = barberStaff[j]; // overwrite barber ID number 
				CustomerInfo[] temp = new CustomerInfo[barberStaff.length-1];
				barberStaff = temp;
				return;
				/*
				 * if barber is at the end of the arrays
				 */
			} else if ((userName == barberStaff[i].getUserName()) && i==(barberStaff.length - 1) ) {
				CustomerInfo[] temp = new CustomerInfo[barberStaff.length-1];
				barberStaff = temp;
				return;
			} else
				/*
				 * Will have to change this to send notification through the app
				 */
				
				System.out.print("Barber not found");
		}
	}
	// must add a method to locate and return the appointment calendar for the barbershop
	/*
	public AppointmentCalendar getAppointmentSchedule(BarberID barber) {
		
	} */
}
