// The password need to contain the number and lowercase and uppercase and Special symbols same time !
// Ues the complexCheck(String) method to work! 

public class passwordCheck {
	
	
	public boolean passwordLengthCheck(String password) {
		if(password.length() != 0 && 8 <= password.length() && password.length()<=16) {
			return true;
		}
		System.out.println("check the length !!!");
		return false;
	}
	
	
	public boolean blankSpaceCheck(String password) {
		char[] pass = password.toCharArray();
		for(int i=0; i<pass.length; i++) {
			if(pass[i] == ' ') {
				System.out.println("Don't contain black space !!!");
				return false;
			}
		}
		return true;
	}
	
	public boolean complexCheck(String password) {
		int number=0 ,lowerCase=0,upperCase=0,symbols=0;
		
		if(passwordLengthCheck(password) && blankSpaceCheck(password)) {
			char[] pass = password.toCharArray();
			for(int i=0; i<pass.length; i++) {
				if(pass[i] >='0'&& pass[i]<='9') {
					number =1;
				}
				else if(pass[i] >='a'&&pass[i]<='z') {
					lowerCase =1;
				}
				else if(pass[i] >='A'&&pass[i]<='Z') {
					upperCase=1;
				}
				else {
					symbols = 1;
				}
			}
			int sum = number + lowerCase + upperCase + symbols;
			if(sum == 4) {
				return true;
				//System.out.println("This is a good password! " + password);
			}
		}
		return false;
		//System.out.println("Please check the password length and password can not contain blank space!!!")
	}
	
	
	
	//test drive
	/*
	public static void main(String[] args) {
		passwordCheck test = new passwordCheck();
		test.complexCheck("Asjsia sn12$");
	}*/
	
}
