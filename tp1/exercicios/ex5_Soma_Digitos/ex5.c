#include <stdio.h>
#include <stdlib.h> //Usado para a função abs()
#define ENTRADAS_MAX 50 //O arquivo pub.in não parecia ter flags (ex.: "FIM") como os outros exercícios, então simplesmente limitei a quantidade de entradas para o número de linhas do pub.in

int somaDigitos(int n);

int main(){

	int n, somaD;

	for (int i = 0; i < ENTRADAS_MAX; i++){ //Lê 50 valores inteiros e imprime a soma de seus dígitos
		scanf("%d", &n);
		n = abs(n); //Atribui o valor absoluto de n para tratar valores negativos
		somaD = somaDigitos(n);
		printf("%d\n", somaD);
	}
}

int somaDigitos(int n){
	int soma = 0;
	int resto;
	
	//Obtém o resto da divisão de n por 10 para obter cada dígito separado de n, fazendo divisão de inteiros em seguida para "limpar" o dígito já utilizado
	for (int i = 0; n > 0; i++){
		resto = n % 10;
		soma += resto;
		n /= 10;
	}

	return soma;
	
}
