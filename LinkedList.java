package com;
import java.io.Serializable;

/*	addNode(String) 				 ***for add Node to end of the Linked list
 *  insertNodeByIndex(int,String)    ***for insert the node to the linked list by the Index 
 *  removeHeadNode() 				 ***remove the first node of the Linked list
 *  removeNodeByIndex(int) 			 ***remove the node by the Index
 *  print() 						 ***print all the node
 *  searchNode(String)				 ***search the node , return the index
 * 
 **/

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
	
	//test drive
	/*
	public static void main(String[] args) {
		LinkedList test = new LinkedList();
		test.addNode("2");
		test.addNode("21");
		//test.print();
		test.addNode("3");
		test.addNode("40");
		test.addNode("50");
		//test.print();
		test.insertNodeByIndex(1, "10");
		//test.print();
		test.removeNodeByIndex(2);
		test.print();
		System.out.println("size : "+test.size());
		test.removeHeadNode();
		test.print();
		test.searchNode("50");
		}
	
	*/
	
	
}
