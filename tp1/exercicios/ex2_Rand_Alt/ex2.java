import java.util.Scanner;
import java.util.Random;

public class ex2 {
	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		random.setSeed(4); //Seed para o objeto random
		String flag = "FIM";
	
		String str = scanner.nextLine();
		String encr;
		
		//Esse loop gera dois caracteres aleatorios, le uma string e substitui toda ocorrencia do primeiro caractere pelo segundo, até receber a string "FIM'"
		while (!stringComp(str, flag)){
			char a = randChar(random);
			char b = randChar(random);

			encr = "";
		
			for (int i = 0; i < str.length(); i++){
				if (str.charAt(i) == a)
					encr += b;
				else
					encr += str.charAt(i);
			}
	
			System.out.println(encr);
			str = scanner.nextLine();
		}
	}
	
	//Esse metodo cria um caractere, atribui uma letra aleatoria de a-z e o retorna
	static char randChar(Random random){
		char ch = (char)('a' + Math.abs(random.nextInt()) % 26);
		return ch;
	}

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
