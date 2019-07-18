package userInfo;

import java.io.Serializable;

public class BarbershopInfo implements Serializable{
	private String userName;
	private String barbershopName;
	private String barbershopEmail;
	private int barbershopPhone;
	
	public BarbershopInfo(String userName, String barbershopName, String barbershopEmail, int barbershopPhone) {
		
		this.userName = userName;
		this.barbershopName = barbershopName;
		this.barbershopEmail = barbershopEmail;
		this.barbershopPhone = barbershopPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBarbershopName() {
		return barbershopName;
	}

	public void setBarbershopName(String barbershopName) {
		this.barbershopName = barbershopName;
	}

	public String getBarbershopEmail() {
		return barbershopEmail;
	}

	public void setBarbershopEmail(String barbershopEmail) {
		this.barbershopEmail = barbershopEmail;
	}

	public int getBarbershopPhone() {
		return barbershopPhone;
	}

	public void setBarbershopPhone(int barbershopPhone) {
		this.barbershopPhone = barbershopPhone;
	}

	@Override
	public String toString() {
		return "BarbershopInfo [userName=" + userName + ", barbershopName=" + barbershopName + ", barbershopEmail="
				+ barbershopEmail + ", barbershopPhone=" + barbershopPhone + "]";
	}
	
	
}
