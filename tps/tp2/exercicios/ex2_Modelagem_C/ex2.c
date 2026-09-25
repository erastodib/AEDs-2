#include <stdio.h>
#include <stdbool.h>
#include <string.h>

typedef struct{

	int dia;
	int mes;
	int ano;

}Data;

void parseData(Data *data, char *dataStr){ //parseData(&dataRegistro, "yyyy-mm-dd");
	
	if (dataStr != NULL && dataStr[0] != '\0'){
			char *parte = strtok(dataStr, "-");
			data->ano = atoi(parte);

			parte = strtok(NULL, "-");
			data->mes = atoi(parte);

			parte = strtok(NULL, "-");
			data->dia = atoi(parte);
	}
}


typedef struct{
	
	int id;
	char[] marca;
	char[] modelo;
	int ano;
	char[] categoria;
	char[] combustivel;
	int cilindros;
	float cilindrada;
	char[] transmissao;
	char[] tracao;
	float consumoCidade;
	float consumoEstrada;
	float co2;
	bool turbo;
	Data dataRegistro;


}Veiculo;
