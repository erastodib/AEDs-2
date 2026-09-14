import java.util.Scanner;
import java.text.Normalizer;

/*
Obs.: O enunciado desse exercício é ambíguo em relação ao tratamento de caracteres acentuados, e nenhuma das entradas de pub.in permite a interpretação do tratamento esperado. Decidi implementar a função formatarString() que utiliza a classe Normalizer para decompor os caracteres da string em sua forma canônica equivalente (ex.: 'ã' -> 'a' + '~'), cria uma nova String e percorre a original decomposta, adicionando apenas os caracteres que não pertencem ao bloco de diacríticos da tabela Unicode. Em seguida, a função também transforma todos os caracteres em Uppercase. Enquanto a parte de converter para Uppercase é esperada, a funcionalidade de remover os acentos é completamente opcional e foi feita somente para objetivos de aprendizado, visto que ela usa classes que não estão inclusas no enunciado. Ainda assim, para não descumprir completamente as regras impostas pelo enunciado, fiz questão de implementar a funcionalidade de remover os acentos percorrendo o vetor através de um 'for' ao invés de simplesmente usar str.replaceAll()
*/

public class ex3 {

	public static void main(String[] args){

		String flag = "FIM";
		String X1, X2, X3, X4;
		Scanner scanner = new Scanner(System.in);

		String str = scanner.nextLine(); //Lê strings do usuário até receber a flag "FIM"
		while (!stringComp(str, flag)){
			str = formatarString(str);

			X1 = checkVogais(str)? "SIM" : "NAO";
			X2 = checkConsoantes(str)? "SIM" : "NAO";
			X3 = checkInteiros(str)? "SIM" : "NAO";
			X4 = checkReais(str)? "SIM" : "NAO";

			System.out.printf("%s %s %s %s\n", X1, X2, X3, X4);

			str = scanner.nextLine();
		}
		scanner.close();
	}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/* Essa função utiliza a classe Normalizer para decompor os caracteres da string em sua forma canônica equivalente (ex.: 'ã' -> 'a' + '~'), cria uma nova String e percorre a original decomposta, adicionando apenas os caracteres que não pertencem ao bloco de diacríticos da tabela Unicode. Em seguida, a função também transforma todos os caracteres em Uppercase subtraindo 32 na tabela ASCII caso estejam no intervalo (a-z)*/

	static String formatarString(String str){
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		String str_new = "";

		for (int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);

			if (ch < '\u0300' || ch > '\u036F'){
				if (ch >= 'a' && ch <= 'z')
					ch = (char)(ch - 32);

				str_new += ch;
			}
		}

		return str_new;
	
	}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//A função abaixo verifica se a String recebida é composta apenas por vogais
	static boolean checkVogais(String str){
		if (str.length() == 0) return false; //Retorna 'false' se a String estiver vazia

		for (int i = 0; i < str.length(); i++){ //Percorre a string caractere por caractere
			char ch = str.charAt(i); //Variável auxiliar para evitar a repetição da função charAt()
			if (ch != 'A' && ch != 'E' && ch != 'I' && ch != 'O' && ch !=  'U')
				return false; //Retorna 'false' caso não seja uma vogal
		}
		return true; //Caso contrário, retorna 'true'
	}


	//A função abaixo verifica se a String recebida é composta apenas por consoantes
	static boolean checkConsoantes(String str){
		if (str.length() == 0) return false; //Retorna 'false' se a String estiver vazia

		for (int i = 0; i < str.length(); i++){	//Percorre a string caractere por caractere
			char ch = str.charAt(i); //Variável auxiliar para evitar a repetição da função charAt()
			if ((ch < 'A' || ch > 'Z') ||
			(ch  == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'))
					return false; //Retorna 'false' se o caractere estiver fora do intervalo (A-Z) ou se for uma vogal
		}
		return true; //Caso contrário, retorna 'true'
	}

	//A função abaixo verifica se a String recebida é composta apenas por números inteiros
	static boolean checkInteiros(String str){
		if (str.length() == 0) return false; //Retorna 'false' se a String estiver vazia

		for (int i = 0; i < str.length(); i++){ //Percorre a string caractere por caractere
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				return false; // Retorna 'false' se o caractere estiver fora do intervalo (0-9)
		}

		return true; //Caso contrário, retorna 'true'
	}


	/* A função abaixo verifica se a String recebida é composta apenas por números reais.
	O arquivo pub.out considera 'true' entradas como '.123'; ',123'; '123.'; '123,' e não parece considerar marcadores de milhar. Logo, a função foi implementada simplesmente limitando o caractere (','/'.' a uma única ocorrencia e checando se os outros caracteres são números)*/

	static boolean checkReais(String str){
		if (str.length() == 0) return false; //Retorna 'false' se a String estiver vazia
		int contDec = 0; //Contador de marcadores decimais (. ou ,). Para ser um número real válido, a sring só pode ter uma ocorrência de marcador decimal

		for (int i = 0; i < str.length(); i++){
			char ch = str.charAt(i); //Variável auxiliar para evitar a repetição da função charAt()
			if (ch == '.' || ch == ',')
				contDec++; //Verifica se a string possui '.' ou ',' e incrementa contDec em 1
			if (contDec > 1 ||
			(ch != '.' && ch != ',' && 
			(ch < '0' || ch > '9')))
				return false; //Retorna 'false' caso haja mais de um marcador decimal OU se o caractere não estiver no intervalo (0-9) (excluindo . e ,)
		}
		return true; //Caso contrário, retorna 'true'
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Essa funcao retorna 'true' caso duas strings sejam iguais e 'false' caso sejam diferentes
        static boolean stringComp(String str, String flag){

                if (str.length() != flag.length()) //Caso o tamanho das strings seja diferente, nao faz sentido compará-las caractere a caractere, pois já são automaticamente diferentes
                        return false;

                for (int i = 0; i < flag.length(); i++){//Caso as strings tenham o mesmo tamanho, esse loop compara caractere a caractere
                        if (str.charAt(i) != flag.charAt(i))
                                return false;
                }

                return true;
        }

}
