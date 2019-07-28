import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class BarbershopMainUI extends javax.swing.JFrame {
/* 
 * Will have to fetch the tree, or pass the tree, or some other means of using the tree
 * To search for the username entered by the user, to add barber or create new barber. 
 */
	static BarberShop barberShop;
	static CustomerInfo customerOrBarber;
	static Tree infoTree;
	
	javax.swing.JButton barberButton, backButton, barberMakeButton, barberAddButton, customerAppointmentsButton, customerWaitlistButton,
				signoutButton, customerMenuButton, addButton, makeButton;
	javax.swing.JPanel buttonsPanel, bottomPanel, mainPanel, customerMenuPanel, addBarberPanel, showAppointmentPanel,
				showWaitlistPanel, manageBarberPanel, makeBarberPanel;   
	javax.swing.JTextArea dataReturn;
	javax.swing.JLabel nameLabel;
    javax.swing.JTextField userText;
    java.awt.GridBagConstraints pos;

    

public JPanel barbershopMainPanel() {
		
		mainPanel = new JPanel();
				
		nameLabel = new JLabel(barberShop.shopInfo.getUserName()); // name of barbershop
		nameLabel.setFont(new Font("Tahoma",0 , 15));
		mainPanel.add(nameLabel);

		buttonsPanel = new JPanel(new GridBagLayout());
		barberButton = new JButton("Manage Barbers");
		barberButton.addActionListener(new ActionListener() {
			
			//This will remove the current panel being displayed and create a panel to be displayed on the frame
			public void actionPerformed(ActionEvent e) {			
				mainPanel.removeAll();
				JPanel panel = ManageBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		
		customerMenuButton = new JButton("Manage Customers");
		customerMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				mainPanel.removeAll();
				JPanel panel = CustomerMenuPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
			
		//Used to position buttons
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);
		
		buttonsPanel.add(customerMenuButton, pos);
		buttonsPanel.add(barberButton, pos);

		mainPanel.add(buttonsPanel);

		return mainPanel;
	}
                                            
	public JPanel CustomerMenuPanel() {
		customerMenuPanel = new JPanel();
		/*
		 * 	nameLabel = new JLabel(barberShop.shopInfo.getUserName()); // name of barbershop
		 *	nameLabel.setFont(new Font("Tahoma",0 , 15));
		 * 
		 */		
		customerMenuPanel.add(nameLabel);
		buttonsPanel = new JPanel(new GridBagLayout());
		customerAppointmentsButton = new JButton("show today's Appointments"); // Display the day's appointments
		customerAppointmentsButton.addActionListener(new ActionListener() {
			
			//This will remove the current panel being displayed and create a panel to be displayed on the frame
			public void actionPerformed(ActionEvent e) {			
				customerMenuPanel.removeAll();
				JPanel panel = showAppointmentPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}	
		});
		
		customerWaitlistButton = new JButton("Show current Waitlist"); // Display the waitlist contents
		customerWaitlistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerMenuPanel.removeAll();
				JPanel panel = showWaitlistPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				customerMenuPanel.removeAll();
				JPanel panel = barbershopMainPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();

			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);	
		buttonsPanel.add(customerAppointmentsButton, pos);
		buttonsPanel.add(customerWaitlistButton, pos);
		buttonsPanel.add(backButton, pos);
		customerMenuPanel.add(buttonsPanel);
		
		return customerMenuPanel;
	}
	public JPanel showWaitlistPanel() {
		showWaitlistPanel = new JPanel();
		dataReturn = new JTextArea();	
		dataReturn.setText(barberShop.shopInfo.currentList.getCurrentWaitlist());
		
		buttonsPanel = new JPanel(new GridBagLayout());
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				showWaitlistPanel.removeAll();
				JPanel panel = CustomerMenuPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();

			}
		});
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);	
		buttonsPanel.add(backButton, pos);
		showWaitlistPanel.add(buttonsPanel);
		showWaitlistPanel.add(dataReturn);  // adding String current Waitlist
		
		return showWaitlistPanel;
	}

	public JPanel showAppointmentPanel() {
		showAppointmentPanel = new JPanel();
		dataReturn = new JTextArea();	
		//dataReturn.setText(barberShop.shopInfo.getAppointments());
		dataReturn.setText("Apointments Sseete");
		buttonsPanel = new JPanel(new GridBagLayout());
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				showAppointmentPanel.removeAll();
				JPanel panel = CustomerMenuPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();

			}
		});
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);	
		buttonsPanel.add(backButton, pos);
		showAppointmentPanel.add(buttonsPanel);
		showAppointmentPanel.add(dataReturn);  // adding string current Appointments
		
		return showAppointmentPanel;
	}

	public JPanel ManageBarberPanel() {
		manageBarberPanel = new JPanel();
		manageBarberPanel.add(nameLabel);
		
		buttonsPanel = new JPanel(new GridBagLayout());
		barberMakeButton = new JButton("Make a Barber and Add to Staff"); 
		barberMakeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {			
				manageBarberPanel.removeAll();
				JPanel panel = MakeBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}	
		});
		
		barberAddButton = new JButton("Add a Barber to Staff"); 
		barberAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageBarberPanel.removeAll();
				JPanel panel = AddBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				manageBarberPanel.removeAll();
				JPanel panel = CustomerMenuPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();

			}
		});
		
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);	
		buttonsPanel.add(barberMakeButton, pos);
		buttonsPanel.add(barberAddButton, pos);
		buttonsPanel.add(backButton, pos);
		customerMenuPanel.add(buttonsPanel);	
		return manageBarberPanel;
	}

	protected JPanel AddBarberPanel() {
		addBarberPanel = new JPanel();
		addBarberPanel.add(nameLabel);
		
		addButton = new JButton("Add"); //Add them
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = dataReturn.getText();
				barberShop.addBarberToStaff(infoTree.returnInfo(userName));
				addBarberPanel.removeAll();
				JPanel panel = ManageBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {				
				addBarberPanel.removeAll();
				JPanel panel = ManageBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);	
		buttonsPanel.add(addButton, pos);
		buttonsPanel.add(backButton, pos);
		dataReturn.setText("Enter userName of barber to add");
		addBarberPanel.add(dataReturn);
		addBarberPanel.add(buttonsPanel);
		return addBarberPanel;
	}

	protected JPanel MakeBarberPanel() {
		makeBarberPanel = new JPanel();
		makeBarberPanel.add(nameLabel);
		
		makeButton = new JButton("Make");
		makeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = dataReturn.getText();
		    	customerOrBarber = infoTree.returnInfo(userName);
		    	barberShop.addBarberToStaff(customerOrBarber); 
				addBarberPanel.removeAll();
				JPanel panel = ManageBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {				
				addBarberPanel.removeAll();
				JPanel panel = ManageBarberPanel();
				add(panel);
				panel.repaint();
				panel.revalidate();
			}
		});
		pos = new GridBagConstraints();
		pos.insets = new Insets(40,40,40,40);	
		buttonsPanel.add(addButton, pos);
		buttonsPanel.add(backButton, pos);
		dataReturn.setText("Enter userName of barber to add");
		addBarberPanel.add(dataReturn);
		addBarberPanel.add(buttonsPanel);
		
		return makeBarberPanel;
	}

	public BarbershopMainUI(BarberShop barberShop) {
		this.barberShop = barberShop;
		JPanel panel = barbershopMainPanel();
		add(panel);

		//Interface of the GUI
		setSize(900, 500);
		setTitle("Barbershop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public BarbershopMainUI(BarberShopInfo barberShop) {
		this.barberShop = new BarberShop(barberShop);
		JPanel panel = barbershopMainPanel();
		add(panel);

		//Interface of the GUI
		setSize(900, 500);
		setTitle("Barbershop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}


