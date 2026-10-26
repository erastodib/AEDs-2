#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printArray(int arr[], int size);
void mergeSort(int arr[], int len);
void merge(int arr[], int esq[], int l_len, int dir[], int r_len);

int main(void) {
	srand((unsigned int)time(NULL)); // Seed for pseudo-random number generation

	int arr[10];
	int len = sizeof(arr) / sizeof(arr[0]);

	for (int i = 0; i < len; i++) {
		arr[i] = rand() % 100;
	}

	printf("Before: \n");
	printArray(arr, len);

	mergeSort(arr, len);

	printf("After: \n");
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

void mergeSort(int arr[], int len) {
	if (len <= 1)
		return;

	int mid = len / 2;

	int* esq = (int*)malloc(mid * sizeof(int));
	int* dir = (int*)malloc((len - mid) * sizeof(int));

	int i = 0; // Esquerda
	int j = 0; // Direita

	for (; i < len; i++) {
		if (i < mid) {
			esq[i] = arr[i];
		} else {
			dir[j] = arr[i];
			j++;
		}
	}

	mergeSort(esq, mid);
	mergeSort(dir, len - mid);

	merge(arr, esq, mid, dir, len - mid);

	// Free memory dynamically allocated during recursion
	free(esq);
	free(dir);
}

void merge(int arr[], int esq[], int l_len, int dir[], int r_len) {
	int i = 0;        // Merged array index
	int l = 0, r = 0; // Left and Right array indices

	while (l < l_len && r < r_len) {
		if (esq[l] <= dir[r]) {
			arr[i] = esq[l];
			l++;
		} else {
			arr[i] = dir[r];
			r++;
		}
		i++;
	}

	while (l < l_len) {
		arr[i++] = esq[l++];
	}

	while (r < r_len) {
		arr[i++] = dir[r++];
	}
}
