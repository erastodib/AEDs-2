import java.util.Scanner;

public class stringMethods {
	public static void main(String args[]){

	String email;

	Scanner scanner = new Scanner(System.in);
	
	System.out.print("Enter your email: ");
	email = scanner.nextLine();
	
	String username = email.substring(0, email.indexOf("@"));
	String domain = email.substring(email.indexOf("@") + 1);

	System.out.println("Email: " + email + "\nUsername: " + username + "\nDomain: " + domain);
	
	scanner.close();

}

}
