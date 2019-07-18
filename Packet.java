import java.io.Serializable;

class Packet  implements Serializable {
	CustomerInfo infoC;
	BarberShopInfo infoB;
	boolean back; // value that states if it is or isnt going to send data back
	
	Packet(CustomerInfo info, boolean back) {
		this.infoC = info;
		this.infoB = null;
		this.back = back;
	}
	
	Packet(BarberShopInfo info,boolean back) {
		this.infoB = info;
		this.infoC = null;
		this.back = back;
	}
	
	public CustomerInfo getCustomer() {
		return this.infoC;
	}
	
	public BarberShopInfo getBarberShop() {
		return this.infoB;
	}
	
	public boolean sendBack() {
		return back;
	}
}
