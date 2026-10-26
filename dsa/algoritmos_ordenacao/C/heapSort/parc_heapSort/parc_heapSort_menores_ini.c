#include <stdio.h>

static void maxHeapify(int arr[], int size, int i);
static void partialSortSmallestFront(int arr[], int n, int k);
static void printArray(int arr[], int size);

int main(void) {
    int arr[] = {8, 2, 5, 3, 9, 4, 7, 6, 1};
    int len = sizeof(arr) / sizeof(arr[0]);
    int k = 3; // Isolate and sort the 3 smallest elements at indices 0..k-1

    printf("Original array:\n");
    printArray(arr, len);

    partialSortSmallestFront(arr, len, k);

    printf("\nAfter partial sort (first %d smallest elements at indices 0..%d):\n", k, k - 1);
    printArray(arr, len);

    return 0;
}

static void maxHeapify(int arr[], int size, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < size && arr[left] > arr[largest]) {
        largest = left;
    }

    if (right < size && arr[right] > arr[largest]) {
        largest = right;
    }

    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        maxHeapify(arr, size, largest);
    }
}

static void partialSortSmallestFront(int arr[], int n, int k) {
    if (k <= 0 || k > n) return;

    // 1. Build a max-heap out of the first k elements: O(k)
    for (int i = k / 2 - 1; i >= 0; i--) {
        maxHeapify(arr, k, i);
    }

    // 2. Process remaining elements (indices k to n-1): O((n - k) * log k)
    // If an element is smaller than max element among the k smallest (arr[0]), swap it in
    for (int i = k; i < n; i++) {
        if (arr[i] < arr[0]) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            maxHeapify(arr, k, 0);
        }
    }

    // 3. Sort the first k elements in ascending order via standard HeapSort: O(k log k)
    for (int i = k - 1; i > 0; i--) {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        maxHeapify(arr, i, 0);
    }
}

static void printArray(int arr[], int size) {
    printf("[ ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("]\n");
}
