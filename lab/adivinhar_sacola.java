import java.util.Scanner;

public class adivinhar_sacola {

	public static void main(String[] args){
			
		
	}

	//

}


class Lista {
	int[] vet;
	int n;

	Lista(){	this(6);}
	Lista (int len){
		vet = new int[len];
		n = 0;
	}

	void inserirInicio(int x)throws Exception{
	
		if (n >= vet.length())
			throw new Exception("Erro!");

		//levar elementos para o fim do vetor
		for (int i = n; i > 0; i--){
			vet[i] = vet[i-1];
		}

		vet[0] = x;
		n++;
	
	}
	void inserirFim(int x)throws Exception{
		
		if (n >= vet.length())
			throw new Exception("Erro!");

		vet[n] = x;
		n++;
	}


	void inserir(int x, int pos)throws Exception{
	
		if (n >= vet.length() || pos < 0 || pos > n)
			throw new Exception("Erro!");

		//levar elementos para o fim do vetor
		for (int i = n; i > pos; i--){
			vet[i] = vet[i-1];
		}
		
		vet[pos] = x;
		n++;
	}
	int removerInicio()throws Exception{
	
		if (n == 0)
			throw new Exception("Erro!");

		int resp = vet[0];
		n--;

		for(int i = 0; i < n; i++){
			vet[i] = vet[i+1];
		}

		return resp;
	}
	int removerFim()throws Exception{
		if (n == 0)
			throws new Exception("Erro!");

		return vet[--n];
	}
	int remover(int pos){}
	void mostrar(){}

}
