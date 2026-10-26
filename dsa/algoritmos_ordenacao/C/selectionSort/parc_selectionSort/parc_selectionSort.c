#include <stdio.h>

void swap(int array[], int a, int b);
void partialSelectionSort(int array[], int size, int k);

int main(void) {
    int array[] = {9, 1, 8, 2, 7, 3, 6, 4, 5};
    int size = sizeof(array) / sizeof(array[0]);
    int k = 3; // Isolate and sort only the 3 smallest elements at indices 0..k-1

    partialSelectionSort(array, size, k);

    printf("After partial SelectionSort (first %d elements sorted): \n", k);
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");

    return 0;
}

void partialSelectionSort(int array[], int size, int k) {
    // Limit outer loop iterations to k instead of size - 1
    for (int i = 0; i < k && i < size - 1; i++) {
        int minIx = i;

        for (int j = i + 1; j < size; j++) {
            if (array[j] < array[minIx]) {
                minIx = j;
            }
        }

        swap(array, i, minIx);
    }
}

void swap(int array[], int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
}
