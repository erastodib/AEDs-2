#include <stdio.h>
#include <stdlib.h>
#include <time.h>

static void printArray(int arr[], int size);
static void swap(int arr[], int a, int b);
static void quicksort_range(int arr[], int lowIndex, int highIndex);
static void quicksort(int arr[], int len);

int main(void) {
    srand((unsigned int)time(NULL));

    int arr[10];
    int len = sizeof(arr) / sizeof(arr[0]);

    for (int i = 0; i < len; i++) {
        arr[i] = rand() % 100;
    }

    printf("Before:\n");
    printArray(arr, len);

    quicksort(arr, len);

    printf("After:\n");
    printArray(arr, len);

    return 0;
}

static void quicksort(int arr[], int len) {
    quicksort_range(arr, 0, len - 1);
}

static void quicksort_range(int arr[], int lowIndex, int highIndex) {
    if (lowIndex >= highIndex)
        return;

    int pivotIndex = (rand() % (highIndex - lowIndex + 1)) + lowIndex;
    int pivot = arr[pivotIndex];
    swap(arr, pivotIndex, highIndex);

    int lp = lowIndex;
    int hp = highIndex;

    while (lp < hp) {
        while (arr[lp] <= pivot && lp < hp) {
            lp++;
        }

        while (arr[hp] >= pivot && lp < hp) {
            hp--;
        }

        swap(arr, lp, hp);
    }

    swap(arr, lp, highIndex);

    quicksort_range(arr, lowIndex, lp - 1);
    quicksort_range(arr, lp + 1, highIndex);
}

static void swap(int arr[], int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}

static void printArray(int arr[], int size) {
    printf("[ ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("]\n");
}
