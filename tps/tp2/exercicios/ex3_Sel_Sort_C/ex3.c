#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <strings.h>
#define DEFAULT 128

// CLASSE DATA ===================================================================================

typedef struct{

	int dia;
	int mes;
	int ano;

} Data;

Data parseData(char *str){

	Data data;
	char *parte;

	parte = strtok(str, "-");
	data.ano = atoi(parte);

	parte = strtok(NULL, "-");
	data.mes = atoi(parte);

	parte = strtok(NULL, "-");
	data.dia = atoi(parte);

	return data;
}

void formatData(Data d, char *buffer){
	sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
}

// CLASSE VEICULO ===================================================================================

typedef struct{

	int id;
	char marca[DEFAULT];
	char modelo[DEFAULT];
	int ano;
	char categoria[DEFAULT];
	char combustivel[DEFAULT];
	int cilindros;
	double cilindrada;
	char transmissao[DEFAULT];
	char tracao[DEFAULT];
	double consumoCidade;
	double consumoEstrada;
	double co2;
	bool turbo;
	Data dataRegistro;

} Veiculo;

Veiculo *parseVeiculo(char *str){

	Veiculo *v = (Veiculo *)malloc(sizeof(Veiculo));
	char *parte;

	parte = strtok(str, ",");
	v->id = atoi(parte);

	parte = strtok(NULL, ",");
	strcpy(v->marca, parte);

	parte = strtok(NULL, ",");
	strcpy(v->modelo, parte);

	parte = strtok(NULL, ",");
	v->ano = atoi(parte);

	parte = strtok(NULL, ",");
	strcpy(v->categoria, parte);

	parte = strtok(NULL, ",");
	strcpy(v->combustivel, parte);

	parte = strtok(NULL, ",");
	v->cilindros = atoi(parte);

	parte = strtok(NULL, ",");
	v->cilindrada = atof(parte);

	parte = strtok(NULL, ",");
	strcpy(v->transmissao, parte);

	parte = strtok(NULL, ",");
	strcpy(v->tracao, parte);

	parte = strtok(NULL, ",");
	v->consumoCidade = atof(parte);

	parte = strtok(NULL, ",");
	v->consumoEstrada = atof(parte);

	parte = strtok(NULL, ",");
	v->co2 = atof(parte);

	parte = strtok(NULL, ",");
	v->turbo = strcmp(parte, "true") == 0;

	parte = strtok(NULL, ",");
	v->dataRegistro = parseData(parte);

	return v;
}

void formatVeiculo(Veiculo v, char *buffer){

	char data[20];
	char combustivel[DEFAULT];

	formatData(v.dataRegistro, data);

	strcpy(combustivel, v.combustivel);

	for (int i = 0; combustivel[i] != '\0'; i++){
		if (combustivel[i] == ';')
			combustivel[i] = ',';
	}

	sprintf(
		buffer,
		 "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",
		 v.id,
		 v.marca,
		 v.modelo,
		 v.ano,
		 v.categoria,
		 combustivel,
		 v.cilindros,
		 v.cilindrada,
		 v.transmissao,
		 v.tracao,
		 v.consumoCidade,
		 v.consumoEstrada,
		 v.co2,
		 v.turbo ? "true" : "false",
		 data
	);
}
//CSV

Veiculo *lerCsv(char *caminhoArquivo, int *n){

	FILE *arquivo;
	Veiculo *array;
	char linha[1024];
	int cap = 10;

	arquivo = fopen(caminhoArquivo, "r");

	if (arquivo == NULL){

		*n = 0;
		return NULL;
	}

	array = (Veiculo *)malloc(cap * sizeof(Veiculo));

	fgets(linha, sizeof(linha), arquivo);

	*n = 0;

	while (fgets(linha, sizeof(linha), arquivo) != NULL){

		if (*n == cap){

			cap *= 2;

			array = (Veiculo *)realloc(
				array,
				cap * sizeof(Veiculo));
		}

		Veiculo *v = parseVeiculo(linha);

		array[*n] = *v;
		(*n)++;

		free(v);
	}

	fclose(arquivo);

	return array;
}

void selectionSort(Veiculo *veiculos, int n){

	for (int i = 0; i < n - 1; i++){
		int min = i;

		for (int j = i + 1; j < n; j++){

			if (strcasecmp(veiculos[j].modelo, veiculos[min].modelo) < 0)
				min = j;
		}

		Veiculo temp = veiculos[i];
		veiculos[i] = veiculos[min];
		veiculos[min] = temp;
	}
}

int main(){

	int n;
	int id;
	int qtd = 0;

	Veiculo *veiculos = lerCsv("/tmp/veiculos.csv", &n);

	Veiculo *selecionados = malloc(n * sizeof(Veiculo));

	do{

		scanf("%d", &id);

		if (id != -1){

			for (int i = 0; i < n; i++){

				if (veiculos[i].id == id){

					selecionados[qtd] = veiculos[i];
					qtd++;

					break;
				}
			}
		}

	} while (id != -1);

	selectionSort(selecionados, qtd);

	for (int i = 0; i < qtd; i++){

		char buffer[1024];

		formatVeiculo(selecionados[i], buffer);
		printf("%s\n", buffer);
	}

	free(selecionados);
	free(veiculos);
}
