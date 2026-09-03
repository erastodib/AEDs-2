import java.util.Scanner;

public class aula_02 {
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
        	//int ini = scanner.nextInt();
                
	        System.out.println("n = ");
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

			char op = scanner.nextChar();
		
			switch(op){

			       	case '1':
				        x = somatorioIterativo(n);
					System.out.println(x);
					break;
		          	case '2':
			                x = somatorioRecursivo(n);
			                System.out.println(x);
					break;
			   	case '3':
			                x = somatorioGauss(n);
			                System.out.println(x);
					break;
			      	case '0':
                			active = false;
					break;
		            	default:
		               	 	System.out.println("Entrada invalida!");
			                break;
			}
        	}
                         
	}

	static int somatorioIterativo(int n){
		return somatorioIterativo(1, n);
	}

	static int somatorioRecursivo(int n){
	
		if (n <= 0)
			return 0;

		return n + somatorioRecursivo(n-1);
	
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
