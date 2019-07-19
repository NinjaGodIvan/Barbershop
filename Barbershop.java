
public class Barbershop {
	String shopName;
	String shopAddr; // Address of location
	String shopOwner;
	String shopPhone; // phone # for contact
	String email;
	String webAddr; // website url
	String shopUserName; // user name of application shop account
	int shopId = 0; /* every time new barber shop is created, increment shop id
						provides easy way to search through the barber shops to locate appointment calendars, etc*/
	
	//BarberId[] barberStaff;
	//BarberShopInfo shopInfo;
	
	
	/* to add to constructor::
	 * BarberID[] :: array of barbers who work for barber shop
	 * BarberShopInfo :: if neccessary
	 */
	Barbershop(String name, String addr, String owner, String phone, String email, String webAddr, String userName) {
		this.shopName = name;
		this.shopAddr = addr;
		this.shopOwner = owner;
		this.shopPhone = phone;
		this.email = email;
		this.webAddr = webAddr;
		this.shopUserName = userName;
		this.shopId++; // 
	}
	Barbershop(String name, String addr, String owner, String phone, String email, String userName) {
		this(name, addr, owner, phone, email, "N/A", userName);
	}
	//setters
	public void setName(String name) {
		this.shopName = name;
	}
	public void setAddr(String addr) {
		this.shopAddr = addr;
	}
	public void setOwner(String own) {
		this.shopOwner = own;
	}
	public void setPhone(String phone) {
		this.shopPhone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setWeb(String webAddr) {
		this.webAddr = webAddr;
	}
	public void setUserName(String userName) {
		this.shopUserName = userName;
	}
	//getters 
	public String getShopName() {
		return shopName;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public String getShopOwner() {
		return shopOwner;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public String getWebAddr() {
		return webAddr;
	}
	public String getShopUserName() {
		return shopUserName;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Barbershop [shopName=" + shopName + ", shopAddr=" + shopAddr + ", shopOwner=" + shopOwner
				+ ", shopPhone=" + shopPhone + ", email=" + email + ", webAddr=" + webAddr + ", shopUserName="
				+ shopUserName + "]";
	}
	public String getShopContactInfo() {
		return this.getShopName() + " " + this.getShopOwner() + " " + this.getEmail() + " " + this.getShopPhone();
	}
	// must add a method to locate and return the appointment calendar for the barbershop
	/*
	public AppointmentCalendar getAppointmentSchedule(BarberID barber) {
		
	} */
}
