// Node structure representing each element in the tree
class Node {
    int data;
    Node left;
    Node right;

    public Node(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }
}

// Binary Tree class with standard operations
public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    // Insert elements in level-order (breadth-first)
    public void insert(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }

        // Queue used for level-order insertion
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                break;
            } else {
                queue.add(current.left);
            }

            if (current.right == null) {
                current.right = newNode;
                break;
            } else {
                queue.add(current.right);
            }
        }
    }

    // In-order traversal: Left -> Root -> Right
    public void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    // Pre-order traversal: Root -> Left -> Right
    public void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // Post-order traversal: Left -> Right -> Root
    public void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserting elements into the tree
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        for (int val : values) {
            tree.insert(val);
        }

        System.out.print("In-order traversal:   ");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.print("Pre-order traversal:  ");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("Post-order traversal: ");
        tree.postOrder(tree.root);
        System.out.println();
    }
}
