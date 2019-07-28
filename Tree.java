import java.io.Serializable;

public class Tree implements Serializable {
	class Node implements Serializable {
		Node(CustomerInfo customer) {
			this.info = customer;
		}
		public String getName() {
			return info.getUserName();
		}
		
		public boolean equalTo(Node n) {
			if (n != null && this.getName().equals(n.getName())) {
				return true;
			} else {
				return false;
			}
		}
		CustomerInfo info;
		Node left = null;
		Node right = null;
	}
	
	private Node root;
	
	Tree() {
		this.root = null;
	}
	
	public void addNode(CustomerInfo customer) {
		if (this.isEmpty()) { // check if empty
			root = new Node(customer);
		} else {
			this.addNode(customer,root);
		}	
	}
	
	private void addNode(CustomerInfo c, Node n) {
		String nodeName = n.info.getUserName();
		String newNodeName = c.getUserName();
		
		if (nodeName.compareTo(newNodeName) < 0) {
			if (n.left != null) {
				this.addNode(c,n.left);
			} else {
				n.left = new Node(c);	
			}	
		} else if (nodeName.compareTo(newNodeName) > 0) { // if names are same dont put in
			if (n.right != null) {
				this.addNode(c,n.right);
			} else { 
				n.right = new Node(c);
			}
		} else if (nodeName.compareTo(newNodeName) == 0) {
			n = new Node(c); // REPLACE
		}
	}
	
	public void removeNode(CustomerInfo customer) {
		if (!isEmpty()) {
			removeNode(customer,root);
		}
	}
	
	private void removeNode(CustomerInfo c, Node n) {
		String nodeName = n.info.getUserName();
		String newNodeName = c.getUserName();
		
		if (nodeName.compareTo(newNodeName) < 0) {
			if (n.left != null && !n.left.getName().equals(newNodeName)) {
				this.removeNode(c,n.left);	
			} else if (n.left != null) {
				removeThisNode(n.left,n);
			}
		} else if (nodeName.compareTo(newNodeName) > 0) { // if names are same dont put in
			if (n.right != null && !n.right.getName().equals(newNodeName)) {
				this.removeNode(c,n.right);
			} else if (n.right != null) {
				removeThisNode(n.right,n);
			}
		} else {
			if (n.getName().equals(newNodeName) && n == root) {
				removeThisNode(n,null);
			}
		}
	}
	
	private void removeThisNode(Node n, Node before) {
		Node hold = n;
		if (n.left == null && n.right == null) {
			if (n == root) {
				root = null;
			}
			n = null;
		} else if (n.left != null && n.right != null) {
			Node temp = n.right;
			Node beforeTemp = null;
			while (temp.left != null) {
				beforeTemp = temp;
				temp = temp.left;
			}
			temp.left = n.left;
			if (n.right != temp) {
				temp.right = n.right;
			}
			if (n == root) {
				root = temp;
			}
			n = temp;
			if (beforeTemp != null) {
				beforeTemp.left = null;
			}
		} else if (n.left != null) {
			if (n == root) {
				root = n.left;
			}
			n = n.left;
		} else {
			if (n == root) {
				root = n.right;
			}
			n = n.right;
		}		
		if (before != null) {
			if (before.left != null && hold.equalTo(before.left)) {
				before.left = n;
			} else {
				before.right = n;
			}
		}	
	}
	
	public CustomerInfo returnInfo(CustomerInfo customer) {
		if (!isEmpty()) { // check if empty
			return this.returnInfo(customer,root);
		}	
		return null;
		
	}

	private CustomerInfo returnInfo(CustomerInfo c, Node n) {
		String nodeName = n.info.getUserName();
		String newNodeName = c.getUserName();

		if (nodeName.compareTo(newNodeName) < 0) {
			if (n.left != null) {
				return this.returnInfo(c,n.left);
			}
		} else if (nodeName.compareTo(newNodeName) > 0) { // if names are same dont put in
			if (n.right != null) {
				return this.returnInfo(c,n.right);
			}
		} else if (nodeName.compareTo(newNodeName) == 0) {
			return n.info;
		}
		return null;		
	}
	public CustomerInfo returnInfo(String userName) {
		if(inTree(userName)) {
			Node n = root;
			String nodeName = n.getName();
			if (nodeName.compareTo(userName) == 0)
				return n.info;
			else {
				if(n.left != null)
					return returnInfo(userName, n.left);
				if(n.right != null)
					return returnInfo(userName, n.right);
			}
		}
		return null;
	}
	private CustomerInfo returnInfo(String userName, Node n) {
		String nodeName = n.getName();
		if (nodeName.compareTo(userName) == 0)
			return n.info;
		else {
			if(n.left != null)
				return returnInfo(userName, n.left);
			if(n.right != null)
				return returnInfo(userName, n.right);
		}
		return null;
	}
	public boolean inTree(String userName) {
		Node n = root;
		String nodeName = n.getName();
		if (nodeName.compareTo(userName) ==0)
			return true;
		else if (nodeName.compareTo(userName) > 0) {
			if (n.left != null) {
				return this.inTree(userName, n.left);
			}
		} else if (nodeName.compareTo(userName) < 0) {
			if (n.right != null) {
				return this.inTree(userName, n.right);
			}
		}
		return false;
	}
	public boolean inTree(String userName, Node n) {
		String nodeName = n.getName();
		if (nodeName.compareTo(userName) ==0)
			return true;
		else if (nodeName.compareTo(userName) > 0) {
			if (n.left != null) {
				return this.inTree(userName, n.left);
			}
		} else if (nodeName.compareTo(userName) < 0) {
			if (n.right != null) {
				return this.inTree(userName, n.right);
			}
		}
		return false;
	}
	
	
	public boolean inTree(CustomerInfo customer) {
		if (!isEmpty()) { // check if empty
			return this.inTree(customer,root);
		}	
		return false;
	}
	
	private boolean inTree(CustomerInfo c, Node n) {
		String nodeName = n.info.getUserName();
		String newNodeName = c.getUserName();
		
		if (nodeName.compareTo(newNodeName) < 0) {
			if (n.left != null) {
				return this.inTree(c,n.left);
			}
		} else if (nodeName.compareTo(newNodeName) > 0) { // if names are same dont put in
			if (n.right != null) {
				return this.inTree(c,n.right);
			}
		} else if (nodeName.compareTo(newNodeName) == 0) {
			if (c.getPassword().equals(n.info.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void printInOrder() {
		if (!isEmpty())
			printInOrder(root);
	}
	
	private void printInOrder(Node n) {
		if (n != null) {
			printInOrder(n.right);
			System.out.println(n.info);
			printInOrder(n.left);
		}
	}
	
	private boolean isEmpty() {
		if (root == null) {
			return true;		
		}
		return false;
	}
	
}
