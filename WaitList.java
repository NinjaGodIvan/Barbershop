import LinkedList;
import CustomerInfo;
import BarberShopInfo;

// currently configured to use customer Id as a parameter, can be switched out when customer ID class is built
class WaitList {
	private static BarberShopInfo barberShopId;
	private Node waiting;
	
	public WaitList(BarberShopId shopId) {
		barberShopId = shopId;
	}
	public void addToWait(CustomerInfo c) {
		waiting.addNode(c);
	}
	public String getCurrentWaitList() {
		String currentWaitList = "";
		for(int i = 0; i < list.size(); i++) {
			currentWaitList += (list.get(i).toString() + "\n");
		}
		return currentWaitList;
	}
	public String estimateWait() {
		double waitTime = (list.size()*30)/60;
		return "Wait time is " + "%.1f" + waitTime + "hrs";
	}
	public CustomerId waitListPop() {
		CustomerId nextCustomer = list.get(0); //get first customer on list, remove if non responsive.
		list.remove(0); // clear first customer from queue
		String customerContact = nextCustomer.getPhoneNumber();// get customer phone number and notify
		// notify customer of appointment	
		return nextCustomer; // return customer ID to the requesting barbershop
	}	
}
