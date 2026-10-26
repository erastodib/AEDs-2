#include <stdio.h>

void swap(int array[], int a, int b);
void selectionSort(int array[], int size);

int main(void) {
    int array[] = {9, 1, 8, 2, 7, 3, 6, 4, 5};
    int size = sizeof(array) / sizeof(array[0]);

    selectionSort(array, size);

    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");

    return 0;
}

void selectionSort(int array[], int size) {
    for (int i = 0; i < size - 1; i++) {
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
