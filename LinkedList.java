import java.io.Serializable;
public class LinkedList implements Serializable {
	Node headNode = null;
	
	class Node{
	    String data;
		Node next = null;	
		public Node(String data) {
			this.data = data;
		}
	}
	
	LinkedList(){
		headNode = null;
	}
	
	
	public void addNode(String data) {
		Node newNode = new Node(data);
		
		Node tem = headNode;   
		if(headNode == null) {   //Check is empty ?
			headNode = newNode;
			return;
		}
		while(tem.next != null) {
			tem = tem.next;
		}
		tem.next = newNode;				
	}
	
	//print the size of the linkedlist 
	public int size() {
		Node tem = headNode;
		 //check empty list
		if(headNode == null) {  
			return 0;
		}
		int count = 1;
		while(tem.next != null) {
			count++;
			tem = tem.next;
		}
		
		return count;
	}
	
	public void insertNodeByIndex(int index,String data) {
		//check the  index
		if(index<1|| index>size()+1) {	
			System.out.println("error index");
			return;
		}
		int currentIndex = 0;
		Node tem = headNode;
		Node newNode = new Node(data);
		
		while(tem.next != null) {
				
			if(index-1 == currentIndex) {
				newNode.next = tem.next;
				tem.next = newNode;
			}
			tem = tem.next;
			currentIndex++;
		}	
	}
	
	
	
	public void removeNodeByIndex(int index) {
		//check the  index
		if(index<1|| index>size()+1) {
			System.out.println("error index");
		}
		int currentIndex = 0;
		Node tem = headNode;
		while(tem.next != null) {
			if(index-1 == currentIndex) {
				tem.next = tem.next.next;
			}
			
			tem = tem.next;
			currentIndex++;
		}
	}
	
	public void removeHeadNode() {
		headNode = headNode.next;	
	}
	
	public void print() {
		int index = 0;
		Node tem = headNode;
		while(tem != null) {
			System.out.println(index+ " " +  tem.data);
			//System.out.println(tem.data);
			index++;
			tem = tem.next;
		}
	}
	
	/*public void Sort() {
		Node tem  = headNode;
	if(tem ==null) {
		System.out.println("empty");
	}	else  {
		while(tem.next != null) {
			Node dump = tem.next;
			if(tem.data<dump.data)
		}
		
	}
	}*/
	
	
	public void searchNode(String data) {
		Node searchNode = new Node(data);
		Node tem = headNode;
		int index = 0;
		while(tem != null) {
			if(searchNode.data.equals(tem.data)) {
				System.out.println("finded! index is :"+ index);
			}
			index++;
			tem = tem.next;
		}
	}
}
