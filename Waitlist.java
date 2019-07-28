import java.io.Serializable;
import CustomerInfo;
import BarbershopInfo;

class Waitlist implements Serializable {
	//uses barbershopInfo to manage the waitlist, as an array of customers. 
	private BarbershopInfo shopInfo;
	private CustomerInfo[] list = null;
	private int size = 0;
	
	public Waitlist(BarbershopInfo shopInfo) {
		this.shopInfo = shopInfo;
		this.shopInfo.currentList = this;
	}
	public void addToWait(CustomerInfo cInfo) { //put people into the array, increasing size each time.
		if(list == null) {
			list[0] = cInfo;
			this.size++;
		}
		else {
			CustomerInfo[] temp = new CustomerInfo[this.size + 1];
			temp[this.size] = cInfo;
			list = temp;
			this.size++;
		}		
	}
	public int listSize() {
		return this.size;
	}
	// returns a newline separated string of the waitlist customers, using toString method
	public String getCurrentWaitlist() {
		String currentWaitlist = "";
		for(int i = 0; i < this.size; i++) {
			currentWaitlist += (list[i].toString() + "\n");
		}
		return currentWaitlist;
	}
	public String estimateWait() { // estimate the wait time according to half hour increments.
		double waitTime = (this.size*30)/60;
		return "Wait time is " + "%.1f" + waitTime + "hrs";
	}
	public CustomerInfo getNext() {
		CustomerInfo next = null;
		if(list != null) {
			next = list[0];
			CustomerInfo[] temp= new CustomerInfo[listSize() -1];
			for(int i = 0, j = 1; j < this.size; i++, j++) {
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
		return nextCustomer; // return customer ID to the requesting barbershop
	}	// did not implement this functionality in final unfinished form
}
