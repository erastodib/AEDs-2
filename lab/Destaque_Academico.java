import java.util.Scanner;
import java.util.ArrayList;

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

		ArrayList<Aluno> arr = new ArrayList<Aluno>();
        	while (scanner.hasNext()) {

			int n = scanner.nextInt();
			int k = scanner.nextInt();

            		Aluno novoAluno = new Aluno();
            
            		novoAluno.setNome(scanner.next());
		        novoAluno.setNota(scanner.nextInt());
		        novoAluno.setFaltas(scanner.nextInt());
            
          		arr.add(novoAluno);
       		}		
	

	}

}
