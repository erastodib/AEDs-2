#include <stdio.h>

	int stringComp(char *str, char *flag, int len);

	int main(){
	
		char flag[] = {'F', 'I', 'M'};
		char str[128];
		
		int len = (sizeof(flag) / sizeof(flag[0]));

		do {
			fgets(str, sizeof(str), stdin);
		} while (stringComp(str, flag, len) != 1);

	}

	int stringComp(char *str, char *flag, int len){

		if (str[len] != '\n' && str[len] != '\0')
			return 0;
		
		for (int i = 0; i < len; i++){
			if (str[i] != flag[i])
				return 0;
		}

		return 1;
	}
