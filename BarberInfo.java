package userInfo;
import java.io.Serializable;

public class BarberInfo implements Serializable {
	private String userName;
	private String barberName;
	private String barberEmail;
	private int barberPhone;
	
	

	public BarberInfo(String userName, String barberName, String barberEmail, int barberPhone) {
		super();
		this.userName = userName;
		this.barberName = barberName;
		this.barberEmail = barberEmail;
		this.barberPhone = barberPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBarberName() {
		return barberName;
	}

	public void setBarberName(String barberName) {
		this.barberName = barberName;
	}

	public String getBarberEmail() {
		return barberEmail;
	}

	public void setBarberEmail(String barberEmail) {
		this.barberEmail = barberEmail;
	}

	public int getBarberPhone() {
		return barberPhone;
	}

	public void setBarberPhone(int barberPhone) {
		this.barberPhone = barberPhone;
	}

	

	@Override
	public String toString() {
		return "BarberInfo [userName=" + userName + ", barberName=" + barberName
				+ ", barberEmail=" + barberEmail + ", barberPhone=" + barberPhone + "]";
	}

}
