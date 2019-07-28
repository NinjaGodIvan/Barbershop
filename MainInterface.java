import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class MainInterface extends JFrame implements ActionListener{

	
	private JButton logInButton,
					signUpButton;
	
	private JLabel titleLabel;
	private JPanel mainPanel;
	
	
	public MainInterface(){
	
	logInButton = new JButton("Log In");
	signUpButton = new JButton("Sign Up");
	titleLabel = new JLabel("Choose: ");
	
	
	mainPanel = new JPanel();
	mainPanel.add(titleLabel);
	mainPanel.add(signUpButton);
	mainPanel.add(logInButton);

	add(mainPanel);
	
	
	logInButton.addActionListener(this);
	signUpButton.addActionListener(this);
	
	setSize(400, 200);
	setTitle("Welcome to Barber Shop  !");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	}

	public static void main(String[] args) {
		new MainInterface();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logInButton) {
			this.dispose();
			new logInJFrame();
		}
		
		if(e.getSource() == signUpButton) {
			this.dispose();
			new signUpJFrame();
		}
	}
}

class logInJFrame extends JFrame implements ActionListener{
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
			
			setSize(400, 200);
			setTitle("Welcome to Log in  !");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
		}
		public static void main(String[] args) {
				
			new logInJFrame();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backButton) {
				this.dispose();
				new MainInterface();
			}	
			
			if(e.getSource() == loginAsCustomerButton) {
				this.dispose();
				new customerlogFrame();
			}	
			if(e.getSource() == loginAsBarberButton) {
				this.dispose();
				new barberlogFrame();
			}	
			if(e.getSource() == loginAsBarberShopButton) {
				this.dispose();
				new barberShoplogFrame();
			}	
			
			}			
			
}
	



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
		signUpAsCustomerButton.addActionListener(this);
		signUpAsBarberButton.addActionListener(this);
		signUpAsBarberShopButton.addActionListener(this);
		backButton.addActionListener(this);
		add(signUpPanel);
		setSize(400, 200);
		setTitle("Welcome to sign up  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
			
		new signUpJFrame();
	}
		//  .....
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			this.dispose();
			new MainInterface();
		}
		if(e.getSource() == signUpAsBarberButton) {
			this.dispose();
			new barberSignFrame();
		}
		
		if(e.getSource() == signUpAsCustomerButton) {
			this.dispose();
			new customerSignFrame();
		}
		if(e.getSource() == signUpAsBarberShopButton) {
			this.dispose();
			new barberShopSignFrame();
		}
		
		
	}
	
}



class customerlogFrame extends JFrame implements ActionListener{
	private JButton LogInButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel logInPanel;
	GetServer getServer = new GetServer();	
	Customer customer;
	
	public customerlogFrame() {
		LogInButton = new JButton("log in");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(10); 
		passWordTextField = new JTextField(10); 
		
		logInPanel = new JPanel();
		
		logInPanel.add(userNameLabel);
		logInPanel.add(userNameTextField);
		logInPanel.add(passWordLabel);
		logInPanel.add(passWordTextField);
		logInPanel.add(LogInButton);
		
		//LogInButton.addActionListener(this); 
		LogInButton.addActionListener(new aButtonHandler());
		add(logInPanel);
		setSize(400, 200);
		setTitle("Welcome to log in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		
		new customerlogFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == LogInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			//JOptionPane.showMessageDialog(null, name);
			CustomerInfo customerInfo = getServer.getCustomerInfo(name,password);
			if (customerInfo == null) {
				System.out.println("ACCOUNT DOESNT EXIST");
			} else {
				System.out.println("Your username is " + customerInfo.getUserName());

				customer = new Customer(customerInfo);
			}			
			
			
			
			//   need  work....
		}
		
	}
	
	
}


class barberlogFrame extends JFrame implements ActionListener{
	private JButton LogInButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel logInPanel;
	Barber barber;
	GetServer getServer = new GetServer();
	
	
	public barberlogFrame() {
		LogInButton = new JButton("log in");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(10); 
		passWordTextField = new JTextField(10); 
		
		logInPanel = new JPanel();
		
		logInPanel.add(userNameLabel);
		logInPanel.add(userNameTextField);
		logInPanel.add(passWordLabel);
		logInPanel.add(passWordTextField);
		logInPanel.add(LogInButton);
		
		LogInButton.addActionListener(this); 
		
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
		if(e.getSource() == LogInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
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

class barberShoplogFrame extends JFrame implements ActionListener{
	private JButton LogInButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel logInPanel;
	BarbershopBarberUI shopUI;
	BarberShop barberShop;
	GetServer getServer = new GetServer();
	
	public barberShoplogFrame() {
		LogInButton = new JButton("log in");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(10); 
		passWordTextField = new JTextField(10); 
		
		logInPanel = new JPanel();
		
		logInPanel.add(userNameLabel);
		logInPanel.add(userNameTextField);
		logInPanel.add(passWordLabel);
		logInPanel.add(passWordTextField);
		logInPanel.add(LogInButton);
		
		LogInButton.addActionListener(this); 
		
		add(logInPanel);
		setSize(400, 200);
		setTitle("Welcome to log in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		
		new barberShoplogFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == LogInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
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
	private JButton signInButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel signInPanel;
	
	GetServer getServer = new GetServer();
	
	public barberSignFrame() {
		signInButton = new JButton("Sign up");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(10); 
		passWordTextField = new JTextField(10); 
		
		signInPanel = new JPanel();
		
		signInPanel.add(userNameLabel);
		signInPanel.add(userNameTextField);
		signInPanel.add(passWordLabel);
		signInPanel.add(passWordTextField);
		signInPanel.add(signInButton);
		
		signInButton.addActionListener(this); 
		
		add(signInPanel);
		setSize(400, 200);
		setTitle("Welcome to sign in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
public static void main(String[] args) {
		
		new barberSignFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signInButton) {
			 //serNameTextField.setText("a");
			String name =userNameTextField.getText();
			
			 //passWordTextField.setText("a");
			 String password =  passWordTextField.getText();
			CustomerInfo temp = new CustomerInfo(name,password);
			temp.isBarber = true;
			boolean test = getServer.createCustomerInfoAccount(temp);
			if (test) {
				System.out.println("ACCOUNT CREATED");
			} else {
				System.out.println("ACCOUNT EXISTS");
			}		
		}
		
	}
	
	
}

class customerSignFrame extends JFrame implements ActionListener{
	private JButton signInButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel signInPanel;
	
	GetServer getServer = new GetServer();
	
	public customerSignFrame() {
		signInButton = new JButton("Sign up");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(10); 
		passWordTextField = new JTextField(10); 
		
		signInPanel = new JPanel();
		
		signInPanel.add(userNameLabel);
		signInPanel.add(userNameTextField);
		signInPanel.add(passWordLabel);
		signInPanel.add(passWordTextField);
		signInPanel.add(signInButton);
		
		signInButton.addActionListener(this); 
		
		add(signInPanel);
		setSize(400, 200);
		setTitle("Welcome to sign in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
public static void main(String[] args) {
		
		new customerSignFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			boolean test = getServer.createCustomerInfoAccount(new CustomerInfo(name,password));
			if (test) {
				System.out.println("ACCOUNT CREATED");
			} else {
				System.out.println("ACCOUNT EXISTS");
			}	
		}
		
	}
	
	
}

class barberShopSignFrame extends JFrame implements ActionListener{
	private JButton signInButton;
	private JLabel userNameLabel,
					passWordLabel;
	private JTextField userNameTextField,
						passWordTextField;
	private JPanel signInPanel;
	
	GetServer getServer = new GetServer();
	public barberShopSignFrame() {
		signInButton = new JButton("Sign up");
		userNameLabel = new JLabel("User Name: ");
		passWordLabel = new JLabel("password:  ");
		userNameTextField = new JTextField(10); 
		passWordTextField = new JTextField(10); 
		
		signInPanel = new JPanel();
		
		signInPanel.add(userNameLabel);
		signInPanel.add(userNameTextField);
		signInPanel.add(passWordLabel);
		signInPanel.add(passWordTextField);
		signInPanel.add(signInButton);
		
		signInButton.addActionListener(this); 
		
		add(signInPanel);
		setSize(400, 200);
		setTitle("Welcome to sign in  !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
public static void main(String[] args) {
		
		new barberShopSignFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signInButton) {
			String name = userNameTextField.getText();
			String password = passWordTextField.getText();
			boolean test = getServer.createBarberShopAccount(new BarberShopInfo(name,password));
			if (test) {
				System.out.println("ACCOUNT CREATED");
			} else {
				System.out.println("ACCOUNT EXISTS");
			}				
		}
		
	}
	
	
}


class RunProgram {
	
	public static void main(String[] args) {
		try {
			new RunProgram();
//		Request request;
		TempRunProgram temp = new TempRunProgram();	
/*		GetServer getServer = new GetServer();
		try {
			Scanner keyboard = new Scanner(System.in);
			while (true) {
			System.out.println("Enter 1 to enter Customer, 2 to get a customer: ");
			int value = keyboard.nextInt();
			keyboard.nextLine();
			if (value == 1) {
				System.out.println("Please Enter A Name to put in server: ");
				String name = keyboard.nextLine();
				getServer.sendInfo(new Packet((new CustomerInfo(name)),RequestEnum.Request.giveData));
			} else {
				System.out.println("Please Enter A Name to get from server: ");
				String name = keyboard.nextLine();
				getServer.getInfo(new Packet((new CustomerInfo(name)),RequestEnum.Request.getData));
			}
				
			} */
//			InterFace ainterface = new InterFace();
		} catch (Exception e) {
			
		}

	}
}

class TempRunProgram extends Thread {
	TempRunProgram() {		
		this.start();
	}
	
	public void run() {
		Server server = new Server();		
	}
}
