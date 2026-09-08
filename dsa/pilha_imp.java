public class pilha_imp{

	static class Pilha{
		int tam;
		int[] vet;
		int top;


		public Pilha(){this(6);}
		public Pilha(int tam){
			this.tam = tam;
			vet = new int[tam];
			top = -1;
		}

		public void push(int val){
			if (top == tam - 1)
				throw new RuntimeException("Stack overflow!");
			
			vet[++top] = val;
		}

		public int pop(){
			if (top == -1)
				throw new RuntimeException("Stack underflow!");

			return vet[top--];
		}

		public int peek(){
			if (top == -1)
				throw new RuntimeException("Pilha vazia!");
			
			return vet[top];
		}

		public boolean isEmpty(){
			return (top == -1);
		}


	}

	public static void main(String[] args){
	
		Pilha pilha = new Pilha(6);

		pilha.push(10);
		pilha.push(20);

		System.out.println("Topo da pilha: " + pilha.peek());

		pilha.push(30);
		
		pilha.pop();
		pilha.pop();
		pilha.pop();
		pilha.pop();
		pilha.pop();

		System.out.println("Pilha vazia? " + pilha.isEmpty());

	}
}
