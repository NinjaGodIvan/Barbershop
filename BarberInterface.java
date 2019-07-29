
import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BarberInterface extends JFrame{
	private JButton doneHaircutButton, 
					breakButton, 
					leaveButton, 
					banCustomerButton;
	
	private JTextField waitlistTextField, appointmentsTextField;
	private JLabel titleLabel;
	private JPanel titlePanel,
				   waitlistPanel, 
				   appointmentsPanel, 
				   functionPanel;
	Customer customer;			
//	AppointmentInterFace temp;	   
	
	BarberInterface(Customer customer){
		this.customer = customer;		
		waitlistTextField = new JTextField(30);
		appointmentsTextField = new JTextField(50);
		
		titleLabel = new JLabel("Welcome to BarberShop");
		
		titlePanel = new JPanel();
		waitlistPanel = new JPanel();
		appointmentsPanel = new JPanel();
		functionPanel = new JPanel();
		
		doneHaircutButton = new JButton("Done with haircut");
		breakButton =  new JButton("Take break");
		leaveButton = new JButton("Leave shift");
		banCustomerButton = new JButton("Ban  Customer");
		
		doneHaircutButton.addActionListener(new aButtonHandler());
		breakButton.addActionListener(new aButtonHandler());
		leaveButton.addActionListener(new aButtonHandler());
		banCustomerButton.addActionListener(new aButtonHandler());
		
		setLayout(new BorderLayout());
		
		titlePanel.add(titleLabel);
		
		waitlistPanel.setBorder(BorderFactory.createTitledBorder("Waiting list:"));
		waitlistPanel.setLayout(new BoxLayout(waitlistPanel, BoxLayout.Y_AXIS));
		waitlistPanel.add(waitlistTextField);
		
		appointmentsPanel.setBorder(BorderFactory.createTitledBorder("Appointments list:"));
		appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
		appointmentsPanel.add(appointmentsTextField);
		
		functionPanel.setBorder(BorderFactory.createTitledBorder("Function:"));
		functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.X_AXIS));
		functionPanel.add(doneHaircutButton);
		functionPanel.add(breakButton);
		functionPanel.add(leaveButton);
		functionPanel.add(banCustomerButton);
		
		add(titlePanel, BorderLayout.NORTH);
		add(waitlistPanel, BorderLayout.WEST);
		add(appointmentsPanel, BorderLayout.CENTER);
		add(functionPanel, BorderLayout.SOUTH);
		
		
		setSize(800, 400);
		setTitle("Barber Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private class aButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		//	temp = new AppointmentInterFace(customer);
		}
	}
	
	
	
}
/*

class MakeBarberButtonPanelInterFace extends JFrame {
	Customer customer;	
	BarberButtonPanel buttonPanel;
	
	AppointmentInterFace(Customer customer) {
		this.customer = customer;
		setTitle("Make an Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());	
		buttonPanel = new BarberButtonPanel(customer.getCustomerInfo().barberShopList);
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
	
	BarberButtonPanel(BarberShopInfo[] barberList) {
		this.barberList = barberList;
		this.makeBarberList();
	}
	
	public int makeBarberList() {
			buttonPanel = new JPanel();			
			
			int size = 0;
			for (BarberShopInfo b : barberList) {
				size++;
			}
			setLayout(new GridLayout(1,size));	
			setBorder(BorderFactory.createTitledBorder("BarberShops"));					
			System.out.println(size);
			barberShops = new JButton[size];
			barberShopNames = new String[size];

			for (int i = 0; i < size; i++) {
				barberShops[i] = new JButton(barberList[i].getUserName());
				barberShops[i].setPreferredSize(new Dimension(150, 30));				
				barberShops[i].addActionListener(new aButtonHandler(barberList[i].getUserName()));
				buttonPanel.add(barberShops[i]);				
			}		
			
			add(buttonPanel);
//			setSize(150, (size*15));	
			return size;			
	}	
	private class aButtonHandler implements ActionListener{
		private String buttonName;
		aButtonHandler(String buttonName) {
			this.buttonName = buttonName;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}		
}
*/
