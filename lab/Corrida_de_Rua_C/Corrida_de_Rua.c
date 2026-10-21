#include <stdio.h>

typedef struct Tempo {
	int hora;
	int min;
	int seg;
} Tempo;

Tempo setTempo(int hora, int min, int seg){
	Tempo tempo;
	tempo.hora = hora;
	tempo.min = min;
	tempo.seg= seg;

	return tempo;
}


typedef struct Corredor{
	char nome[30];
	Tempo tempo;
} Corredor;

void insertionSort_aux(Corredor arr[], int low, int high){
	for (int i = low; low < high; i++){
		Corredor tmp = arr[i];
		int j = i - 1;
		while(j >= low && arr[j]. > tmp){
			arr[j+1] = arr[j];
			j--;
		}
		arr[j+1] = tmp;
	}
}

void insertionSort_parc(Corredor arr[], int n, int k){
	for (int i = 1; i < k; i++){
		int tmp = arr[i];
		int j = i - 1;
		while(j >= k && arr[j] > tmp){
			arr[j+1] = arr[j];
			j--;
		}
		arr[j+1] = tmp;
	}
}

int main(){
	
	int n; //qtd corredores
	int k; //qtd ranking
	
	while (scanf("%d", &n) != EOF){

		scanf("%d", &k);
		Corredor arr[n];

		for (int i = 0; i < n; i++){
				scanf("%[^\n] %d %d %d" arr[i].nome, &arr[i].Tempo.hora, &arr[i].Tempo.min, &arr[i].Tempo.seg);
				getchar();
		}
	}

}

