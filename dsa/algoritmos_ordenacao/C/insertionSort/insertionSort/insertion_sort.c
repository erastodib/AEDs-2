#include <stdio.h>

void insertionSort(int array[], int size);

int main(void) {
	int array[] = {9, 1, 8, 2, 7, 3, 6, 5, 4};
	int size = sizeof(array) / sizeof(array[0]);

	insertionSort(array, size);

	for (int i = 0; i < size; i++) {
		printf("%d ", array[i]);
	}
	printf("\n");

	return 0;
}

void insertionSort(int array[], int size) {
	for (int i = 1; i < size; i++) {
		int temp = array[i];
		int j = i - 1;

		while (j >= 0 && array[j] > temp) {
			array[j + 1] = array[j];
			j--;
		}
		array[j + 1] = temp;
	}
}
