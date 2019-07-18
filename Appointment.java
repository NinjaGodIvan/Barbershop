/* Implementation is incomplete. Still need to put more */

package barber;
import java.util.Calendar;

public class Appointment {
		
	/** Function that adds a customer's appointment */
	public void makeAppointment(Calendar appointment, int day, int month, int year, int min, int hour) {
		
		//Sets the customer's appointment based on the input
		appointment = Calendar.getInstance();
		appointment.set(year, month - 1, day, hour, min, 0);
		
		//Gets current date for comparing year of the appointment and the current year
		Calendar curr = Calendar.getInstance();
		
		//Only used for conditional statements; Must go back to previous month because
		appointment.add(Calendar.MONTH, -1);
		
		//Must check for valid inputs
		if(day < 1 || day > appointment.getActualMaximum(Calendar.DAY_OF_MONTH)) { 
			System.out.println("You must pick a day between 1 to " + appointment.getActualMaximum(Calendar.DAY_OF_MONTH) + " in this month. Deleting your Appointment time :(");
			appointment.clear();
		}
		else if(month < 1 || month > 12) {
			System.out.println("You must pick a month between 1 to 12. Deleting your Appointment time :(");
			appointment.clear();
		}
		else if(min < 1 || min > 59) {
			System.out.println("You must pick a minute between 1 to 59. Deleting your Appointment time :(");
			appointment.clear();
		}
		else if(hour < 1 || hour > 12) {
			System.out.println("You must pick an hour between 1 to 12. Deleting your Appointment time :(");
			appointment.clear();
		}
		else if(year < curr.get(Calendar.YEAR)) {
			System.out.println("You must pick a year after " + (curr.get(Calendar.YEAR) - 1) + ". Deleting your Appointment time :(");
			appointment.clear();
		}
		else {
			//Goes to the next month for the correct appointment's month
			appointment.add(Calendar.MONTH, 1);
			System.out.println("Success! Your appointment had been added!");
		}
	}
	
	/** Function that gets the customer's appointment */
	public String getAppointment(Calendar cal) {
		
		int day, monthInInt, year, min, hour;
		String month = "January";
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
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
	public void checkAppointment(Calendar cal, CustomerInfo info) {
		
		System.out.println(info.getUserName() + ", your appointment time is " + getAppointment(cal));
	}
	
	/** Function that cancels a customer's appointment */
	public void cancelAppointment() {
		
		//Code this
	}
}
