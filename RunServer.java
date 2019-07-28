import java.util.Scanner;

public class RunServer {
	
	public static void main(String[] args) {
		try {
			TempRunProgram temp = new TempRunProgram();	
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
