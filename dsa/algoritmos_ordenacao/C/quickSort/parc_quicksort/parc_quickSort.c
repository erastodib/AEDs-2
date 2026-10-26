#include <stdio.h>

static void partialQuickSort(int arr[], int ini, int fim, int k);
static int partition(int arr[], int ini, int fim);

int main(void) {
    int arr[] = {8, 2, 5, 3, 9, 4, 7, 6, 1};
    int len = sizeof(arr) / sizeof(arr[0]);
    int k = 3; // Sort only the smallest 3 elements

    partialQuickSort(arr, 0, len - 1, k);

    printf("Array (first %d elements sorted): ", k);
    for (int i = 0; i < len; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}

static void partialQuickSort(int arr[], int ini, int fim, int k) {
    if (fim <= ini) return;

    int pivo = partition(arr, ini, fim);

    // Only recurse left if the left range overlaps with the first k positions
    if (ini < k) {
        partialQuickSort(arr, ini, pivo - 1, k);
    }

    // Only recurse right if the pivot position is before position k - 1
    if (pivo < k - 1) {
        partialQuickSort(arr, pivo + 1, fim, k);
    }
}

static int partition(int arr[], int ini, int fim) {
    int pivo = arr[fim];
    int i = ini - 1;

    for (int j = ini; j <= fim - 1; j++) {
        if (arr[j] < pivo) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    i++;
    int temp = arr[i];
    arr[i] = arr[fim];
    arr[fim] = temp;
    return i;
}
