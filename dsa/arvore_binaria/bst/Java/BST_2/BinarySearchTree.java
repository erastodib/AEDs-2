import java.util.Random;

public class BinarySearchTree {

	// 'Node' class structure and constructor
	private static class Node {
		int data;
		Node left, right;

		Node(int value){this.data = value;}
	}


	// Tree node declaration and BST constructor
	private Node root;
	BinarySearchTree(){root = null;}


	// INSERT
	public void insert(int x) throws Exception {root = insert(x, root);}

	private Node insert(int x, Node n) throws Exception {
		if (n == null)
			n = new Node(x);

		else if (x < n.data)
			n.left = insert(x, n.left);

		else if (x > n.data)
			n.right = insert(x, n.right);

		else throw new Exception("Duplicate element!");

		return n;
	}

	public void insertParent(int x) throws Exception {
		if (root == null)
			root = new Node(x);
		else if (x < root.data)
			insertParent(x, root.left, root);
		else if (x > root.data)
			insertParent(x, root.right, root);

		else throw new Exception("Duplicate element!");
	}

	private void insertParent(int x, Node n, Node parent) throws Exception {
		if (n == null){
			if (x < parent.data)
				parent.left = new Node(x);
			else
				parent.right = new Node(x);
		} else if (x < n.data)
			insertParent(x, n.left, n);
		else if (x > n.data)
			insertParent(x, n.right, n);
		
		else throw new Exception("Duplicate element!");
	}

	//ITERATIVE SEARCH
	public boolean searchIt(int x){
		Node n = root;

		while (n != null && x != n.data){
			if (x < n.data)
				n = n.left;
			else
				n = n.right;
		}

		return (n != null);
	}

	//RECURSIVE SEARCH
	public boolean searchRec(int x){return searchRec(x, root);}

	private boolean searchRec(int x, Node n){
		boolean found;
		
		if (n == null)
			found = false;
		else if (x == n.data)
			found = true;
		
		else if (x < n.data)
			found = searchRec(x, n.left);
		else
			found = searchRec(x, n.right);

		return found;
	}

	//REMOVE
	public Node remove(int x, Node n) throws Exception {
		if (n == null) throw new Exception ("Não encontrado!");
		
		else if (x < n.data)
			n.left = remove(x, n.left);
		else if (x > n.data)
			n.right = remove(x, n.right);
		else if (n.right == null)
			n = n.left;
		else if (n.left == null)
			n = n.right;
		else
			n.left = maxLeft(n, n.left);

		return n;
	}

	private Node maxLeft(Node n, Node left) {
        if (left.right == null) {
                n.data = left.data;
                left = left.left;
        }
        else
                left.right = maxLeft(n, left.right);

        return left;
}	

	//BST TRAVERSAL

	//In-Order traversal
	public void inOrder(){inOrder(root);}

	private void inOrder(Node n){
		if (n != null){
			inOrder(n.left);
			System.out.print(n.data + " ");
			inOrder(n.right);
		}
	}

	//Post-Order traversal
	public void postOrder(){postOrder(root);}

	private void postOrder(Node n){
		if (n != null){
			postOrder(n.left);
			postOrder(n.right);
			System.out.print(n.data + " ");
		}
	}

	//Pre-Order traversal
	public void preOrder(){preOrder(root);}

	private void preOrder(Node n){
		if (n != null){
			System.out.print(n.data + " ");
			preOrder(n.left);
			preOrder(n.right);
		}
	}

	//Get Tree Height
	public int getHeight(){return getHeight(root);}

	private int getHeight(Node n){
		int height = -1;
		if (n != null){
			int lH = getHeight(n.left);
			int rH = getHeight(n.right);
			height = 1 + ((lH > rH) ? lH : rH);
		}

		return height;
	}

	//Get largest binary tree value
	public int getMax() throws Exception {
		if (root == null) throw new Exception ("Não encontrado!");
		Node n = root;
		while (n.right != null) n = n.right;

		return n.data;
	}

	//Get smallest binary tree value
	public int getMin() throws Exception {
		if (root == null) throw new Exception ("Não encontrado!");
		Node n = root;
		while (n.left != null) n = n.left;

		return n.data;
	}
	

	/*Inserts 100,000 random distinct elements
	 * Every 1,000 insertions, displays the number of tree elements and its base 2 logarithm,
	 * the average number of comparisons of a search with measured and speculated success,
	 * and the tree height*/

	public void benchmark() throws Exception {
		BinarySearchTree a = new BinarySearchTree();
		Random gen = new Random();
		gen.setSeed(0);

		double harm = 0; // H(n) = 1 + 1/2 + ... + 1/n
				 
		for (int i = 1; i <= 100000; i++){
			int val;
			do {
				val = gen.nextInt(Integer.MAX_VALUE);
			} while (a.searchIt(val));

			a.insert(val);
			harm += 1.0/i;
			
			if (i % 1000 == 0){
				double lg = Math.log(i) / Math.log(2);
				long sum = a.lvlSum(a.root, 0);

				double measured = 1.0 + (double) sum / i;
				double spec = 2 * (1 + 1.0/i) * harm - 3;

				System.out.printf("\n| %-8s | %-12s | %-12s | %-12s | %-8s |%n",
    				"n:", "lg:", "measured:", "speculated:", "height:");

				System.out.printf("| %-8d | %-12.4f | %-12.4f | %-12.4f | %-8d |%n",
				i, lg, measured, spec, a.getHeight());

			}
		}
	}

	private long lvlSum(Node n, int lvl){
		long sum = 0;
		if (n != null)
			sum = lvl + lvlSum(n.left, lvl + 1) + lvlSum(n.right, lvl + 1);

		return sum;
	}

	//SUM OF EXISTING ELEMENTS
	public int sum(){return sum(root);};

	private int sum(Node n){
		int sum = 0;
		if (n != null)
			sum = n.data + sum(n.left) + sum(n.right);

		return sum;
	}

	//MIRROR TREE
	public void mirror(){
		System.out.println("Before:");
		inOrder();

		mirror(root);

		System.out.println("After:");
		inOrder();
	}

	private void mirror(Node n){
		if (n != null){
			Node tmp = n.left;
			n.left = n.right;
			n.right = tmp;
			mirror(n.left);
			mirror(n.right);
		}

	}


	public void randomTree() throws Exception {
		Random gen = new Random();
		gen.setSeed(0);

		for (int i = 0; i < 30; i++){
			int val;

			do {
				val = gen.nextInt(100);
			} while (searchIt(val));

			insert(val);
		}
	}

	public static void main(String[] args) throws Exception {
		BinarySearchTree tree = new BinarySearchTree();

		tree.randomTree();
		tree.mirror();
		//tree.benchmark();
	
	

	}
}


