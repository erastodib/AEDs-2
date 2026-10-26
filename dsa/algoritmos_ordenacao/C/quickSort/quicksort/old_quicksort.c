#include <stdio.h>

// QuickSort = move elementos menores à esquerda de um pivô
// Divide o array recursivamente em 2 partes
//
// Complexidade de tempo: Melhor caso: O(n Log n)
//            Caso médio: O(n Log n)
//            Pior caso: O(n²) se já ordenado
//
// Complexidade espacial = O(log(n)) devido à recursão

static void quickSort(int arr[], int ini, int fim);
static int partition(int arr[], int ini, int fim);

int main(void) {
    int arr[] = {8, 2, 5, 3, 9, 4, 7, 6, 1};
    int len = sizeof(arr) / sizeof(arr[0]);

    quickSort(arr, 0, len - 1);

    for (int i = 0; i < len; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}

static void quickSort(int arr[], int ini, int fim) {
    if (fim <= ini) return;

    int pivo = partition(arr, ini, fim);
    quickSort(arr, ini, pivo - 1);
    quickSort(arr, pivo + 1, fim);
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
