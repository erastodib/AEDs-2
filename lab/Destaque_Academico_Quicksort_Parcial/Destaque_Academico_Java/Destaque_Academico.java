import java.util.Scanner;

class Aluno{
	private String nome;
	private int nota;
	private int faltas;


//GETTERS

	public String getNome(){return this.nome;}
	public int getNota(){return this.nota;}
	public int getFaltas(){return this.faltas;}


//SETTERS

	public void setNome(String nome){this.nome = nome;}
	public void setNota(int nota){this.nota = nota;}
	public void setFaltas(int faltas){this.faltas = faltas;}
}

public class Destaque_Academico{

	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextInt()){

			int n = scanner.nextInt(); //qtd alunos
			int k = scanner.nextInt(); //k primeiros colocados

			Aluno[] arr = new Aluno[n];

			for (int i = 0; i < n; i++){
            		
				Aluno novoAluno = new Aluno();
			        novoAluno.setNota(scanner.nextInt());
		        	novoAluno.setFaltas(scanner.nextInt());
	            		novoAluno.setNome(scanner.nextLine().trim());
            
				arr[i] = (novoAluno);
	       		}		
			
			quicksort_parcial(arr, 0, n - 1, k);

			for (int i = 0; i < k; i++)
				System.out.println(arr[i].getNota() + " " + arr[i].getFaltas() + " " +  arr[i].getNome());

		}
	
		scanner.close();
	}

	private static void swap(Aluno[] arr, int i, int j){
		Aluno temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static int part(Aluno arr[], int low, int high){
		Aluno pivo = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++){
			if (
			arr[j].getNota() > pivo.getNota() ||
			(arr[j].getNota() == pivo.getNota() && arr[j].getFaltas() < pivo.getFaltas()) ||
			(arr[j].getNota() == pivo.getNota() && arr[j].getFaltas() == pivo.getFaltas() && arr[j].getNome().compareTo(pivo.getNome()) < 0)
			){



				i++;
				swap(arr, i, j);
			}
		}

		swap(arr, i + 1, high);
		return i + 1;
	}

	private static void quicksort_parcial(Aluno arr[], int low, int high, int k){
		if (low < high){
			int p_index = part(arr, low, high);
			quicksort_parcial(arr, low, p_index - 1, k);//ordena lado esquerdo
			if (p_index < k - 1)
				quicksort_parcial(arr, p_index + 1, high, k);

		}
	}

}
