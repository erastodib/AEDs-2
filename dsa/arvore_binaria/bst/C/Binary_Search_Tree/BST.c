#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Node structure
typedef struct Node {
    int data;
    struct Node* left;
    struct Node* right;
} Node;

// Create a new node
Node* createNode(int value) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if (newNode == NULL) {
        printf("Memory allocation failed!\n");
        exit(1);
    }
    newNode->data = value;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

// Insert a key into the BST
Node* insert(Node* root, int key) {
    if (root == NULL) {
        return createNode(key);
    }

    if (key < root->data) {
        root->left = insert(root->left, key);
    } else if (key > root->data) {
        root->right = insert(root->right, key);
    }
    // Duplicate keys are ignored

    return root;
}

// Search for a key in the BST
bool search(Node* root, int key) {
    if (root == NULL) {
        return false;
    }
    if (root->data == key) {
        return true;
    }

    if (key < root->data) {
        return search(root->left, key);
    } else {
        return search(root->right, key);
    }
}

// Helper function to find the minimum value node in a subtree
Node* findMin(Node* root) {
    Node* current = root;
    while (current && current->left != NULL) {
        current = current->left;
    }
    return current;
}

// Delete a key from the BST
Node* deleteNode(Node* root, int key) {
    if (root == NULL) {
        return NULL;
    }

    if (key < root->data) {
        root->left = deleteNode(root->left, key);
    } else if (key > root->data) {
        root->right = deleteNode(root->right, key);
    } else {
        // Node found! Handle deletion cases:

        // Case 1 & 2: Node with only one child or no child
        if (root->left == NULL) {
            Node* temp = root->right;
            free(root);
            return temp;
        } else if (root->right == NULL) {
            Node* temp = root->left;
            free(root);
            return temp;
        }

        // Case 3: Node with two children
        // Get in-order successor (smallest in the right subtree)
        Node* temp = findMin(root->right);

        // Copy successor content to this node
        root->data = temp->data;

        // Delete the in-order successor
        root->right = deleteNode(root->right, temp->data);
    }
    return root;
}

// In-order traversal (prints BST in sorted order)
void inOrder(Node* root) {
    if (root == NULL) return;
    inOrder(root->left);
    printf("%d ", root->data);
    inOrder(root->right);
}

// Helper to free all allocated memory for the tree
void freeTree(Node* root) {
    if (root == NULL) return;
    freeTree(root->left);
    freeTree(root->right);
    free(root);
}

int main() {
    Node* root = NULL;

    // Insertion
    int values[] = {50, 30, 20, 40, 70, 60, 80};
    int n = sizeof(values) / sizeof(values[0]);

    for (int i = 0; i < n; i++) {
        root = insert(root, values[i]);
    }

    printf("Sorted BST (In-order): ");
    inOrder(root); // Output: 20 30 40 50 60 70 80
    printf("\n");

    // Search
    printf("Search 40: %s\n", search(root, 40) ? "Found" : "Not Found");
    printf("Search 90: %s\n", search(root, 90) ? "Found" : "Not Found");

    // Delete leaf node (20)
    root = deleteNode(root, 20);
    printf("After deleting 20: ");
    inOrder(root);
    printf("\n");

    // Delete node with two children (50)
    root = deleteNode(root, 50);
    printf("After deleting 50: ");
    inOrder(root);
    printf("\n");

    // Clean up memory
    freeTree(root);

    return 0;
}
