import java.io.Serializable;

public class BarberShopInfo implements Serializable {
	private String name;
	private String email;
	private String userName;
	private long phone;
	private String password;
	private String address;
	
	BarberShopInfo(String name,String userName,String email,long phone,String password) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = "nothing for now";
	}
	
	BarberShopInfo(String userName) { // just a test constructor
		this("test", userName,"test",5555,"no password");
	}

	BarberShopInfo(String userName,String password) { // just a test constructor
		this("test", userName,"test",5555,password);
	}
	
	public String getAddress() {
		return address;
	}
	
	public long getPhone() {
		return phone;
	}
	
	public String getName() {
		return name;
	}	
	
	public String getUserName() {
		return userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "Name" + userName;
	}
}
