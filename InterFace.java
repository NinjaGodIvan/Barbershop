import java.util.Scanner;

public class InterFace {
	GetServer getServer = new GetServer();
	BarberShop barberShop;
	Barber barber;
	Customer customer;

	InterFace() {
		getInput();
		
		
	}
	
	public void makeCustomerAccount(String name,String password) {
		boolean test = getServer.createCustomerInfoAccount(new CustomerInfo(name,password));
		if (test) {
			System.out.println("ACCOUNT CREATED");
		} else {
			System.out.println("ACCOUNT EXISTS");
		}		
	}
	
	public void makeBarberAccount(String name,String password) {
		CustomerInfo temp = new CustomerInfo(name,password);
		temp.isBarber = true;
		boolean test = getServer.createCustomerInfoAccount(temp);
		if (test) {
			System.out.println("ACCOUNT CREATED");
		} else {
			System.out.println("ACCOUNT EXISTS");
		}		
	}


	public void makeBarberShopAccount(String name,String password) {
		boolean test = getServer.createBarberShopAccount(new BarberShopInfo(name,password));
		if (test) {
			System.out.println("ACCOUNT CREATED");
		} else {
			System.out.println("ACCOUNT EXISTS");
		}				
	}
	
	public void logInCustomer(String name, String password) {
		CustomerInfo customerInfo = getServer.getCustomerInfo(name,password);
		if (customerInfo == null) {
			System.out.println("ACCOUNT DOESNT EXIST");
		} else {
			if (customerInfo.isBarber == true)
				barber = new Barber(customerInfo);
			else 
				customer = new Customer(customerInfo);
		}		
	}
	
	public void logInBarberShop(String name,String password) {
		BarberShopInfo barberShopInfo = getServer.getBarberShopInfo(name,password);
		if (barberShopInfo == null) {
			System.out.println("ACCOUNT DOESNT EXIST");
		} else {
			barberShop = new BarberShop(barberShopInfo);
		}
	}
	
	public void getInput() {
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter 1 to make customer Account\nEnter 2 to log into customer account\nEnter 3 to make BarberShopAccount\nEnter 4 to log into BarberShopAccount\nEnter 5 to make barber Account\nEnter 6 to log into barber account ");
			int value = keyboard.nextInt();
			keyboard.nextLine();
			if (value == 1) {
				System.out.println("Please Enter a userName: ");
				String name = keyboard.nextLine();
				System.out.println("Please Enter a password: ");
				String password = keyboard.nextLine();
				
				makeCustomerAccount(name,password);

			} else if (value == 2) {
				System.out.println("Please Enter a userName: ");
				String name = keyboard.nextLine();
				System.out.println("Please Enter a password: ");
				String password = keyboard.nextLine();
				logInCustomer(name,password);			
	
			} else if (value == 3) {
				System.out.println("Please Enter a barberShop name: ");
				String name = keyboard.nextLine();
				System.out.println("Please Enter a password: ");
				String password = keyboard.nextLine();
				
				makeBarberShopAccount(name,password);		
				
			} else if (value == 4) {
				System.out.println("Please Enter a userName: ");
				String name = keyboard.nextLine();
				System.out.println("Please Enter a password: ");
				String password = keyboard.nextLine();
				logInBarberShop(name,password);
				
				
			} else if (value == 5) {
				System.out.println("Please Enter a barber name: ");
				String name = keyboard.nextLine();
				System.out.println("Please Enter a password: ");
				String password = keyboard.nextLine();
				
				makeBarberAccount(name,password);		
				
			} else if (value == 6) {
				System.out.println("Please Enter a userName: ");
				String name = keyboard.nextLine();
				System.out.println("Please Enter a password: ");
				String password = keyboard.nextLine();
				
				
				
			}
			
		} catch (Exception e) {
;
		} finally {
			this.getInput();			
		}		
		
		
	}












}
