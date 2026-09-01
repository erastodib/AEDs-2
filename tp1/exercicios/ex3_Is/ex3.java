import java.util.Scanner;
import java.text.Normalizer;

/*
Obs.: O enunciado desse exercício é ambíguo em relação ao tratamento de caracteres acentuados, e nenhuma das entradas de pub.in permite a interpretação do tratamento esperado. Decidi implementar a função formatarString() que utiliza a classe Normalizer para decompor os caracteres da string em sua forma canônica equivalente (ex.: 'ã' -> 'a' + '~'), cria uma nova String e percorre a original decomposta, adicionando apenas os caracteres que não pertencem ao bloco de diacríticos da tabela Unicode. Em seguida, a função também transforma todos os caracteres em Uppercase. Enquanto a parte de converter para Uppercase é esperada, a funcionalidade de remover os acentos é completamente opcional e foi feita somente para objetivos de aprendizado, visto que ela usa classes que não estão inclusas no enunciado. Ainda assim, para não descumprir completamente as regras impostas pelo enunciado, fiz questão de implementar a funcionalidade de remover os acentos percorrendo o vetor através de um 'for' ao invés de simplesmente usar str.replaceAll()
*/

public class ex3 {

	public static void main(String[] args){

		String flag = "FIM";
		Scanner scanner = new Scanner(System.in);

		String str = scanner.nextLine(); //Lê strings do usuário até receber a flag "FIM"
		while (!stringComp(str, flag)){
			str = formatarString(str);

			String X1 = checkVogais(str)? "SIM" : "NAO";
			String X2 = checkConsoantes(str)? "SIM" : "NAO";
			String X3 = checkInteiros(str)? "SIM" : "NAO";
			String X4 = checkReais(str)? "SIM" : "NAO";

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
	static boolean checkVogais(String str){
		if (str.length() == 0) return false;

		for (int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if (ch != 'A' && ch != 'E' && ch != 'I' && ch != 'O' && ch !=  'U')
				return false;
		}
		return true;
	}

	static boolean checkConsoantes(String str){
		if (str.length() == 0) return false;

		for (int i = 0; i < str.length(); i++){	
			char ch = str.charAt(i);
			if ((ch < 'A' || ch > 'Z') || 
			(ch  == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'))
					return false;
		}
		return true;		
	}


	static boolean checkInteiros(String str){
		if (str.length() == 0) return false;

		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				return false;
		}

		return true;
	}

	static boolean checkReais(String str){
		if (str.length() == 0) return false;
		int contDec = 0;

		for (int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if (ch == '.' || ch == ',')
				contDec++;
			if (contDec > 1 ||
			(ch != '.' && ch != ',' && 
			(ch < '0' || ch > '9')))
				return false;
		}
		return true;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean stringComp(String str, String flag){
		
		if (str.length() != flag.length())
			return false;
	
		for (int i = 0; i < flag.length(); i++){
			if (str.charAt(i) != flag.charAt(i))
				return false;
		}

		return true;
	}

}
