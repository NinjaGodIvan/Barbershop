import java.io.Serializable;

class Packet  implements Serializable {
	CustomerInfo infoC;
	BarberShopInfo infoB;
	boolean back; // value that states if it is or isnt going to send data back
	RequestEnum r = new RequestEnum();
	RequestEnum.Request request;
	// if true send back
	private void setBack(RequestEnum.Request r) {
		if (RequestEnum.Request.giveData == r || RequestEnum.Request.removeData == r) {
			this.back = false;
		} else {
			this.back = true;
		}
	}
	
	Packet(CustomerInfo info,RequestEnum.Request request) { // constructor, DOESNT SEND BACK DATA
		this.infoC = info;
		this.infoB = null;
		this.request = request;
		this.setBack(request);
	}

	Packet(BarberShopInfo info,RequestEnum.Request request) { // constructor, DOESNT SEND BACK DATA
		this.infoC = null;
		this.infoB = info;
		this.request = request;
		this.setBack(request);
	}
	
	Packet(String userName, RequestEnum.Request request) {
		this.infoC = new CustomerInfo(userName);
		this.infoB = null;
		this.request = request;
		this.setBack(request);
	}
	
	Packet(String userName,String password, RequestEnum.Request request) {
		this.infoC = new CustomerInfo(userName,password);
		this.infoB = null;
		this.request = request;
		this.setBack(request);	
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
