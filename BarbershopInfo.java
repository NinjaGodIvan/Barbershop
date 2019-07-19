package userInfo;

import java.io.Serializable;

public class BarbershopInfo implements Serializable{
	private String userName;
	private int barbershopId;
	private String barbershopName;
	private String barbershopEmail;
	private int barbershopPhone;
	
	

	public BarbershopInfo(String userName, int barbershopId, String barbershopName, String barbershopEmail,
			int barbershopPhone) {
		super();
		this.userName = userName;
		this.barbershopId = barbershopId;
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

	

	public int getBarbershopId() {
		return barbershopId;
	}

	public void setBarbershopId(int barbershopId) {
		this.barbershopId = barbershopId;
	}

	@Override
	public String toString() {
		return "BarbershopInfo [userName=" + userName + ", barbershopId=" + barbershopId + ", barbershopName="
				+ barbershopName + ", barbershopEmail=" + barbershopEmail + ", barbershopPhone=" + barbershopPhone
				+ "]";
	}
	
	
	
}
