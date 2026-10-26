#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printArray(int arr[], int size);
void partialMergeSort(int arr[], int len, int k);
void merge(int arr[], int esq[], int l_len, int dir[], int r_len, int k);

int main(void) {
    srand((unsigned int)time(NULL));

    int arr[10];
    int len = sizeof(arr) / sizeof(arr[0]);
    int k = 3; // Isolate and sort only the 3 smallest elements at indices 0..k-1

    for (int i = 0; i < len; i++) {
        arr[i] = rand() % 100;
    }

    printf("Before: \n");
    printArray(arr, len);

    partialMergeSort(arr, len, k);

    printf("After partial MergeSort (first %d elements sorted at front): \n", k);
    printArray(arr, len);

    return 0;
}

void printArray(int arr[], int size) {
    printf("[ ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("]\n");
}

void partialMergeSort(int arr[], int len, int k) {
    if (len <= 1 || k <= 0)
        return;

    int mid = len / 2;

    int* esq = (int*)malloc(mid * sizeof(int));
    int* dir = (int*)malloc((len - mid) * sizeof(int));

    int i = 0;
    int j = 0;

    for (; i < len; i++) {
        if (i < mid) {
            esq[i] = arr[i];
        } else {
            dir[j] = arr[i];
            j++;
        }
    }

    // Recurse left with limit k
    partialMergeSort(esq, mid, k);

    // Only sort the right half if k exceeds the left subarray length
    if (k > mid) {
        partialMergeSort(dir, len - mid, k - mid);
    }

    merge(arr, esq, mid, dir, len - mid, k);

    free(esq);
    free(dir);
}

void merge(int arr[], int esq[], int l_len, int dir[], int r_len, int k) {
    int i = 0;        // Merged array index
    int l = 0, r = 0; // Left and Right array indices

    // Stop merging as soon as k elements or all available elements are placed
    while (l < l_len && r < r_len && i < k) {
        if (esq[l] <= dir[r]) {
            arr[i] = esq[l];
            l++;
        } else {
            arr[i] = dir[r];
            r++;
        }
        i++;
    }

    while (l < l_len && i < k) {
        arr[i++] = esq[l++];
    }

    while (r < r_len && i < k) {
        arr[i++] = dir[r++];
    }
}
