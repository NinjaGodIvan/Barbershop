//package barber;
import java.util.Calendar;

public class Appointment {
	Calendar date;
	CustomerInfo customer;
	int am_pm; //1. AM; 2. PM
	
	/** Function that adds a customer's appointment */
	public String makeAppointment(Customer customer, CustomerInfo customerInfo, int day, int month, int year, int min, int hour, int am_pm) {				

		/* Compare non-Calendar variables */
		if(month < 1 || month > 12) {
			return "You must pick a month between 1 to 12. Deleting your Appointment time :(";
		}
		if(min < 0 || min > 59) {
			return "You must pick a minute between 0 to 59. Deleting your Appointment time :(";
		}
		if(hour < 1 || hour > 12) {
			return "You must pick an hour between 1 to 12. Deleting your Appointment time :(";
		}
		
		//Sets the customer's appointment date and time 
		Calendar appointment = Calendar.getInstance();

		//Sets current date and time
		Calendar curr = Calendar.getInstance();
										
		//max_day: Last day of a specific month; least_year: returns the current year
		String max_day, curr_year;
		
		//Sets AM or PM
		this.am_pm = am_pm;
		
		/*
		 * Converts the time format from 24-hour to 12-hour to set appointment
		 * If-block: Sets hour from 12:00AM to 11:00AM; Else-block: Sets hour from 12:00PM to 11:00PM
		 */
		if(am_pm == 1) {
			if(hour == 12) //Sets to 12:00AM and appointment hour to be the first hour of the day
				appointment.set(year, month - 1, day, 0, min, 0);
		}
		else {
			//Sets from 1:00PM to 11:00PM -> e.g: hour = 1 is 1:00AM and hour = 13 is 1:00PM; If this condition is false, then hour is set to 12:00PM
			if(hour >= 1 && hour <= 11) 
				hour += 12;
		}
		
		appointment.set(year, month - 1, day, hour, min, 0);
		System.out.println("Appointment Date: " + appointment.getTime() + "\n"); //Debugging purposes

		//If day is less than 1 or day is greater than the least maximum day from a specified month (February 28), go to this block
		if(day < 1 || day > 28) { 
			//If day is less than 1 or day is greater than the most maximum day from specified months, then go to this block
			if(day < 1 || day > 31)
				return "Please pick a day from 1 to 31. Deleting your appointment time :(";
				
			/*
			 * Goes back to previous month to prevent from going to the next month 
			 * If user puts November 32, then it will set to December 2 instead
			 * This function will go back to the previous month -> December 2 to November 2
			 */
			appointment.add(Calendar.MONTH, -1);	
			System.out.println("Appointment Date: " + appointment.getTime() + "\n"); //Debugging purposes
			
			//If the day is greater than the last day of the month, go to this block
			if(day > appointment.getActualMaximum(Calendar.DAY_OF_MONTH)){
				//Gets the last day of the month
				max_day = Integer.toString(appointment.getActualMaximum(Calendar.DAY_OF_MONTH));
				return "You must pick a day between 1 to " + max_day + " in this month. Deleting your Appointment time :(";
			}
			
			//Revert the appointment time
			appointment.add(Calendar.MONTH, 1);
			appointment.set(year, month - 1, day, hour, min, 0);
			System.out.println("Appointment Date: " + appointment.getTime() + "\n"); //Debugging purposes
		}
		if(year < curr.get(Calendar.YEAR)) {
			curr_year = Integer.toString(curr.get(Calendar.YEAR) - 1);
			return "You must pick a year after " + curr_year + ". Deleting your Appointment time :(";
		}
		if(appointment.getTimeInMillis() < curr.getTimeInMillis()){
			return "You have enter a date or time that has passed. Deleting your Appointment time :(";
		}
		
		//Goes to the next month for the correct appointment's month
		this.date = appointment; // assign appointment to the date of appointment
		this.customer = customerInfo; // assigning customer to appointment
		return "No Error";
		
	}
	
	/** Function that gets the customer's appointment */
	public String getAppointment() {
		
		int day, monthInInt, year, min, hour;
		String month = "January";
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		day = this.date.get(Calendar.DATE);
		monthInInt = this.date.get(Calendar.MONTH) + 1;
		year = this.date.get(Calendar.YEAR);
		min = this.date.get(Calendar.MINUTE);
		hour = this.date.get(Calendar.HOUR_OF_DAY);	
				
		if(am_pm == 1) {
			if(hour == 0)
				hour = 12; //Displays as 12:00AM instead of 0:00AM
		}else {
			
			/*
			 * Converts time format from 24-hour to 13-hour, e.g: 13:00 is 1:00PM
			 * If hour == 12, do nothing
			 */
			if(hour >= 13 && hour <= 23) 
				hour -= 12;
		}
			
		
		for(int a = 0; a < months.length; a++)
			if(a == (monthInInt - 1)) {
				month = months[monthInInt - 1];
			}
		
		if(am_pm == 1) {
			//adds 0 and the minute 
			if(min >= 0 && min < 10)
				return month + " " + day + ", " + year + " at " + hour + ":0" + min + "AM";
			else
				return month + " " + day + ", " + year + " at " + hour + ":" + min + "AM";
		}
		else {
			if(min >= 0 && min < 10)
				return month + " " + day + ", " + year + " at " + hour + ":0" + min + "PM";
			else
				return month + " " + day + ", " + year + " at " + hour + ":" + min + "PM";		}
	}
	
	/** Function that outputs the customer's appointment date */
	public String checkAppointment(CustomerInfo info) {
		 return info.getUserName() + ", your appointment time is " + getAppointment();
	}
	
	/** Function that cancels a customer's appointment */
	public void cancelAppointment(Customer cust) {
		cust.appt = null;
	}
	
	@Override
	public String toString() {
		return "Appointment [date=" + getAppointment() + ", customer=" + customer + "]";
	}
}

