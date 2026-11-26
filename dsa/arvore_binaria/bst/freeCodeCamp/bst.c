//Binary Search Tree (BST)
//->	A binary tree in which for each node, the value of all nodes in the left subtree is lesser or equal than the root, and the value of all the nodes in the right subtree is greater than the root.

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

typedef struct Node {
	int data;
	struct Node* left;
	struct Node* right;
} Node;			//Nodes will be created in heap using malloc

Node* getNewNode(int data);
Node* insertA(Node* root, int data);
void insertB(Node** rootPtr, int data);
bool search(Node* root, int data);
int findMinValIt(Node* root);
int findMinVal(Node* root);
int findMaxVal(Node* root);
Node* findMinNode(Node* root);
Node* findMaxNode(Node* root);
int max(Node* a, Node* b);
int findHeight(Node* root);
void preOrder(Node* root);
void inOrder(Node* root);
void postOrder(Node* root);
bool isBSTHelper(Node* root);
bool isBST(Node* root, long long min, long long max);


int main(){
	Node* root = NULL; //Creating an empty tree
	root = insertA(root, 15);
	root = insertA(root, 10);
	root = insertA(root, 20);
	insertB(&root, 25);
	insertB(&root, 8);
	insertB(&root, 12);

	int num;
	printf("Search value: ");
	scanf("%d", &num);
	if(search(root, num) == true) printf("Value '%d' found.\n", num);
	else printf("Value '%d' not found.\n", num);
}

//CREATE
Node* getNewNode(int data){
	Node *newNode = (Node*)malloc(sizeof(Node));
	newNode->data = data;
	newNode->left = newNode->right = NULL;
	return newNode;
}
//

//INSERT
//Method A: Return updated root pointer
Node* insertA(Node* root, int data){
	if (root == NULL){ //empty tree or leaf found
		return getNewNode(data);
	}
	else if (data <= root->data){
		root->left = insertA(root->left, data);
	}
	else{
		root->right = insertA(root->right, data);
	}

	return root;

}

//Method B: Pass double pointer to root
void insertB(Node** rootPtr, int data){
	if (*rootPtr == NULL){ //empty tree or leaf found
		*rootPtr = getNewNode(data);
		return;
	}
	else if (data <= (*rootPtr)->data){
		insertB(&((*rootPtr)->left), data);
	}
	else {
		insertB(&((*rootPtr)->left), data);
	}
}
//

//SEARCH
bool search(Node* root, int data){
	if (root == NULL) return false;
	else if(root->data == data) return true;
	else if (data <= root->data) return search(root->left, data);
	else return search(root->right, data);
}
//

//Find min/max element
//
//Iterative solution
int findMinValIt(Node* root){
	if (root == NULL){
		printf("Error: empty tree!\n");
		return -1;
	}
	while (root->left != NULL){
		root = root->left;
	}
	return root->data;
}

//Recursive solution

int findMinVal(Node* root){
	if(root == NULL){
		printf("Error: empty tree!\n");
		return -1;
	}
	else if (root->left == NULL){
		return root->data;
	}

	return findMinVal(root->left);
}

int findMaxVal(Node* root){
	if (root == NULL){
		printf("Error: empty tree!\n");
		return -1;
	}
	else if (root->right == NULL){
		return root->data;
	}

	return findMaxVal(root->right);

}
//

//Find min/max NODE
Node* findMinNode(Node* root){
	while (root && root->left != NULL){
		node = node->left;
	}
	return node;
}

Node* findMaxNode(Node* root){
	while (root && root -> right != NULL){
		node = node->right;
	}
	return node;
}
//

//Find tree height
int max (Root* a, Root* b){ //Helper function to find greater of 2 values
	if (a > b) return a;
	else return b;
}

int findHeight(Node* root){
	if (root == NULL){
		return -1;
	}

	return max(findHeight(root->left), findHeight(root->right)) + 1;
}
//

//Tree traversal


//Pre Order:	DLR
//In Order:	LDR
//Post Order:	LRD
 

void preOrder(Node* root){
	
	if (root == NULL) return;
	printf("%d", root->data);
	preOrder(root->left);
	preOrder(root->right);
}

void inOrder(Node* root){
	
	if (root == NULL) return;
	inOrder(root->left);
	printf("%d", root->data);
	inOrder(root->right);
}

void postOrder(Node* root){
	
	if (root == NULL) return;
	postOrder(root->left);
	postOrder(root->right);
	printf("%d", root->data);
}

//DELETE

Node* delete(Node* root, int data){
	 
	if (root == NULL) return root;
	else (if data < root->data)
	       root->left = delete(root->left, data);
	else if (data > root->data)
		root->right = delete(root->left, data);
	else {
		if (root->left == NULL && root->right == NULL){
			free(root);
			root = NULL;
		}
		else if (root->left == NULL){
			Node* temp = root;
			root = root->right;
			free(temp);
		}
		else if (root->right == NULL){
			Node* temp = root;
			root = root->right;
			free(temp);
		}
		else {
			Node* temp = findMin(root->right);
			root->data = temp->data;
			root->right = delete(root->right, temp->data);
		}
	}

	return root;
}

// Not necessairly part of the data structure function, but I thought it'd be nice to add this one as well, since its a pretty popular leetcode question:

bool isBST(Node* root, long long min, long long max){

	if (root == NULL) return true;
	
	if (root->data <= min || root-> data >= max){
		return false;
	}

	return isBST(root->left, min, root->data) && isBST(root->right, root->data, max);
}

bool isBSTHelper(Node* root){
	return isBST(root, LLONG_MIN, LLONG_MAX);
}
