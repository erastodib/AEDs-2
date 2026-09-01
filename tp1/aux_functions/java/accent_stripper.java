import java.util.Scanner;
import java.text.Normalizer;


public class accent_stripper {

	public static void main(String[] args){
	
		Scanner scanner = new Scanner(System.in);
		
		String str = scanner.nextLine();
		String new_str = stripAccents(str);

		System.out.println("Original: " + str + "\nFormatado: " + new_str);
	
	}

	static String stripAccents(String str){
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return str;
	}

}
