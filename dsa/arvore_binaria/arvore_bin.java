class Node{

	int data;
	Node left;
	Node right;

	public Node(int data){
		this.data = data;
	}
}

class BinarySearchTree{
	Node root;

	public void insert(Node node){
		root = insertHelper(root, node);
	}

	private Node insertHelper(Node root, Node node){

		int data = node.data;

		if(root == null){
			root = node;
			return root;
		}
		else if (data < root.data){
			root.left = insertHelper(root.left, node);
		}
		else
			root.right = insertHelper(root.right, node);

		return root;
	}

	public void display(){
	
	}

	private void displayHelper(Node root){
	}

	public boolean search(int data){
		return false;
	}

	private boolean searchHelper(Node root, int data){
		return false;
	}

	public void remove(int data){
		
	}

	private Node removeHelper(Node root, int data){
		return null;
	}

	private int successor(Node root){
		return 0;
	}

	private int predecessor(Node root){
		return 0;
	}


}

public class arvore_bin{

	public static void main(String[] args){
		
		BinarySearchTree tree = new BinarySearchTree();

		tree.insert(new Node(5));
		tree.insert(new Node(1));
		tree.insert(new Node(9));
		tree.insert(new Node(2));
		tree.insert(new Node(7));
		tree.insert(new Node(3));
		tree.insert(new Node(6));
		tree.insert(new Node(4));
		tree.insert(new Node(8));

	}
}
