#include <stdio.h>

typedef struct Tempo {
	int hora;
	int min;
	int seg;
} Tempo;

int s_total(Tempo tempo){
	return tempo.hora * 3600 + tempo.min * 60 + tempo.seg;
}


typedef struct Corredor{
	char nome[30];
	Tempo tempo;
} Corredor;

void insertionSort_parc(Corredor arr[], int n, int k){
	for (int i = 1; i < n; i++){
		Corredor tmp = arr[i];
		int j;
		int t = s_total(tmp.tempo);
		if (i <= k)
			j = i - 1;
		else 
			j = k - 1;
		
		while (j >= 0 && (s_total(arr[j].tempo) > t)){
			arr[j+1] = arr[j];
			j--;
		}
		arr[j+1] = tmp;
	}
}

int main(){
	
	int n; //qtd corredores
	int k; //qtd ranking
	
	while (scanf("%d %d", &n, &k) != EOF){
		Corredor arr[n];

	for (int i = 0; i < n; i++)
		scanf("%29s %d %d %d", arr[i].nome, &arr[i].tempo.hora, &arr[i].tempo.min, &arr[i].tempo.seg);


		insertionSort_parc(arr, n, k);

		for (int i = 0; i < k; i++)
                	printf("%s %d %d %d\n", arr[i].nome, arr[i].tempo.hora, arr[i].tempo.min, arr[i].tempo.seg);
	}
}

