import java.io.Serializable;

public class BarberShopInfo implements Serializable {
	private String name;
	private String email;
	private String userName;
	private long phone;
	private String password;
	private String address;
	CustomerInfo[] barberStaff;
	Appointment[] appointmentList;
	public Waitlist currentList;	
	int appointmentIndex;
	LinkedList messages;
//	public Waitlist currentList;
	
	BarberShopInfo(String name,String userName,String email,long phone,String password) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = "nothing for now";
		this.appointmentList = new Appointment[100];
		this.appointmentIndex = 0;
		this.messages = new LinkedList();
	}
	
	BarberShopInfo(String userName) { // just a test constructor
		this("test", userName,"test",5555,"no password");
	}

	BarberShopInfo(String userName,String password) { // just a test constructor
		this("test", userName,"test",5555,password);
	}
	
	public boolean giveAppointment(Appointment appointment) {
		if (appointmentIndex < 100) {
			appointmentList[appointmentIndex] = appointment;
			appointmentIndex++;
			return true;
		} else {
			return false;
		}
	}
	
	public void giveMessage(String message) {
		messages.addNode(message);
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
	public Appointment[] getAppointmentArray() {
		return this.appointmentList;
	}
	public String getAppointments() {
		String allAppointments  = "";
		for(Appointment a : appointmentList) {
			allAppointments += a.toString() + "\n";
		}
		return allAppointments;
	}
	public String toString() {
		return "Name" + userName;
	}
}
