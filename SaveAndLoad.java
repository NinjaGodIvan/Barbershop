package barber;
import java.io.*;

public class SaveAndLoad {
	
	String filename = "data.txt";
	
	public void SaveData(Tree tree) {
		
		try {
		
			ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream(filename));
			saveData.writeObject(tree);
			saveData.close();
		
		}catch(IOException e) {
			System.out.println("Unable to save data.");
		}
		
	}
	
	public Tree LoadData() {
		
		Tree loadTree = null;
		
		try {
			
			ObjectInputStream loadData = new ObjectInputStream(new FileInputStream(filename));
			loadTree = (Tree) loadData.readObject();
			loadData.close();
		
		}catch(Exception e) {
			System.out.println("Unable to load data.");
		}		
		return loadTree;
	}

}
