#include <stdio.h>
#include <stdbool.h>
#define BUFFER 64

bool isAnagrama(char *str);
void formatarString(char *str);
bool stringComp(char *str, char *flag, int flagLen);

int main(){

	char flag[] = "FIM";
	int flagLen = (sizeof(flag) / sizeof(flag[0]) - 1);
	char str[BUFFER];

	fgets(str, BUFFER, stdin);
	while (!stringComp(str, flag, flagLen)){
		formatarString(str);
		printf("%s\n", str);
		fgets(str, BUFFER, stdin);
	}


	
}

void formatarString(char *str){

	for (int i = 0; str[i] != '\0' && str[i] != '\n'; i++){
		char ch = str[i];
		if (ch >= 'A' && ch <= 'Z')
			str[i] = ch + ('a' - 'A');
	}	
}

bool isAnagrama(char *str){
}

bool stringComp(char *str, char *flag, int flagLen){

	if (str[flagLen] != '\n' && str[flagLen] != '\0')
		return false;

	for (int i = 0; i < flagLen; i++){
		if (str[i] != flag[i])
			return false;
	}

	return true;
}

