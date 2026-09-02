#include <stdio.h>
#define BUFFER 64 //Tamanho padrão das Strings utilizadas no programa

int stringComp(char *str, char *flag, int flagLen);
char *inverteString(char *str, char *inv);

int main(){

	char flag[] = "FIM"; //flag para interromper o loop de entrada
	int flagLen = ((sizeof(flag) / sizeof(flag[0])) -1);

	char str[BUFFER];
	char inv[BUFFER];

	/*Lê uma String e imprime sua String inversa, obtida através de inverteString(), até receber a flag "FIM"*/
	fgets(str, sizeof(str), stdin);
	while (stringComp(str, flag, flagLen) != 1){
		inverteString(str, inv);
		printf("%s\n", inv);
		fgets(str, sizeof(str), stdin);
	}

}

char *inverteString(char *str, char *inv){

	//Percorre 'str' para obter seu tamanho real
	int len = 0;
	while (str[len] != '\0')
		len++;
	//Remove o caractere '\n' de str, caso exista, e atualiza seu tamanho
	if (len > 0 && str[len - 1] == '\n'){
		str[len - 1] = '\0';
		len--;
	}

	int i = len - 1 , j = 0; //i = len -1 pois str[len] = '\0'

	//Percorre str de trás para frente, e preenche inv de frente pra trás
	while (i >= 0){
		inv[j] = str[i];
		i--; j++;
	}

	inv[j] = '\0'; //Adiciona o terminador nulo ao final de inv
	return inv;
}

int stringComp(char *str, char *flag, int flagLen){

	if (str[flagLen] != '\n' && str[flagLen] != '\0') //Retorna 0 caso str e flag tenham tamanhos diferentes
		return 0;

	for (int i = 0; i < flagLen; i++){ //Percorre str caractere por caractere e retorna 0 caso algum caractere seja diferente de flag no mesmo índice
		if (str[i] != flag[i])
			return 0;
	}

	return 1; //Caso contrário, retorna 1
}
