import java.io.*;

public class SaveAndLoad {
	
	
	static int counter = 0;
	
	String filename1;
	String filename2;
	
	SaveAndLoad(String filename) {
		counter++;
		this.filename1 = filename + "" + counter + ".dat";
		this.filename2 = filename + "2" + counter + ".dat";
//		System.out.println(this.filename1 + " " + this.filename2);
	}
	
	public void SaveData(Tree tree) {
		
		try {
			File temp = new File(filename1);
			temp.createNewFile();			
			ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream(filename1,false));
			saveData.writeObject(tree);
			saveData.close();
		}catch(IOException e) {
			System.out.println("Unable to save data.");
		}
	}
	
	public void SaveData(BarberShopTree barberTree) {
		
		try {
			File temp = new File(filename2);
			temp.createNewFile();				
			ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream(filename2,false));
			saveData.writeObject(barberTree);
			saveData.close();
		}catch(IOException e) {
			System.out.println("Unable to save data.");
		}	
	}
	
	public Tree LoadTreeData() {
		
		Tree loadTree = null;
		
		try {
			File temp = new File(filename1);
			temp.createNewFile();			
			ObjectInputStream loadData = new ObjectInputStream(new FileInputStream(filename1));
			loadTree = (Tree) loadData.readObject();
			loadData.close();
		
		}catch(Exception e) {
//			System.out.println("Unable to load data.");
		}	finally {	
		return loadTree;
		}
	}
	
	public BarberShopTree LoadBarberShopTreeData() {
		
		BarberShopTree loadTree = null;
		
		try {
			File temp = new File(filename2);
			temp.createNewFile();			
			ObjectInputStream loadData = new ObjectInputStream(new FileInputStream(filename2));
			loadTree = (BarberShopTree) loadData.readObject();
			loadData.close();
		
		}catch(Exception e) {
//			System.out.println("Unable to load data.");
		} finally {
		return loadTree;
		}
	}

}
