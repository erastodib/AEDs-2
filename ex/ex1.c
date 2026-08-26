#include <stdio.h>
#define N 8
int sub = 0;

int exA(){
	int a, b, c, i = 0;

	while (i < N){
	a--; b--; c--;
	sub += 3;

	i++;
	} //3n subtracoes

	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			a--; b--;
			sub += 2;
		}	
	}

	return sub;		
}

int exB(){
	int a, b, c, d, e;
	
	for (int i = 0; i < N; i++){
		a--; b--; c--; d--; e--;
		sub += 5;
	}

	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			for (int k = 0; k < N; k++){
				a--; b--; c--; d--;
				sub += 4;
			}
		}
	}
	
	return sub;
}

int exC(){
	int a;

	for (int i = N-1; i > 0; i /= 2){
		a--;
		sub++;
	}

	int j = 0;
	while (j < N){	
		a--;
		sub++;

		j++;
	}

	return sub;
}
int exD(){
	int a, b, c;

	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			for(int k = 0; k < N; k++){
				a++; b++;
				sub += 2;
			}
		}
	}

	for (int i = 0; i < 5; i++){
		c--;
		sub++;
	}
}

int exE(){
	int a, b, c, d, e;

	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			a--; b--; c--; d--; e--;
			sub += 5;	
			for(int k = 0; k < N; k++){
				for(int l = 0; l < N; l++){
					for(int m = 0; m < 9; m++){
						a--;
						sub++;
					}
				}
			}
		}
	}

	for (int i = 0; i < N; i+=2){
		a--;
		sub++;
	}
}

int exF(){
	
}
int main(){
	char sel;

	printf("Exercicios:\n"
		"a) 3n + 2n²\n"
		"b) 5n + 4n³\n"
		"c) lg(n) + n\n"
		"d) 2n³ + 5\n"
		"e) 9n^4 + 5n² + n/2\n"
		"f) lg(n) + 5 lg(n)\n");

	printf("Ex.: ");
	scanf("%c", &sel);
	
	switch(sel){
		case 'a':
			sub = exA();
			printf("Sub.: %d\n", sub);
			break;
		case 'b':
			sub = exB();
			printf("Sub.: %d\n", sub);
			break;
		case 'c':
			sub = exC();
			printf("Sub.: %d\n", sub);
			break;
		case 'd':
			sub = exD();
			printf("Sub.: %d\n", sub);
			break;
		case 'e':
			sub = exE();
			printf("Sub.: %d\n", sub);
			break;
		case 'f':
			sub = exF();
			printf("Sub.: %d\n", sub);
			break;
	}

}
