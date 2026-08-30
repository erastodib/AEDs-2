import java.util.Scanner;

public class ex1{
	
	public static String ciframento(String str){
		String encr = "";

		for(int i = 0; i < str.length(); i++){
			encr += (char) (str.charAt(i)+3);
		}

		return encr;
	}


	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();

		String encr = ciframento(str);

		System.out.println(encr);
		scanner.close();

	}
}






