import java.util.Scanner;

public class FIM_flag {

	public static void main(String[] args){

	String flag = "FIM";
	Scanner scanner = new Scanner(System.in);

	String str = scanner.nextLine();
	while (!stringComp(str, flag)){
		str = scanner.nextLine();
	}

}


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
