import java.io.Serializable;
import CustomerInfo;
import BarbershopInfo;

class Waitlist implements Serializable {
	
	private BarberShopInfo shopInfo;
	private CustomerInfo[] list = null;
	private int size = 0;
	
	public Waitlist(BarberShopInfo shopInfo) {
		this.shopInfo = shopInfo;
		this.shopInfo.currentList = this;
	}
	public void addToWait(CustomerInfo cInfo) {
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
	public String getCurrentWaitlist() {
		String currentWaitlist = "";
		for(int i = 0; i < this.size; i++) {
			currentWaitlist += (list[i].toString() + "\n");
		}
		return currentWaitlist;
	}
	public String estimateWait() {
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

		// notify customer of appointment
		return nextCustomer; // return customer ID to the requesting barbershop
	}	
}
