public class pilha_imp{

	static class Pilha{
		int tam;
		int[] vet;
		int top;


		public Pilha(){this(6);}
		public Pilha(int tam){
			this.tam = tam;
			vet = new int[tam];
			top = 0;
		}

		public void push(int val){
			if (top >= tam)
				throw new RuntimeException("Stack overflow!");
			
			vet[top++] = val;
		}

		public int pop(){
			if (top == 0)
				throw new RuntimeException("Stack underflow!");

			return vet[--top];
		}

		public int peek(){
			if (top == 0)
				throw new RuntimeException("Empty stack!");
			
			return vet[top-1];
		}

		public boolean isEmpty(){
			return (top == 0);
		}

		public void print(){
			
			if (top == 0)
				System.out.println("[ ]");

			else {
				System.out.print("[ ");
				for (int i = 0; i < top; i++ ){
					System.out.print(vet[i]);
					
					if (i < top-1)
						System.out.print(", ");
				}
				System.out.println(" ]");
			
			}
		}


	}

	public static void main(String[] args){
	
		Pilha pilha = new Pilha(6);
		
		pilha.print();

		pilha.push(10);
		pilha.push(20);

		System.out.println("Topo da pilha: " + pilha.peek());
		pilha.push(30);
		pilha.print();	
	/*	pilha.pop();
		pilha.pop();
		pilha.pop();
		pilha.pop();
		pilha.pop();

		System.out.println("Pilha vazia? " + pilha.isEmpty());*/

	}
}
