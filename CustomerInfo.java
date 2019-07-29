import java.io.Serializable;

public class CustomerInfo implements Serializable {
	private String name;
	private String email;
	private String userName;
	private String password;
	private long phone;
	boolean isBarber;
	BarberShopInfo info;
	BarberShopInfo[] barberShopList;
	LinkedList messages;
	Appointment appointment;
	
	CustomerInfo(String name,String userName,String email,long phone,String password) {
//		this.messages = new String[50];
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.isBarber = false;
		this.barberShopList = null;
		this.info = null;
		this.messages = new LinkedList();
	}
	
	public void giveMessage(String message) {
		messages.addNode(message);
	}
	
	CustomerInfo(String userName) { // just a test constructor
		this("test", userName,"test",5555,"no password");
	}
	
	CustomerInfo(String userName, String password) { // just a test constructor
		this("test", userName,"test",5555,password);
	}	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void giveList(BarberShopInfo[] barberShopList) {
		this.barberShopList = barberShopList;
	}
	
	public void makeBarber(BarberShopInfo info) {
		if (info != null) {
			this.isBarber = true;
			this.info = info;
		}
	}
	
	public void setCustomerPhone(long phone) {
		this.phone = phone;
	}
	
	public void setCustomerEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPhoneNumber() {
		return phone + "";
}	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "UserName: " + userName + ", PassWord: " + password;
	}
}
