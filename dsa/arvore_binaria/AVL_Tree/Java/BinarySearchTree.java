// Node structure for the BST
class BSTNode {
    int data;
    BSTNode left;
    BSTNode right;

    public BSTNode(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    BSTNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Insert a key into the BST
    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private BSTNode insertRecursive(BSTNode current, int key) {
        if (current == null) {
            return new BSTNode(key);
        }

        if (key < current.data) {
            current.left = insertRecursive(current.left, key);
        } else if (key > current.data) {
            current.right = insertRecursive(current.right, key);
        }
        // Duplicate keys are ignored in this implementation

        return current;
    }

    // Search for a key in the BST
    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(BSTNode current, int key) {
        if (current == null) {
            return false;
        }
        if (key == current.data) {
            return true;
        }
        return key < current.data
            ? searchRecursive(current.left, key)
            : searchRecursive(current.right, key);
    }

    // Delete a key from the BST
    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private BSTNode deleteRecursive(BSTNode current, int key) {
        if (current == null) {
            return null;
        }

        // Find the node to delete
        if (key < current.data) {
            current.left = deleteRecursive(current.left, key);
        } else if (key > current.data) {
            current.right = deleteRecursive(current.right, key);
        } else {
            // Node found! Handle the 3 deletion cases:

            // Case 1 & 2: Node has 0 or 1 child
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Case 3: Node has 2 children
            // Find the in-order successor (smallest value in the right subtree)
            current.data = minValue(current.right);

            // Delete the in-order successor
            current.right = deleteRecursive(current.right, current.data);
        }

        return current;
    }

    // Helper method to find the minimum value in a subtree
    private int minValue(BSTNode root) {
        int minVal = root.data;
        while (root.left != null) {
            minVal = root.left.data;
            root = root.left;
        }
        return minVal;
    }

    // In-order traversal (prints BST in sorted order)
    public void inOrder(BSTNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insertion
        int[] values = {50, 30, 20, 40, 70, 60, 80};
        for (int val : values) {
            bst.insert(val);
        }

        System.out.print("Sorted BST (In-order): ");
        bst.inOrder(bst.root); // Output: 20 30 40 50 60 70 80
        System.out.println();

        // Search
        System.out.println("Search 40: " + bst.search(40)); // true
        System.out.println("Search 90: " + bst.search(90)); // false

        // Delete leaf node (20)
        bst.delete(20);
        System.out.print("After deleting 20: ");
        bst.inOrder(bst.root);
        System.out.println();

        // Delete node with two children (50)
        bst.delete(50);
        System.out.print("After deleting 50: ");
        bst.inOrder(bst.root);
        System.out.println();
    }
}
