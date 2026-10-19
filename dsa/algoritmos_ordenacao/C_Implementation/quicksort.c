#include <stdio.h>

void swap(int *x, int *y);
void quicksort(int array[], int len);
void quicksort_rec(int array[], int low, int high);
int partition(int array[], int low, int high);

int main(){
	int a[] = {10, 11, 23, 44, 8, 15, 3, 9, 12 ,45, 56, 45, 45};
	int len = (sizeof(a)/sizeof(int));

	quicksort(a, len);

	for (int i = 0; i < len; i++)
		printf("%d ", a[i]);
	printf("\n");
}

void swap(int *x, int *y){
	int temp = *x;
	*x = *y;
	*y = temp;
}

void quicksort(int array[], int len){
	quicksort_rec(array, 0, len - 1);
}

void quicksort_rec(int array[], int low, int high){

	if (low < high){

		int pivot_idx = partition(array, low, high);
		quicksort_rec(array, low, pivot_idx - 1);
		quicksort_rec(array, pivot_idx + 1, high);
	}
}

int partition(int array[], int low, int high){
	
	int pivot = array[high];

	int i = low;

	for (int j = low; j < high; j++){
		if (array[j] <= pivot){
			swap(&array[i], &array[j]);
			i++;
		}
	}

	swap(&array[i], &array[high]);

	return i;
}
