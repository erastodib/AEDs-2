#include <stdio.h>

static void heapify(int arr[], int n, int i);
static void heapSort(int arr[], int n);
static void printArray(int arr[], int size);

int main(void) {
    int arr[] = {8, 2, 5, 3, 9, 4, 7, 6, 1};
    int len = sizeof(arr) / sizeof(arr[0]);

    printf("Before:\n");
    printArray(arr, len);

    heapSort(arr, len);

    printf("After:\n");
    printArray(arr, len);

    return 0;
}

static void heapify(int arr[], int n, int i) {
    int largest = i;       // Initialize largest as root
    int left = 2 * i + 1;  // Left child index
    int right = 2 * i + 2; // Right child index

    // If left child is larger than root
    if (left < n && arr[left] > arr[largest]) {
        largest = left;
    }

    // If right child is larger than largest so far
    if (right < n && arr[right] > arr[largest]) {
        largest = right;
    }

    // If largest is not root
    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}

static void heapSort(int arr[], int n) {
    // Build max heat (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // Extract elements one by one from heap
    for (int i = n - 1; i > 0; i--) {
        // Move current root to end
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        // Call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}

static void printArray(int arr[], int size) {
    printf("[ ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("]\n");
}
