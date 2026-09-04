import java.util.Scanner;

public class aula_02 {
		static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args){
		
        	//int ini = scanner.nextInt();
                
	        System.out.print("n = ");
	        int n = scanner.nextInt();
	        boolean active = true;
		
		int x;

		while (active){
		    	System.out.println(
		                            "=== MENU ===\n" +
		                            "[1] - Somatorio Iterativo\n" +
		                            "[2] - Somatorio Recursivo\n" +
		                            "[3] - Somatorio Gauss"
			);

			char op = scanner.next().charAt(0);
		
			switch(op){

			       	case '1':
				        x = somatorioIterativo(n);
					System.out.println(x);
					active = activePrompt();
					break;
		          	case '2':
			                x = somatorioRecursivo(n);
			                System.out.println(x);
					active = activePrompt();
					break;
			   	case '3':
			                x = somatorioGauss(n);
			                System.out.println(x);
					active = activePrompt();
					break;
		            	default:
		               	 	System.out.println("Entrada invalida!");
			                break;
			}
        	}
                         
	}

	static int somatorioIterativo(int n){
		int soma = 0;
		for (int i = 0; i <= n; i++)
			soma += i;
		return soma;

	}

	static int somatorioRecursivo(int n){
	
		if (n <= 0)
			return 0;

		return n + somatorioRecursivo(n-1);
	
	}

	static int somatorioGauss(int n){
		if (n <= 0)
			return 0;

		return n * (n + 1) / 2;
	}

	static boolean activePrompt(){
		scanner.skip("\n");	
		System.out.println("Aperte ENTER para continuar...");
		scanner.nextLine();

		System.out.println("Deseja realizar uma nova operação? y/N");

		char yn = Character.toLowerCase(scanner.next().charAt(0));

		return yn == 'y';
	}

/*	
    static int somatorioIterativo(int ini, int fim){
	
		int soma = 0;
		
		for (; ini <= fim; ini++)
			soma += ini;
	
		return soma;
	}
*/



/*        System.out.println(
                            "=== MENU ===\n"
                            "[1] - Somatorio Iterativo\n"
                            "[2] - Somatorio Recursivo\n"
                            "[3] - Somatorio Gauss"
        );
	static int somatorioRecursivo(int ini, int fim){

		if (ini > fim)
			return 0;
	
		return ini + somatorioRecursivo(ini + 1, fim);
	
	}
*/
	

}
