
import java.io.Serializable;
import BarbershopInfo;
import Barber;

public class Barbershop implements Serializable {
	String shopName;
	String shopAddr; // Address of location
	String shopOwner;
	String shopPhone; // phone # for contact
	String email;
	String webAddr; // website url
	String shopUserName; // user name of application shop account
	int[] barberStaff; // for unambiguity, will change data type to barberId
	BarbershopInfo shopInfo;
	
	
	/* to add to constructor::
	 * BarberID[] :: array of barbers who work for barber shop
	 *
	 */
	Barbershop(String name, String addr, String owner, String phone, String email, String webAddr, String userName) {
		this.shopName = name;
		this.shopAddr = addr;
		this.shopOwner = owner;
		this.shopPhone = phone;
		this.email = email;
		this.webAddr = webAddr;
		this.shopUserName = userName;
		this.barberStaff = null;
		this.shopInfo = new BarbershopInfo(shopUserName, shopId, shopName, email, shopPhone);
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
		return this.shopInfo.toString();
	}
	
	public void addBarberToStaff(int barberIDNumber) {
		if(barberStaff == null) {
			barberStaff = new int[1];
			barberStaff[0] = barberIDNumber;
		} else {
			int[] temp = new int[barberStaff.length +1];
			barberStaff = temp;
			barberStaff[barberStaff.length] = barberIDNumber;
		}
	}
	public void removeBarberFromStaff(int barberIDNumber) {
		for(int i = 0; i<barberStaff.length; i++) {
			/*
			 * if barber to be removed is not the last entry in the array
			 */
			if(barberIDNumber == barberStaff[i] && i<(barberStaff.length - 1)) {
				for(int j=i+1; j<(barberStaff.length); j++, i++)
					barberStaff[i] = barberStaff[j]; // overwrite barber ID number 
				int[] temp = new int[barberStaff.length-1];
				barberStaff = temp;
				return;
				/*
				 * if barber is at the end of the arrays
				 */
			} else if (barberIDNumber == barberStaff[i] && i==(barberStaff.length - 1) ) {
				int[] temp = new int[barberStaff.length-1];
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
