#include <stdio.h>

static void heapify(int arr[], int n, int i);
static void partialHeapSort(int arr[], int n, int k);
static void printArray(int arr[], int size);

int main(void) {
    int arr[] = {8, 2, 5, 3, 9, 4, 7, 6, 1};
    int len = sizeof(arr) / sizeof(arr[0]);
    int k = 3; // Find and sort only the 3 largest elements

    printf("Original array:\n");
    printArray(arr, len);

    partialHeapSort(arr, len, k);

    printf("\nAfter partial HeapSort (top %d elements sorted at the end):\n", k);
    printArray(arr, len);

    return 0;
}

static void heapify(int arr[], int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && arr[left] > arr[largest]) {
        largest = left;
    }

    if (right < n && arr[right] > arr[largest]) {
        largest = right;
    }

    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        heapify(arr, n, largest);
    }
}

static void partialHeapSort(int arr[], int n, int k) {
    // 1. Build max-heap: O(n)
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // 2. Extract top k elements instead of all n: O(k log n)
    for (int i = n - 1; i >= n - k; i--) {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

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
