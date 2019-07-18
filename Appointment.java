/* Implementation is not complete */

package barber;
import java.util.Calendar;
import java.util.LinkedList;

public class Appointment {
	
	private LinkedList <Calendar> appointment_list = new LinkedList<Calendar>();
	
	/** Function that adds a customer's appointment */
	public void makeAppointment(int day, int month, int year, int min, int hour) {
		
		//Sets the customer's appointment based on the input
		appointment = Calendar.getInstance();
		appointment.set(year, month - 1, day, hour, min, 0);
		
		//Gets current date for comparison
		Calendar curr = Calendar.getInstance();
		//Only used for conditional statements; Must go back to previous month because
		appointment.add(Calendar.MONTH, -1);
		
		//Must check for valid inputs
		if(day < 1 || day > appointment.getActualMaximum(Calendar.DAY_OF_MONTH)) 
			System.out.println("You must pick a day between 1 to " + appointment.getActualMaximum(Calendar.DAY_OF_MONTH) + " this month");
		else if(month < 1 || month > 12)
			System.out.println("You must pick a month between 1 to 12");
		else if(min < 1 || min > 59)
			System.out.println("You must pick a minute between 1 to 59");
		else if(hour < 1 || hour > 12)
			System.out.println("You must pick an hour between 1 to 12");
		else if(year < curr.get(Calendar.YEAR)) 
			System.out.println("You must pick a year after " + (curr.get(Calendar.YEAR) - 1));
		else {
			appointment.add(Calendar.MONTH, 1);
			//Adds the appointment to the list
			appointment_list.add(appointment);
			System.out.println("Success!");
		}
	}
	
	/** Function that gets the customer's appointment */
	public String getAppointment(int i) {
		
		int day, monthInInt, year, min, hour;
		String month = "January";
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		Calendar cal = appointment_list.get(i);
		day = cal.get(Calendar.DATE);
		monthInInt = cal.get(Calendar.MONTH) + 1;
		year = cal.get(Calendar.YEAR);
		min = cal.get(Calendar.MINUTE);
		hour = cal.get(Calendar.HOUR);	
		
		for(int a = 0; a < months.length; a++)
			if(a == (monthInInt - 1)) {
				month = months[monthInInt - 1];
			}
		
		return month + " " + day + ", " + year + " " + hour + ":" + min;
	}
	
	/** Function that outputs the customer's appointment date */
	public void checkAppointment(int choice) {
		
		for(int i = 0; i < appointment_list.size(); i++) {
			if(choice == i) {
				System.out.println("The appointment time is " + getAppointment(i));			
				break;
			}
		}
	}
}
