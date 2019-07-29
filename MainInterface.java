import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class MainInterface extends JFrame implements ActionListener{

	//references for the components I need 
	private JButton logInButton,
					signUpButton;
	private JLabel titleLabel;
	private JPanel mainPanel;
	
	public MainInterface(){
	
	logInButton = new JButton("Log In");
	signUpButton = new JButton("Sign Up");
	titleLabel = new JLabel("Please Choosing one: ");
	
	
	mainPanel = new JPanel();
	mainPanel.add(titleLabel);
	mainPanel.add(signUpButton);
	mainPanel.add(logInButton);

	add(mainPanel);
	
	//add button to addActionListener
	logInButton.addActionListener(this);
	signUpButton.addActionListener(this);
	//set frame size
	setSize(400, 400);
	//set title
	setTitle("Welcome to Barber Shop  !!!");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	}
	// create new object
	public static void main(String[] args) {
		new MainInterface();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logInButton) {
			this.dispose();		//Destroy the current frame
			new logInJFrame();   //create new frame
		}
		
		if(e.getSource() == signUpButton) {
			this.dispose();
			new signUpJFrame();
		}
	}
}

class logInJFrame extends JFrame implements ActionListener{
	//references for the components I need 
	private JButton loginAsCustomerButton,
					loginAsBarberButton,
					loginAsBarberShopButton,
					backButton;
					
	
	private JPanel logInPanel;
	private JLabel titleLabel;
		public logInJFrame() {
			loginAsCustomerButton  = new JButton("log in As Customer");
			loginAsBarberButton  = new JButton("log in As Barber");
			loginAsBarberShopButton = new JButton("log in As BarberShop");
			backButton = new JButton("back");
			
			titleLabel = new JLabel("Choose");
			
			logInPanel = new JPanel();
			
			logInPanel.add(titleLabel);
			logInPanel.add(loginAsBarberButton);
			logInPanel.add(loginAsCustomerButton);
			logInPanel.add(loginAsBarberShopButton);
			logInPanel.add(backButton);
			
			loginAsCustomerButton.addActionListener(this);
			loginAsBarberButton.addActionListener(this);
			loginAsBarberShopButton.addActionListener(this);
			backButton.addActionListener(this);
			
			
			add(logInPanel);
			
			setSize(400, 400);  //set size
			setTitle("Welcome to Log in  !");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
		}
		public static void main(String[] args) {
				
			new logInJFrame(); //new object
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backButton) {
				this.dispose();    //Destroy the current frame
				new MainInterface();	// back to the main meau
			}	
			
			if(e.getSource() == loginAsCustomerButton) {
				this.dispose();
				new customerlogFrame();// go to the customer log in meau
			}	
			if(e.getSource() == loginAsBarberButton) {
				this.dispose();
				new barberlogFrame();// go to the barber log in meau
			}	
			if(e.getSource() == loginAsBarberShopButton) {
				this.dispose();
				new barberShoplogFrame();// go to the barbershop log in meau
			}	
			
			}			
			
}
	


// the sign up JFrame
class signUpJFrame extends JFrame implements ActionListener{
	private JButton signUpAsCustomerButton,
					signUpAsBarberButton,
					signUpAsBarberShopButton,
					backButton;
	
	private JPanel signUpPanel;
	private JLabel titleLabel;
	
	public signUpJFrame() {
		signUpAsCustomerButton = new JButton("sign Up As Customer");
		signUpAsBarberButton = new JButton("sign Up As Barber");
		signUpAsBarberShopButton = new JButton("sign Up As BarberShop");
		titleLabel = new JLabel("Choose");
		signUpPanel = new JPanel();
		backButton = new JButton("back");
		
		signUpPanel.add(titleLabel);
		signUpPanel.add(signUpAsCustomerButton);
		signUpPanel.add(signUpAsBarberButton);
		signUpPanel.add(signUpAsBarberShopButton);
		signUpPanel.add(backButton);
		//add ActionListener
		signUpAsCustomerButton.addActionListener(this);
		signUpAsBarberButton.addActionListener(this);
		signUpAsBarberShopButton.addActionListener(this);
		backButton.addActionListener(this);
		add(signUpPanel);
		setSize(400, 400);
		setTitle("Welcome to sign up  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
			// create new object
		new signUpJFrame();
	}
		//  .....
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			this.dispose();
			new MainInterface();  //back to Main meau
		}
		if(e.getSource() == signUpAsBarberButton) {
			this.dispose();
			new barberSignFrame();  // go to barber sign up frame
		}
		
		if(e.getSource() == signUpAsCustomerButton) {
			this.dispose();
			new customerSignFrame();    // go to customer sign up frame
		}
		if(e.getSource() == signUpAsBarberShopButton) {
			this.dispose();
			new barberShopSignFrame();	// go to barbershop sign up frame
		}
		
		
	}
	
}


// the customer lig in frame
class customerlogFrame extends JFrame implements ActionListener{
	private JButton LogInButton,backButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel logInPanel;
	GetServer getServer = new GetServer();	
	Customer customer;
	
	public customerlogFrame() {
		backButton = new JButton("back");
		LogInButton = new JButton("log in");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(20); 
		passWordTextField = new JTextField(20); 
		
		
		logInPanel = new JPanel();
		
		logInPanel.add(userNameLabel);
		logInPanel.add(userNameTextField);
		logInPanel.add(passWordLabel);
		logInPanel.add(passWordTextField);
		logInPanel.add(LogInButton);
		logInPanel.add(backButton);
		
		backButton.addActionListener(this);
		LogInButton.addActionListener(this);
		add(logInPanel);
		setSize(400, 400);
		setTitle("Welcome to log in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		
		new customerlogFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backButton) {
			this.dispose();
			new logInJFrame();
			
		}
		
		if(e.getSource() == LogInButton) {
			// get  user input
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			// check the input info with server
			CustomerInfo customerInfo = getServer.getCustomerInfo(name,password);
			if (customerInfo == null) {
				System.out.println("ACCOUNT DOESNT EXIST");
			} else {
				System.out.println("Your username is " + customerInfo.getUserName());

				customer = new Customer(customerInfo);
			}			
				
		}
		
	}
	
	
}

// barber log in frame
class barberlogFrame extends JFrame implements ActionListener{
	private JButton LogInButton,backButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel logInPanel;
	Barber barber;
	GetServer getServer = new GetServer();
	
	
	public barberlogFrame() {
		LogInButton = new JButton("log in");
		backButton = new JButton("Back");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(20); 
		passWordTextField = new JTextField(20); 
		
		logInPanel = new JPanel();
		
		logInPanel.add(userNameLabel);
		logInPanel.add(userNameTextField);
		logInPanel.add(passWordLabel);
		logInPanel.add(passWordTextField);
		logInPanel.add(LogInButton);
		logInPanel.add(backButton);
		LogInButton.addActionListener(this); 
		backButton.addActionListener(this); 
		add(logInPanel);
		setSize(400, 200);
		setTitle("Welcome to log in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		
		new barberlogFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
			System.out.println("\n\n\n\n\n\n FUDGE \n\n\n\n\n\n\n\n\n\n");	
			if(e.getSource() == backButton) {
				this.dispose();
				new logInJFrame();
				
			}
		if(e.getSource() == LogInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			// check the input info with server
			CustomerInfo customerInfo = getServer.getCustomerInfo(name,password);
			if (customerInfo == null) {
				System.out.println("ACCOUNT DOESNT EXIST");
			} else {
				if (customerInfo.isBarber == true) {
					barber = new Barber(customerInfo);
				}
			}	
		}	
	}	
}
// barbershop log in frame
class barberShoplogFrame extends JFrame implements ActionListener{
	private JButton LogInButton,backButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel logInPanel;
	BarbershopBarberUI shopUI;
	BarberShop barberShop;
	GetServer getServer = new GetServer();
	
	public barberShoplogFrame() {
		backButton = new JButton("Back");
		LogInButton = new JButton("log in");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(20); 
		passWordTextField = new JTextField(20); 
		
		logInPanel = new JPanel();
		
		logInPanel.add(userNameLabel);
		logInPanel.add(userNameTextField);
		logInPanel.add(passWordLabel);
		logInPanel.add(passWordTextField);
		logInPanel.add(LogInButton);
		logInPanel.add(backButton);
		// add ActionListener
		LogInButton.addActionListener(this); 
		backButton.addActionListener(this); 
		add(logInPanel);
		setSize(400, 400);
		setTitle("Welcome to log in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		
		new barberShoplogFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			this.dispose();
			new logInJFrame();
			
		}
		
		if(e.getSource() == LogInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			// check the input info with server
			BarberShopInfo barberShopInfo = getServer.getBarberShopInfo(name,password);
			if (barberShopInfo == null) {
				System.out.println("ACCOUNT DOESNT EXIST");
			} else {
				System.out.println("Welcome " + barberShopInfo.getUserName());
				barberShop = new BarberShop(barberShopInfo);
				shopUI = new BarbershopBarberUI(barberShop);
			}
		}
		
	}
	
}

class barberSignFrame extends JFrame implements ActionListener{
	private JButton signInButton,backButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel signInPanel;
	
	GetServer getServer = new GetServer();
	
	public barberSignFrame() {
		backButton = new JButton("Back");
		signInButton = new JButton("Sign up");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(20); 
		passWordTextField = new JTextField(20); 
		
		signInPanel = new JPanel();
		
		signInPanel.add(userNameLabel);
		signInPanel.add(userNameTextField);
		signInPanel.add(passWordLabel);
		signInPanel.add(passWordTextField);
		signInPanel.add(signInButton);
		signInPanel.add(backButton);
		
		signInButton.addActionListener(this); 
		backButton.addActionListener(this); 
		add(signInPanel);
		setSize(400, 400);
		setTitle("Welcome to sign in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
public static void main(String[] args) {
		
		new barberSignFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			this.dispose();
			new signUpJFrame();
			
		}
		
		if(e.getSource() == signInButton) {
			
			String name =userNameTextField.getText();
			
			 String password =  passWordTextField.getText();
			 //  check password!
			 passwordCheck check = new  passwordCheck();
			 if(check.complexCheck(password) == true) {
				 
			CustomerInfo temp = new CustomerInfo(name,password);
			temp.isBarber = true;
			// check the input info with server
			boolean test = getServer.createCustomerInfoAccount(temp);
			if (test) {
				System.out.println("ACCOUNT CREATED");
			} else {
				System.out.println("ACCOUNT EXISTS");
			}	
			 }else {
				 JOptionPane.showConfirmDialog(null, "Need number and lowercase and uppercase and Special symbols!(8 to 16) !!!"," WarningDialog!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				// passWordTextField.setText("Need number and lowercase and uppercase and Special symbols!(8 to 16) ");
			 }
		}
		
	}
	
	
}

class customerSignFrame extends JFrame implements ActionListener{
	private JButton signInButton,backButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel signInPanel;
	
	GetServer getServer;
	
	public customerSignFrame() {
		this.getServer = new GetServer();
		backButton = new JButton("Back");
		signInButton = new JButton("Sign up");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(20); 
		passWordTextField = new JTextField(20); 
		
		signInPanel = new JPanel();
		
		signInPanel.add(userNameLabel);
		signInPanel.add(userNameTextField);
		signInPanel.add(passWordLabel);
		signInPanel.add(passWordTextField);
		signInPanel.add(signInButton);
		signInPanel.add(backButton);
		
		signInButton.addActionListener(this); 
		backButton.addActionListener(this); 
		add(signInPanel);
		setSize(400, 400);
		setTitle("Welcome to sign in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
public static void main(String[] args) {
		
		new customerSignFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			this.dispose();
			new signUpJFrame();
			
		}
		if(e.getSource() == signInButton) {
			String name = userNameTextField.getText();
			// checking password strong
			String password = passWordTextField.getText();
			passwordCheck check = new  passwordCheck();
			 if(check.complexCheck(password) == true) {

				// check the input info with server
			boolean test = getServer.createCustomerInfoAccount(new CustomerInfo(name,password));
			if (test) {
				System.out.println("ACCOUNT CREATED");
			} else {
				System.out.println("ACCOUNT EXISTS");
			}
			 }else {
				 JOptionPane.showConfirmDialog(null, "Need number and lowercase and uppercase and Special symbols!(8 to 16) !!!"," WarningDialog!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			 }
		}
		
	}
	
	
}

class barberShopSignFrame extends JFrame implements ActionListener{
	private JButton signInButton, backButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel signInPanel;
	
	GetServer getServer = new GetServer();
	public barberShopSignFrame() {
		backButton = new JButton("Back");
		signInButton = new JButton("Sign up");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(20); 
		passWordTextField = new JTextField(20); 
		
		signInPanel = new JPanel();
		
		signInPanel.add(userNameLabel);
		signInPanel.add(userNameTextField);
		signInPanel.add(passWordLabel);
		signInPanel.add(passWordTextField);
		signInPanel.add(signInButton);
		signInPanel.add(backButton);
		signInButton.addActionListener(this); 
		backButton.addActionListener(this); 
		add(signInPanel);
		setSize(400, 400);
		setTitle("Welcome to sign in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
public static void main(String[] args) {
		
		new barberShopSignFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backButton) {
			this.dispose();
			new signUpJFrame();
			
		}
		if(e.getSource() == signInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			//check password strong
			passwordCheck check = new  passwordCheck();
			 if(check.complexCheck(password) == true) {

			// check the input info with server
			boolean test = getServer.createBarberShopAccount(new BarberShopInfo(name,password));
			if (test) {
				System.out.println("ACCOUNT CREATED");
			} else {
				System.out.println("ACCOUNT EXISTS");
			}	
			 }else {// error need more strong password
				 JOptionPane.showConfirmDialog(null, "Need number and lowercase and uppercase and Special symbols!(8 to 16) !!!"," WarningDialog!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			 }
		}
		
	}
	
	
}

