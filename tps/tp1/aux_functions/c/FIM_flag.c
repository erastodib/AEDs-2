#include <stdio.h>
#include <stdbool.h>
#define BUFFER 128

	bool stringComp(char *str, char *flag, int len);

	int main(){
	
		char flag[] = "FIM";
		char str[BUFFER];
		
		int len = ((sizeof(flag) / sizeof(flag[0])) - 1);

		while (!stringComp(str, flag, len)){
			fgets(str, BUFFER, stdin);
		}

	}

	bool stringComp(char *str, char *flag, int len){

		if (str[len] != '\n' && str[len] != '\0')
			return false;
		
		for (int i = 0; i < len; i++){
			if (str[i] != flag[i])
				return false;
		}

		return true;
	}
