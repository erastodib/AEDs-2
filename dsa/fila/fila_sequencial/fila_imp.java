public class fila_imp{

	static class Fila{
		int tam;
		int[] vet;
		int last;

		public Fila(){this(6);}
		public Fila(int tam){
			this.tam = tam;
			vet = new int[tam];
			last = 0;
		}

		public void add(int val ){
			if (last >= tam)
				throw new RuntimeException("Queue overflow!");

			vet[last++] = val;
		}

		public int remove(){
			if (last == 0)
				throw new RuntimeException("Queue underflow!");
			int buffer = vet[0];
			last--;

			for(int i = 0; i < last; i++)
				vet[i] = vet[i+1];

			return buffer;
		
		}

		public int peek(){
			if (last == 0)
				throw new RuntimeException("Empty queue!");

			return vet[0];
			
		}

		public boolean isEmpty(){
			return (last == 0);
		}

		public void print(){

			if (last == 0)
				System.out.println("[ ]");
			else {
				System.out.print("[ ");
				for (int i = 0; i < last; i++){
					System.out.print(vet[i]);

					if (i < last-1)
						System.out.print(", ");
				}
				System.out.println(" ]");

			}
		}

	}


	public static void main(String[] args){
	
		Fila fila = new Fila();

		fila.add(10);
		fila.add(20);
		fila.add(30);
		fila.add(40);

		fila.print();

		fila.remove();
		System.out.println("Primeiro da fila: " + fila.peek());

		fila.remove();
		fila.remove();
		fila.remove();
		fila.remove();
//		fila.print();
//		System.out.println("Primeiro da fila: " + fila.peek());
	
	}

}
