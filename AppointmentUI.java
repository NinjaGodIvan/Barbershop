import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AppointmentUI extends JFrame{
	
	JPanel MainMenu_panel, MakeAppointment_panel, CheckAppointment_panel, CancelAppointment_panel, InvalidInputMessage_panel, Success_panel, AreYouSure_panel;
	JPanel button_panel; //Used to group multiple buttons to a panel
	GridBagConstraints pos; //Used to position JComponents
	Customer customer; //Needs customer object to make appointment and display customer's username
	String BarberShopSelected;
	GetServer server;
	
	//1. Main Menu Panel (Start Panel too!)
	public JPanel MainMenuPanel() {
		
		//Uses Border Layout to customize position of the JComponents
		MainMenu_panel = new JPanel(new BorderLayout());
				
		//Message Display
		JLabel message = new JLabel("Hello " + customer.custInfo.getUserName() +"! Select your option:");
		message.setFont(new Font("Arial", Font.BOLD, 20));
		message.setBounds(260, -150, 900, 500); 
		MainMenu_panel.add(message);
		
		//Three buttons (Make Appointment, Check Appointment, Cancel Appointment)
		JButton makeAppointment_button, checkAppointment_button, cancelAppointment_button; 

		button_panel = new JPanel(new GridBagLayout());
		
		//Goes to the Make Appointment option
		makeAppointment_button = new JButton("Make Appointment");
		makeAppointment_button.addActionListener(new ActionListener() {
			
			//This will remove the current panel being displayed and create a panel to be displayed on the frame
			public void actionPerformed(ActionEvent e) {
				MakeBarberButtonPanelInterFace temp = new MakeBarberButtonPanelInterFace(customer);
				MainMenu_panel.removeAll(); //Removes all components of the current panel
				JPanel panel = MakeAppointmentPanel(); //Calls a function to return a panel to add to the frame
				add(panel); //Switches to another panel
				panel.repaint();
				panel.revalidate();
			}
		});
		
		//Goes to the Check Appointment option
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
		
		//Goes to the Cancel Appointment option
		cancelAppointment_button = new JButton("Cancel Appointment");
		cancelAppointment_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(customer.appt != null) {
					//Should go to the Are You Sure Panel
					MainMenu_panel.removeAll();
					JPanel panel = AreYouSurePanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}else {
					//Should go to the Cancel Appointment panel and display an error message
					MainMenu_panel.removeAll();
					JPanel panel = CancelAppointmentPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			}
		});
		
		//Used to position buttons
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);
		
		button_panel.add(makeAppointment_button, pos);
		button_panel.add(checkAppointment_button, pos);
		button_panel.add(cancelAppointment_button, pos);
		MainMenu_panel.add(button_panel);
		
		MainMenu_panel.setBounds(0, 0, 900, 500);//Centers the panel
		setLayout(null); //Used to customize how the components should be positioned

		return MainMenu_panel;
	}
	//2. Make Appointment Panel
	public JPanel MakeAppointmentPanel() {
		
		//Centers the panel 
		JPanel adjustPanel = new JPanel(new BorderLayout());
		
		pos = new GridBagConstraints();
		
		/*
		 * If customer's appointment has not been made, display an appointment form.
		 * Otherwise, notify them that they made one already. 
		 */
		if(customer.appt == null) {
			
			//Properly positions the JComponents of text fields, labels, and buttons
			MakeAppointment_panel = new JPanel(new GridBagLayout());
		
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
							// Vlad is the writer*************************
							BarberShopInfo temp = server.getBarberShopInfo(BarberShopSelected);
							System.out.println("Info sent to " + temp.getUserName());
							temp.giveAppointment(customer.appt);
							server.giveBarberShopInfo(temp);
							// *********************************************
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
			adjustPanel.add(MakeAppointment_panel);
		}else {
			
			JPanel errorPanel = new JPanel(new BorderLayout());
			JLabel errorText = new JLabel("You have already made your appointment. Please cancel your appointment to do this.");
			errorText.setFont(new Font("Arial", Font.BOLD, 20));
			errorText.setBounds(40, -150, 900, 500); //Add
			errorPanel.add(errorText);
			
			button_panel = new JPanel(new GridBagLayout());
			JButton MainMenuButton = new JButton("Main Menu");

			pos.gridx = 0; 
			pos.gridy = 0; 
			
			MainMenuButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					errorPanel.removeAll();
					JPanel panel = MainMenuPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
			
			
			button_panel.add(MainMenuButton, pos);
			errorPanel.add(button_panel);
			adjustPanel.add(errorPanel);
		}
		
		adjustPanel.setBounds(0,0,900,500); //Add
		setLayout(null); //Add
		
		return adjustPanel;
	}
	
	//3. Check Appointment Panel (Have customer check their appointment date and time)
	public JPanel CheckAppointmentPanel() {
		
		CheckAppointment_panel = new JPanel(new BorderLayout());
		
		if(customer.appt != null) {
			//Lets the customer know the date of their appointment
			JLabel checkText = new JLabel(customer.appt.checkAppointment(customer.custInfo));
			checkText.setFont(new Font("Arial", Font.BOLD, 20));
			checkText.setBounds(130, -150, 900, 500);
			CheckAppointment_panel.add(checkText);
			
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
			pos.insets = new Insets(40,40,40,40);
			button_panel.add(MainMenuButton);
			CheckAppointment_panel.add(button_panel);
		}else {
			
			JLabel errorText = new JLabel("Unable to check your appointment. Would you want to create one?");
			errorText.setFont(new Font("Arial", Font.BOLD, 20));
			CheckAppointment_panel.add(errorText);
			errorText.setBounds(120, -150, 900, 500); //Add
			
			button_panel = new JPanel(new GridBagLayout());
			
			JButton yes = new JButton("Yes");
			yes.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					CheckAppointment_panel.removeAll();
					JPanel panel = MakeAppointmentPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
			
			JButton no = new JButton("No");
			no.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					CheckAppointment_panel.removeAll();
					JPanel panel = MainMenuPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
			
			pos = new GridBagConstraints();
			pos.insets = new Insets(40,40,40,40);
			
			button_panel.add(yes, pos);
			button_panel.add(no, pos);
			CheckAppointment_panel.add(button_panel);
		}
		
		CheckAppointment_panel.setBounds(0, 0, 900, 500);
		setLayout(null);
		return CheckAppointment_panel;
	}
	
	//4. Cancel Appointment Panel (Have customer cancel the appointment)
	public JPanel CancelAppointmentPanel() {
		
		CancelAppointment_panel = new JPanel(new BorderLayout());
		
		//If customer's appointment is created, display a successful message, otherwise tell them that it has not been made.
		if(customer.appt != null) {
			JLabel cancelText = new JLabel("You have successfully cancelled your appointment!");
			cancelText.setFont(new Font("Arial", Font.BOLD, 20));
			cancelText.setBounds(200, -150, 900, 500);
			CancelAppointment_panel.add(cancelText);
			
			//Cancels the appointment
			customer.appt.cancelAppointment(customer);
			
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
			CancelAppointment_panel.add(button_panel);
		} else {
			
			JLabel text = new JLabel("Unable to cancel your appointment. Would you want to create one?");
			text.setFont(new Font("Arial", Font.BOLD, 20));
			text.setBounds(130, -150, 900, 500);
			CancelAppointment_panel.add(text);
			
			button_panel = new JPanel(new GridBagLayout());
			JButton yes = new JButton("Yes");
			yes.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					CancelAppointment_panel.removeAll();
					JPanel panel = MakeAppointmentPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
			
			JButton no = new JButton("No");
			no.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					CancelAppointment_panel.removeAll();
					JPanel panel = MainMenuPanel();
					add(panel);
					panel.repaint();
					panel.revalidate();
				}
			});
			
			pos = new GridBagConstraints();
			pos.insets = new Insets(40,40,40,40);
			button_panel.add(yes, pos);
			button_panel.add(no, pos);
			CancelAppointment_panel.add(button_panel);

		}
		
		CancelAppointment_panel.setBounds(0, 0, 900, 500);
		setLayout(null); //Added

		return CancelAppointment_panel;
	}
	//5. Invalid Input Message Panel (If the user did entered a text or not enter anything on the required fields, go to this panel)
	public JPanel InvalidMessagePanel() {
		
		InvalidInputMessage_panel = new JPanel(new BorderLayout());
		JLabel invalidText = new JLabel("You have either enter a text or did not enter anything on all required fields. Unable to create your appointment :(");
		invalidText.setFont(new Font("Arial", Font.BOLD, 16));
		invalidText.setBounds(30, -150, 900, 500);
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
		InvalidInputMessage_panel.add(button_panel);
		
		setLayout(null);
		InvalidInputMessage_panel.setBounds(0, 0, 900, 500);
		
		return InvalidInputMessage_panel;
	}
	
	//6. Invalid Input Message Panel 2 (If the user enters invalid date fields [e.g. Day: 31, Month: 15], go to this panel)
	public JPanel InvalidMessagePanel2(String err) {
		
		InvalidInputMessage_panel = new JPanel(new BorderLayout());
		
		JLabel invalidText = new JLabel(err);
		invalidText.setFont(new Font("Arial", Font.BOLD, 20));
		invalidText.setBounds(100, -150, 900, 500);
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
		pos.insets = new Insets(40,40,40,40);
		button_panel.add(MainMenuButton, pos);
		InvalidInputMessage_panel.add(button_panel);
		
		InvalidInputMessage_panel.setBounds(0, 0, 900, 500);
		setLayout(null);
		
		return InvalidInputMessage_panel;
	}
	
	//7. Success Panel (If the user successfully create an appointment, go to this panel).
	public JPanel SuccessPanel1() {
		 
		Success_panel = new JPanel(new BorderLayout()); //Add
		JLabel successText = new JLabel("You have successfully made your appointment! You may go back to the main menu :)");
		successText.setFont(new Font("Arial", Font.BOLD, 20));
		successText.setBounds(40, -150, 900, 500);
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
		Success_panel.add(button_panel);
		
		setLayout(null);
		Success_panel.setBounds(0, 0, 900, 500);
		
		return Success_panel;
	}
		
	//8. Are You Sure Panel (Checks if the customer is sure to remove the appointment)
	public JPanel AreYouSurePanel() {
		
		AreYouSure_panel = new JPanel(new BorderLayout()); //Add
		
		JLabel text = new JLabel("Do you want to cancel your appointment?");
		text.setFont(new Font("Arial", Font.BOLD, 20));
		text.setBounds(250, -150, 900, 500);
		AreYouSure_panel.add(text);
		
		button_panel = new JPanel(new GridBagLayout());
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);
		
		//If clicked yes, go to the next message
		JButton yes = new JButton("Yes");
		yes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//Should go to the Cancel Appointment Panel to remove appointment and display a successful message
				AreYouSure_panel.removeAll();
				JPanel panel = AreYouSurePanel2();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		
		//Else, go back to the main menu
		JButton no = new JButton("No");
		no.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				AreYouSure_panel.removeAll();
				JPanel panel = MainMenuPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		
		button_panel.add(yes, pos);
		button_panel.add(no, pos);
		AreYouSure_panel.add(button_panel);
		
		AreYouSure_panel.setBounds(0, 0, 900, 500);
		setLayout(null);

		return AreYouSure_panel;
	}
	
	//9. Are You Sure Panel 2 (Check one more time with the customer, if they are really sure)
	public JPanel AreYouSurePanel2() {
		
		JPanel AreYouSure2_panel = new JPanel(new BorderLayout());
		
		JLabel text = new JLabel("There is no going back after. Are you really sure about it?");
		text.setFont(new Font("Arial", Font.BOLD, 20));
		text.setBounds(170, -150, 900, 500);
		AreYouSure2_panel.add(text);
		
		button_panel = new JPanel(new GridBagLayout());
				
		//If clicked yes, cancel the appointment
		JButton yes = new JButton("Yes I'm Very Sure!");
		yes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//Should go to the Cancel Appointment Panel to remove appointment and display a successful message
				AreYouSure2_panel.removeAll();
				JPanel panel = CancelAppointmentPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		
		//Else, go back to the main menu
		JButton no = new JButton("No, Keep My Appointment");
		no.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				AreYouSure2_panel.removeAll();
				JPanel panel = MainMenuPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);
		
		button_panel.add(yes, pos);
		button_panel.add(no, pos);
		AreYouSure2_panel.add(button_panel);
		
		AreYouSure2_panel.setBounds(0, 0, 900, 500);
		setLayout(null);

		return AreYouSure2_panel;
	}
	
	//Opens an Appointment window
	public AppointmentUI(Customer cust) {
		
		//Gets customer's account and info to make appointment
		this.customer = cust; 

		//Program starts with the main menu panel
		JPanel startPanel = MainMenuPanel();
		add(startPanel);

		//Interface of the GUI
		setSize(900, 500);
		setTitle("Appointment Set Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// Vlad

	class MakeBarberButtonPanelInterFace extends JFrame {
		Customer customer;	
		BarberButtonPanel buttonPanel;
		
		MakeBarberButtonPanelInterFace(Customer customer) {
			this.customer = customer;
			setTitle("Appts");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());	
			buttonPanel = new BarberButtonPanel(customer.getCustomerInfo().barberShopList,this);
		//	int size = buttonPanel.makeBarberList();
			add(buttonPanel);
			setSize(160, 400);			
			setVisible(true);
			revalidate();
			repaint();
	//        update();        
		}
	}	
	
	class BarberButtonPanel extends JPanel {
		private JButton[] barberShops;		
		JPanel buttonPanel;
		BarberShopInfo[] barberList;	
		String[] barberShopNames;
		MakeBarberButtonPanelInterFace hold;
		
		BarberButtonPanel(BarberShopInfo[] barberList, MakeBarberButtonPanelInterFace temp) {
			this.barberList = barberList;
			this.makeBarberList();
			this.hold = temp;
		}
		
		public int makeBarberList() {
				buttonPanel = new JPanel();			
				
				int size = 0;
				for (BarberShopInfo b : barberList) {
					size++;
				}
				if (size > 0) {
					setLayout(new GridLayout(1,size));	
					setBorder(BorderFactory.createTitledBorder("select Shop"));					
					
					barberShops = new JButton[size];
					barberShopNames = new String[size];

					for (int i = 0; i < size; i++) {
						barberShops[i] = new JButton(barberList[i].getUserName());
						barberShops[i].setPreferredSize(new Dimension(150, 30));				
						barberShops[i].addActionListener(new aButtonHandler(barberList[i].getUserName()));
						buttonPanel.add(barberShops[i]);				
					}		
					if (size == 0) {
						
					}
					
					add(buttonPanel);		
		//			setSize(150, (size*15));	
				 } else {
					hold.dispose();			// if no barbers terminate		 
				 }
				return size;			
		}	
		private class aButtonHandler implements ActionListener{
			private String buttonName;
			aButtonHandler(String buttonName) {
				this.buttonName = buttonName;
			}
			
			public void actionPerformed(ActionEvent e) {
				BarberShopSelected = buttonName;
				hold.dispose();
			}
		}		
	}
	
	
	
	
}
