#include <stdio.h>

static void minHeapify(int arr[], int n, int i);
static void partialMinHeapSort(int arr[], int n, int k);
static void printArray(int arr[], int size);

int main(void) {
    int arr[] = {8, 2, 5, 3, 9, 4, 7, 6, 1};
    int len = sizeof(arr) / sizeof(arr[0]);
    int k = 3;

    printf("Original array:\n");
    printArray(arr, len);

    partialMinHeapSort(arr, len, k);

    printf("\nAfter partial Min-HeapSort:\n");
    printArray(arr, len);

    return 0;
}

static void minHeapify(int arr[], int n, int i) {
    int smallest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && arr[left] < arr[smallest]) {
        smallest = left;
    }

    if (right < n && arr[right] < arr[smallest]) {
        smallest = right;
    }

    if (smallest != i) {
        int temp = arr[i];
        arr[i] = arr[smallest];
        arr[smallest] = temp;

        minHeapify(arr, n, smallest);
    }
}

static void partialMinHeapSort(int arr[], int n, int k) {
    // 1. Build min-heap: O(n)
    for (int i = n / 2 - 1; i >= 0; i--) {
        minHeapify(arr, n, i);
    }

    // 2. Extract top k smallest elements: O(k log n)
    for (int i = 0; i < k; i++) {
        int current_heap_size = n - i;
        int min_val = arr[0];

        arr[0] = arr[current_heap_size - 1];
        minHeapify(arr, current_heap_size - 1, 0);

        arr[current_heap_size - 1] = min_val;
    }
}

static void printArray(int arr[], int size) {
    printf("[ ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("]\n");
}
