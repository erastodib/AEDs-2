#include <stdio.h>

int main(){
	int a = 1, n = 16, j = 0;
	
	for (int i = n; i > 0; i/=2){
		a*=2;
		j++;	
	}
	printf("Mult.: %d\n", j);

	return 0;
}
