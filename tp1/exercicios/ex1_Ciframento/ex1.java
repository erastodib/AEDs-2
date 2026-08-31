import java.util.Scanner;

public class ex1{
	
	public static void main(String[] args){

		String flag = "FIM"; //Flag utilizada no metodo stringComp para parar o programa quando receber a string "FIM"
		String encr;
		Scanner scanner = new Scanner(System.in);
		
		String str = scanner.nextLine(); //Primeira leitura antes do loop para impedir que a leitura da flag entre no loop e gere saida

		//Loop recebe strings e as criptografa deslocando os caracteres 3 valores para frente na tabela ASCII 
		while (!stringComp(str, flag)){
			encr = ciframento(str);	
			System.out.println(encr);
			str = scanner.nextLine();
		}

		scanner.close();

	}
	
	//Essa funcao retorna 'true' caso duas strings sejam iguais e 'false' caso sejam diferentes
	static boolean stringComp(String str, String flag){

                if (str.length() != flag.length()) //Caso o tamanho das strings seja diferente, nao faz sentido compará-las caractere a caractere, pois já são automaticamente diferentes
                        return false;

                for (int i = 0; i < flag.length(); i++){ //Caso as strings tenham o mesmo tamanho, esse loop compara caractere a caractere
                        if (str.charAt(i) != flag.charAt(i))
                                return false;
                }

                return true;
        }


	//Esse metodo desloca cada caractere da string 3 posições à frente e retorna a string cifrada
        public static String ciframento(String str){
                String encr = "";

                for(int i = 0; i < str.length(); i++){
                        encr += (char) (str.charAt(i)+3);
                }

                return encr;
        }
}






