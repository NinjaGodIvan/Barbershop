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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return "BarbershopInfo [name=" + name + ", email=" + email
				+ ", userName=" + userName + ", phone=" + phone + ", address="
				+ address + "]";
	}
}
