//I'm still editing.

package barber;
import java.util.Scanner;

public class Menu {
	
	/** Menu for the barber */
	public void BarberMenu(BarberInfo barbInfo) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Hello! " + barbInfo.getUserName() + "!");
		System.out.println("Press enter to continue");
		in.nextLine();
		
		int optionPicker, goAgain;
		
		do{
			//Still need to put more options
			do {
		
				System.out.println("----MAIN MENU----");
				System.out.println("1. Go to Information");
				optionPicker = in.nextInt();		
			
				if(optionPicker != 1)
					System.out.println("Pick an option from 1 to 4");
				
			}while(optionPicker != 1);
			
			if(optionPicker == 1) {
				
				do {
						System.out.println("--------INFORMATION--------");
						System.out.println("Name: " + barbInfo.getBarberName());
						System.out.println("Username: " + barbInfo.getUserName());
						System.out.println("Phone Number: " + barbInfo.getBarberPhone());
						System.out.println("---------------------------");

						System.out.println("1. Change Phone Number");
						System.out.println("2. Change Email Address");
						System.out.println("3. Change Username");
						optionPicker = in.nextInt();			
					
					if(optionPicker != 1 || optionPicker != 2 || optionPicker != 3)
						System.out.println("Pick an option from 1 to 3");
					
					}while(optionPicker != 1 || optionPicker != 2 || optionPicker != 3);
				
				if(optionPicker == 1) {
					
					int phoneNum;
					System.out.println("Enter your new phone number: ");
					phoneNum = in.nextInt();
					barbInfo.setBarberPhone(phoneNum);
					System.out.println("Your phone number has changed!");
					
				}else if(optionPicker == 2){
					
					String email;
					System.out.println("Enter your new email address: ");
					in.nextLine();
					email = in.nextLine();
					barbInfo.setBarberEmail(email);
					System.out.println("Your email address has changed!");
				}else {
					
					String username;
					System.out.println("Enter your new username: ");
					in.nextLine();
					username = in.nextLine();
					barbInfo.setUserName(username);
					System.out.println("Your username has changed!");
				}
			}
			
			do {
				
				System.out.println("Do you want to go again?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				goAgain = in.nextInt();
				
				if(goAgain != 1 || goAgain != 2)
					System.out.println("Pick an option from 1 to 2");
				
			}while(goAgain != 1 || goAgain != 2);
			
		}while(goAgain == 1);
		in.close();
	}
	
	/** Menu for the customer */
	public void CustomerMenu(Customer cust, CustomerInfo custInfo) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Hello! " + custInfo.getUserName() + "!");
		System.out.println("Press enter to continue");
		in.nextLine();
		
		int optionPicker, goAgain;
			
		do {
		
			//Main Menu
			do {
				System.out.println("----MAIN MENU----");
				System.out.println("1. Make an Appointment");
				System.out.println("2. Check your Appointment");
				System.out.println("3. Change Barber Shop");
				System.out.println("4. Go to Information");
				optionPicker = in.nextInt();
				
				if(optionPicker != 1 || optionPicker != 2 || optionPicker != 3 || optionPicker != 4)
					System.out.println("Pick an option from 1 to 4");
				
			}while(optionPicker != 1 || optionPicker != 2 || optionPicker != 3 || optionPicker != 4);
			
			if(optionPicker == 1) {
				//Do something
			}
			else if(optionPicker == 2) {
				//Do something
			}
			else if(optionPicker == 3) {
				
				String barberName;
				System.out.println("Enter the name of your new barbershop: ");
				in.nextLine();
				barberName = in.nextLine();
				cust.changeBarber(barberName);
				System.out.println("Your barbershop's name has changed!");
			}else {
				
				do {
					System.out.println("--------INFORMATION--------");
					System.out.println("Name: " + custInfo.getCustomerName());
					System.out.println("Username: " + custInfo.getUserName());
					System.out.println("Phone Number: " + custInfo.getCustomerPhone());
					System.out.println("---------------------------");

					System.out.println("1. Change Phone Number");
					System.out.println("2. Change Email Address");
					System.out.println("3. Change Username");
					optionPicker = in.nextInt();			
					
					if(optionPicker != 1 || optionPicker != 2 || optionPicker != 3)
						System.out.println("Pick an option from 1 to 3");
					
				}while(optionPicker != 1 || optionPicker != 2 || optionPicker != 3);
				
				if(optionPicker == 1) {
					
					String phoneNum;
					System.out.println("Enter your new phone number: ");
					in.nextLine();
					phoneNum = in.nextLine();
					cust.changePhone(phoneNum);
					System.out.println("Your phone number has changed!");
					
				}else if(optionPicker == 2){
					
					String email;
					System.out.println("Enter your new email address: ");
					in.nextLine();
					email = in.nextLine();
					cust.changeEmail(email);
					System.out.println("Your email address has changed!");
				}else {
					
					String username;
					System.out.println("Enter your new username: ");
					in.nextLine();
					username = in.nextLine();
					custInfo.setUserName(username);
					System.out.println("Your username has changed!");
				}
			}
			
			//If the customer want to go again
			do {
			
				System.out.println("Do you want to go again?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				goAgain = in.nextInt();
				
				if(goAgain != 1 || goAgain != 2)
					System.out.println("Pick an option from 1 to 2");
				
			}while(goAgain != 1 || goAgain != 2);

			
		}while(goAgain == 1);
		in.close();
		
		System.out.println("Thank you!");
	}

}