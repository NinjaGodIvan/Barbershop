import java.io.Serializable;
import CustomerInfo;
import BarbershopInfo;

class Waitlist implements Serializable {
	
	private BarbershopInfo barberShopId;
	private CustomerInfo[] list = null;
	private int size = 0;
	
	public Waitlist(BarbershopInfo shopId) {
		barberShopId = shopId;
		this.barberShopId.currentList = this;
	}
	public void addToWait(CustomerInfo cInfo) {
		if(list == null) {
			list[0] = cInfo;
			this.size++;
		}
		else {
			CustomerInfo[] temp = new CustomerInfo[listSize() + 1];
			temp[listSize()] = cInfo;
			list = temp;			
		}		
	}
	public int listSize() {
		return this.size;
	}
	public String getCurrentWaitlist() {
		String currentWaitlist = "";
		for(int i = 0; i < listSize(); i++) {
			currentWaitlist += (list[i].toString() + "\n");
		}
		return currentWaitlist;
	}
	public String estimateWait() {
		double waitTime = (listSize()*30)/60;
		return "Wait time is " + "%.1f" + waitTime + "hrs";
	}
	public CustomerInfo getNext() {
		CustomerInfo next = null;
		if(list != null) {
			next = list[0];
			CustomerInfo[] temp= new CustomerInfo[listSize() -1];
			for(int i = 0, j = 1; j < listSize(); i++, j++) {
				temp[i] = list[j];
			}
			list = temp;
		}
		return next;
	}
	public CustomerInfo waitListPop() {
		CustomerInfo nextCustomer = this.getNext(); //get first customer on list, remove if non responsive.
		
		String customerContact = nextCustomer.getPhoneNumber();// get customer phone number and notify
		// notify customer of appointment
		nextCustomer.notifyCustomer(customerContact);
		return nextCustomer; // return customer ID to the requesting barbershop
	}	
}
