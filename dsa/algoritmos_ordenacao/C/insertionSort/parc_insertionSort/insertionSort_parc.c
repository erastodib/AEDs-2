#include <stdio.h>

void insertionSort_parc(int vet[], int n, int k);

int main(void) {
	int vet[] = {100, 200, 300, 400, 500, 1, 50};
	int n = sizeof(vet) / sizeof(vet[0]); // 7
	int k = 3;

	insertionSort_parc(vet, n, k);

	for (int i = 0; i < n; i++) {
		printf("%d ", vet[i]);
	}
	printf("\n");

	return 0;
}

void insertionSort_parc(int vet[], int n, int k) {
	for (int i = 1; i < n; i++) {
		int tmp = vet[i];
		int j;
		if (i <= k) {
			j = i - 1;
		} else {
			j = k - 1;
		}

		while (j >= 0 && vet[j] > tmp) {
			vet[j + 1] = vet[j];
			j--;
		}

		vet[j + 1] = tmp;
	}
}
