//Upload this
package barber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AppointmentUI extends JFrame{
	
	JPanel MainMenu_panel, MakeAppointment_panel, CheckAppointment_panel, CancelAppointment_panel, InvalidInputMessage_panel, Success_panel;
	JPanel button_panel;
	JButton makeAppointment_button, checkAppointment_button, cancelAppointment_button;
	GridBagConstraints pos;
	
	/* Testing Customer object */
	Customer customer;
	
	//1. Main Menu Panel (Start Panel too!)
	public JPanel MainMenuPanel() {
		
		MainMenu_panel = new JPanel();
		
		//Message Display
		JLabel message = new JLabel("Hello " + customer.custInfo.getUserName() +"! Select your option:");
		message.setFont(new Font("Arial", Font.BOLD, 30));
		MainMenu_panel.add(message);
		
		//Three buttons (Make Appointment, Check Appointment, Cancel Appointment)
		button_panel = new JPanel(new GridBagLayout());
		makeAppointment_button = new JButton("Make Appointment");
		makeAppointment_button.addActionListener(new ActionListener() {
			
			//This will remove the current panel being displayed and create a panel to be displayed on the frame
			public void actionPerformed(ActionEvent e) {
				MainMenu_panel.removeAll();
				JPanel panel = MakeAppointmentPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		
		checkAppointment_button = new JButton("Check Appointment");
		checkAppointment_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				MainMenu_panel.removeAll();
				JPanel panel = CheckAppointmentPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();

			}
		});
		
		cancelAppointment_button = new JButton("Cancel Appointment");
		cancelAppointment_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				MainMenu_panel.removeAll();
				JPanel CancelAppointment_panel = CancelAppointmentPanel();
				add(CancelAppointment_panel);
				CancelAppointment_panel.repaint();
				CancelAppointment_panel.revalidate();

			}
		});
		
		//Used to position buttons
		pos = new GridBagConstraints();
		pos.insets = new Insets(50,50,50,50);
		
		button_panel.add(makeAppointment_button, pos);
		button_panel.add(checkAppointment_button, pos);
		button_panel.add(cancelAppointment_button, pos);
		MainMenu_panel.add(button_panel);

		return MainMenu_panel;
	}
	//2. Make Appointment Panel
	public JPanel MakeAppointmentPanel() {
		
		MakeAppointment_panel = new JPanel(new GridBagLayout());
		pos = new GridBagConstraints();
		
		//If customer's appointment has not been made, create it for the customer
		if(customer.appt == null) {
		
			JLabel monthText = new JLabel("Enter Month:");
			pos.gridx = 0;
			pos.gridy = 0;
			MakeAppointment_panel.add(monthText, pos);
		
			JTextField monthField = new JTextField();
			pos.gridx = 1;
			pos.gridy = 0;
			monthField.setPreferredSize(new Dimension(100,30));
			MakeAppointment_panel.add(monthField, pos);
		
			JLabel dayText = new JLabel("Enter Day:");
			pos.gridx = 0;
			pos.gridy = -1;
			MakeAppointment_panel.add(dayText,pos);
		
			JTextField dayField = new JTextField();
			pos.gridx = 1;
			pos.gridy = -1;
			dayField.setPreferredSize(new Dimension(100,30));
			MakeAppointment_panel.add(dayField,pos);
		
			JLabel yearText = new JLabel("Enter Year:");
			pos.gridx = 0;
			pos.gridy = -2;
			MakeAppointment_panel.add(yearText,pos);
			pos.gridx = 1;
			pos.gridy = -2;
			JTextField yearField = new JTextField();
			yearField.setPreferredSize(new Dimension(100,30));
			MakeAppointment_panel.add(yearField,pos);
		
			JLabel hourText = new JLabel("Enter Hour:");
			pos.gridx = 0;
			pos.gridy = -3;
			MakeAppointment_panel.add(hourText,pos);
			JTextField hourField = new JTextField();
			pos.gridx = 1;
			pos.gridy = -3;
			hourField.setPreferredSize(new Dimension(100,30));
			MakeAppointment_panel.add(hourField,pos);
		
			JLabel minuteText = new JLabel("Enter Minute:");
			pos.gridx = 0;
			pos.gridy = -4;
			MakeAppointment_panel.add(minuteText,pos);
			JTextField minuteField = new JTextField();
			pos.gridx = 1;
			pos.gridy = -4;
			minuteField.setPreferredSize(new Dimension(100,30));
			MakeAppointment_panel.add(minuteField,pos);
		
			ButtonGroup AM_PM_Buttons = new ButtonGroup();

			JRadioButton AM_Button = new JRadioButton("AM");
			pos.gridx = 0;
			pos.gridy = -5;
			AM_PM_Buttons.add(AM_Button);
			MakeAppointment_panel.add(AM_Button,pos);
			JRadioButton PM_Button = new JRadioButton("PM");
			pos.gridx = 1;
			pos.gridy = -5;
			AM_PM_Buttons.add(PM_Button);
			MakeAppointment_panel.add(PM_Button,pos);
			
			JButton submitButton = new JButton("Submit");
			pos.gridx = 1;
			pos.gridy = -6;
			submitButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					int day, month, year, minute, hour, am_pm;
					
					//Error message if the user enters an invalid value;  
					String error_message = "No Error";
	
					//Makes sure that the user does not enter a non-numerical value and leave the fields blank
					try {
					
						day = Integer.parseInt(dayField.getText());
						month = Integer.parseInt(monthField.getText());
						year = Integer.parseInt(yearField.getText());
						minute = Integer.parseInt(minuteField.getText());
						hour = Integer.parseInt(hourField.getText());
						
						if(AM_Button.isSelected())
							am_pm = 1;
						else if(PM_Button.isSelected())
							am_pm = 2;
						else
							throw new Exception();
						
						//Creates an appointment for the customer
						customer.appt = new Appointment();					
						error_message = customer.appt.makeAppointment(customer, customer.custInfo, day, month, year, minute, hour, am_pm);
						
						//Makes a success or failure panel
						JPanel panel;
						
						//If there is no error, then go to success panel. Otherwise, display an error message instead
						if(error_message == "No Error") {
							
							MakeAppointment_panel.removeAll();
							panel = SuccessPanel1();
							add(panel);
							panel.repaint();
							panel.revalidate();
						}else {
							MakeAppointment_panel.removeAll();
							panel = InvalidMessagePanel2(error_message);
							add(panel);
							panel.repaint();
							panel.revalidate();
						}
					}catch(NumberFormatException exception) { //Catches this exception if the user enter a non-numerical value, double, or text
						//Goes to the error message
						MakeAppointment_panel.removeAll();
						JPanel panel = InvalidMessagePanel();
						add(panel);
						panel.repaint();
						panel.revalidate();
					}catch(Exception exception) { //Catches this exception if the user did not enter AM or PM
						MakeAppointment_panel.removeAll();
						JPanel panel = InvalidMessagePanel();
						add(panel);
						panel.repaint();
						panel.revalidate();
					}
				}
			});
			
			MakeAppointment_panel.add(submitButton,pos);
			
			JButton MainMenuButton = new JButton("Main Menu");
			pos.gridx = 0;
			pos.gridy = -6;
			MainMenuButton.addActionListener(new ActionListener() {
				//Goes back to the main menu
				public void actionPerformed(ActionEvent e) {
					MakeAppointment_panel.removeAll();
					JPanel panel = MainMenuPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
	
			MakeAppointment_panel.add(MainMenuButton,pos);
		}else {
			
			JLabel errorText = new JLabel("You have already made your appointment. Please cancel your appointment to do this.");
			errorText.setFont(new Font("Arial", Font.BOLD, 20));
			MakeAppointment_panel.add(errorText);
			
			JButton MainMenuButton = new JButton("Main Menu");
			pos.gridx = 0;
			pos.gridy = -6;
			
			MainMenuButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					MakeAppointment_panel.removeAll();
					JPanel panel = MainMenuPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
			
			MakeAppointment_panel.add(MainMenuButton,pos);
		}
		
		return MakeAppointment_panel;
	}
	
	//3. Check Appointment Panel (Have customer check their appointment date and time)
	public JPanel CheckAppointmentPanel() {
		
		CheckAppointment_panel = new JPanel();
		
		//If customer's appointment object is made, then go to the if-block. Otherwise, display a different message
		if(customer.appt != null) {
			JLabel checkText = new JLabel(customer.appt.checkAppointment(customer.custInfo));
			checkText.setFont(new Font("Arial", Font.BOLD, 20));
			CheckAppointment_panel.add(checkText);
		}else {
			JLabel errorText = new JLabel("You did not set up your appointment. Go to Make Appointment option to create one.");
			errorText.setFont(new Font("Arial", Font.BOLD, 20));
			CheckAppointment_panel.add(errorText);
		}
		
		button_panel = new JPanel(new GridBagLayout());
		JButton MainMenuButton = new JButton("Main Menu");
		MainMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CheckAppointment_panel.removeAll();
				JPanel MainMenu_panel = MainMenuPanel();
				add(MainMenu_panel);
				MainMenu_panel.repaint();
				MainMenu_panel.revalidate();
			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(50,50,50,50);
		
		button_panel.add(MainMenuButton, pos);

		CheckAppointment_panel.add(MainMenuButton);
		
		return CheckAppointment_panel;
	}
	//4. Cancel Appointment Panel (Have customer cancel the appointment)
	public JPanel CancelAppointmentPanel() {
		
		CancelAppointment_panel = new JPanel();
		
		//If customer's appointment is created, display a successful message, otherwise tell them that it has not been made.
		if(customer.appt != null) {
			JLabel cancelText = new JLabel("You have successfully cancelled your appointment! You can go back now to the main menu.");
			cancelText.setFont(new Font("Arial", Font.BOLD, 20));
			CancelAppointment_panel.add(cancelText);
			customer.appt.cancelAppointment(customer);
		} else {
			JLabel errorText = new JLabel("You did not make an appointment. Unable to cancel your appointment :(");
			errorText.setFont(new Font("Arial", Font.BOLD, 25));
			CancelAppointment_panel.add(errorText);
		}
		
		button_panel = new JPanel(new GridBagLayout());
		JButton MainMenuButton = new JButton("Main Menu");
		MainMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CancelAppointment_panel.removeAll();
				JPanel MainMenu_panel = MainMenuPanel();
				add(MainMenu_panel);
				MainMenu_panel.repaint();
				MainMenu_panel.revalidate();
			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(50,50,50,50);
		button_panel.add(MainMenuButton, pos);
		CancelAppointment_panel.add(MainMenuButton);
		
		return CancelAppointment_panel;
	}
	//5. Invalid Input Message Panel (If the user did entered a text or not enter anything on the required fields, go to this panel)
	public JPanel InvalidMessagePanel() {
		
		InvalidInputMessage_panel = new JPanel();
		JLabel invalidText = new JLabel("You have either enter a text or did not enter anything on all required fields. Unable to create your appointment :(");
		invalidText.setFont(new Font("Arial", Font.BOLD, 16));
		InvalidInputMessage_panel.add(invalidText);
		
		button_panel = new JPanel(new GridBagLayout());
		JButton MainMenuButton = new JButton("Main Menu");
		MainMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				InvalidInputMessage_panel.removeAll();
				JPanel MainMenu_panel = MainMenuPanel();
				add(MainMenu_panel);
				MainMenu_panel.repaint();
				MainMenu_panel.revalidate();
			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(50,50,50,50);
		button_panel.add(MainMenuButton, pos);
		InvalidInputMessage_panel.add(MainMenuButton);
		
		return InvalidInputMessage_panel;
	}
	
	//6. Invalid Input Message Panel 2 (If the user enters invalid date fields [e.g. Day: 31, Month: 15], go to this panel)
	public JPanel InvalidMessagePanel2(String err) {
		
		InvalidInputMessage_panel = new JPanel();
		
		JLabel invalidText = new JLabel(err);
		invalidText.setFont(new Font("Arial", Font.BOLD, 20));
		InvalidInputMessage_panel.add(invalidText);
		customer.appt.cancelAppointment(customer);
		
		button_panel = new JPanel(new GridBagLayout());
		JButton MainMenuButton = new JButton("Main Menu");
		MainMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				InvalidInputMessage_panel.removeAll();
				JPanel MainMenu_panel = MainMenuPanel();
				add(MainMenu_panel);
				MainMenu_panel.repaint();
				MainMenu_panel.revalidate();
			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(50,50,50,50);
		button_panel.add(MainMenuButton, pos);
		InvalidInputMessage_panel.add(MainMenuButton);
		
		return InvalidInputMessage_panel;
	}
	
	
	//7. Success Panel (If the user successfully create an appointment, go to this panel).
	public JPanel SuccessPanel1() {
		 
		
		Success_panel = new JPanel();
		JLabel successText = new JLabel("You have successfully made your appointment! You may go back to the main menu :)");
		successText.setFont(new Font("Arial", Font.BOLD, 20));
		Success_panel.add(successText);
		
		button_panel = new JPanel(new GridBagLayout());
		JButton MainMenuButton = new JButton("Main Menu");
		MainMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Success_panel.removeAll();
				JPanel MainMenu_panel = MainMenuPanel();
				add(MainMenu_panel);
				MainMenu_panel.repaint();
				MainMenu_panel.revalidate();
			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(50,50,50,50);
		button_panel.add(MainMenuButton, pos);
		Success_panel.add(MainMenuButton);
		
		return Success_panel;
	}
		
	
	public AppointmentUI(Customer cust) {
		
		//Gets customer's account and info to make appointment
		this.customer = cust;

		//Program starts with the main menu panel
		JPanel startPanel = MainMenuPanel();
		add(startPanel);

		//Interface of the GUI
		setSize(900, 500);
		setTitle("Ultimate Barbershop App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
