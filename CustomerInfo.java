package userInfo;
import java.io.Serializable;

public class CustomerInfo implements Serializable {
	private String userName;
	private int customerId;
	private String customerName;
	private String customerEmail;
	private int customerPhone;
	private int visitNumber;
	
	

	public CustomerInfo(String userName, int customerId, String customerName, String customerEmail, int customerPhone,
			int visitNumber) {
		super();
		this.userName = userName;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.visitNumber = visitNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(int customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(int visitNumber) {
		this.visitNumber = visitNumber;
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerInfo [userName=" + userName + ", customerId=" + customerId + ", customerName=" + customerName
				+ ", customerEmail=" + customerEmail + ", customerPhone=" + customerPhone + ", visitNumber="
				+ visitNumber + "]";
	}
	
	
	
}
