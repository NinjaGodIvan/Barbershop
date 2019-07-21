public class BarberShopTree implements Serializable{
	class Node implements Serializable {
		Node(BarberShopInfo barberShop) {
			this.info = barberShop;
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
		BarberShopInfo info;
		Node left = null;
		Node right = null;
	}
	
	private Node root;
	
	BarberShopTree() {
		this.root = null;
	}
	
	public void addNode(BarberShopInfo barberShop) {
		if (this.isEmpty()) { // check if empty
			root = new Node(barberShop);
		} else {
			this.addNode(barberShop,root);
		}	
	}
	
	private void addNode(BarberShopInfo c, Node n) {
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
	
	public void removeNode(BarberShopInfo barberShop) {
		if (!isEmpty()) {
			removeNode(barberShop,root);
		}
	}
	
	private void removeNode(BarberShopInfo c, Node n) {
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
	public BarberShopInfo returnInfo(BarberShopInfo barberShop) {
		if (!isEmpty()) { // check if empty
			return this.returnInfo(barberShop,root);
		}	
		return null;
		
	}

	private BarberShopInfo returnInfo(BarberShopInfo c, Node n) {
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
		} else  if (nodeName.compareTo(newNodeName) == 0) {
			return n.info;
		}
		return null;		
		
	}
	
	public boolean inTree(BarberShopInfo barberShop) {
		if (!isEmpty()) { // check if empty
			return this.inTree(barberShop,root);
		}	
		return false;
	}
	
	public boolean inTree(BarberShopInfo c, Node n) {
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
		} else  if (nodeName.compareTo(newNodeName) == 0) {
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
	//-- Gyasi Mose --
	public int sizeTree() {
		int i = 0;
		if (!isEmpty())
			return sizeTree(i, root);
		else
			return i;
	}
	
	private int sizeTree(int i, Node n) {
		if (n != null) {
			i = sizeTree(i, n.right);
			i = i+1;
			i = sizeTree(i, n.left);
		}
		return i;
	}
	
	public BarberShopInfo[] getBarberShopList() {
		int size = sizeTree();
		if (size != 0) {
			BarberShopInfo[] barberShopList = new BarberShopInfo[size];
			getBarberShopList(root,0,barberShopList);
			return barberShopList;
		}
		return null;
	}
	public int getBarberShopList(Node n, int i, BarberShopInfo[] list) {
		if (n!= null) {
			i = getBarberShopList(n.right, i, list);
			list[i++] = n.info;
			i = getBarberShopList(n.left, i, list);
		}
		return i;
	}
	
	public void printBarberShopList(BarberShopInfo[] list) {
		for (BarberShopInfo i: list) {
			System.out.println(i);
		}
	}	
	//-- Gyasi Mose --
	private boolean isEmpty() {
		if (root == null) {
			return true;		
		}
		return false;
	}
	
}
