/* Lista é um Tipo Abstrato de Dados (TAD) que permite inserir e remover elementos em qualquer posição 

   Métodos:

	- Construtores

	- Inserção de elementos:
		* void inserirInicio(elemento)
		* void inserirFim(elemento)
		* void inserir(elemento, posição)

	- Remoção de elementos:
		* elemento removerInicio()
		* elemento removerFim()
		* elemento remover(posição)
	
	- Mostrar, pesquisar, ordenar,...

*/



public class lista_tad{

	static class Lista {
		
		int tam;
		int[] vet;
		int fim;

		public Lista(){this(6);}
		public Lista(int tam){
			this.tam = tam;
			vet = new int[tam];
			fim = 0;
		}

		public void inserirInicio(int x){
			if (fim >= tam)
				throw new RuntimeException("List overflow!");

			for (int i = fim; i > 0; i--)
				vet[i] = vet[i-1];

			vet[0] = x;
			fim++;
		}

		public void inserirFim(int x){
		
			if (fim >= tam)
				throw new RuntimeException("List overflow!");

			vet[fim++] = x;
		
		}

		public void inserir(int x, int pos){
		
			if (fim >= tam)
				throw new RuntimeException("List overflow!");
			if (pos < 0 || pos > fim)
				throw new RuntimeException("Index out of bounds!");

			for (int i = fim; i >= pos; i--)
				vet[i] = vet[i-1];

			vet[pos] = x;
			fim++;	
		}

		public int removerInicio(){
		
			if (fim == 0)
				throw new RuntimeException("List underflow!");

			int buffer = vet[0];
			fim--;

			for (int i = 0; i < (fim); i++)
				vet[i] = vet[i+1];
			
			return buffer;
		}

		public int removerFim(){

			if (fim == 0)
				throw new RuntimeException("List underflow!");

			return vet[--fim];
		
		}

		public int remover(int pos){
		
			if (fim == 0)
				throw new RuntimeException("List underflow!");

			if (pos < 0 || pos >= fim)
				throw new RuntimeException("Index out of bounds!");

			int buffer = vet[pos];
			fim--;

			for (int i = pos; i < fim; i++)
				vet[i] = vet[i+1];
		
			return buffer;
		}

		public int peek(int pos){
		
			if (fim == 0)
				throw new RuntimeException("Empty list!");

			if (pos < 0 || pos >= fim)
				throw new RuntimeException("Index out of bounds!");

			return vet[pos];
		}

		public boolean isEmpty(){
			return (fim == 0);
		}

		public void print(){
		
			if (fim == 0)
				System.out.println("[ ]");
			else {

				System.out.print("[ ");
				for (int i = 0; i < fim; i++){
					System.out.print(vet[i]);
					
					if(i < fim-1)
						System.out.print(", ");

				}
				System.out.println(" ]");
			}
		}
	
	}


	public static void main(String[] args){
		
		Lista lista = new Lista();

		System.out.println("Lista vazia? " + lista.isEmpty());
		lista.print();
		lista.inserirInicio(1);
		lista.inserirInicio(2);
		lista.inserirFim(17);
		lista.inserir(16, 1);
		lista.removerFim();
		System.out.println(lista.peek(0));
		lista.print();
	
	
	}

}
