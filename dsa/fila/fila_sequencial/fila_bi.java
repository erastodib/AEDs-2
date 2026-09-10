import java.util.Queue;
import java.util.LinkedList;

public class fila_bi{
	
	public static void main(String[] args){
	
//		Fila = Estrutura de dados linear FIFO (First-In First-Out)
//		Projetada para armazenar elementos antes do processamento
		
//		inserir = add()      ou offer()
//		remover = remove()   ou poll()
//		examinar = element() ou peek()
//		Sendo que os métodos da segunda coluna são mais usados por não lançarem Exceptions

		Queue<String> queue = new LinkedList<String>();

		//System.out.println(queue.isEmpty());

		queue.offer("Karen");
		queue.offer("Chad");
		queue.offer("Steve");
		queue.offer("Harold");
		
		//System.out.println(queue.size());
		//System.out.println(queue.contains("Harold"));

		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		queue.peek();
			
		System.out.println(queue);
	}

}

//		Exemplos práticos de aplicações:
//
//		1. Buffer de teclado (as letras devem aparacer na tela na ordem em que foram pressionadas)
//		2. Fila de impressora (Impressões devem ser realizadas na ordem correta)
//		3. Usada em LinkedLists, PriorityQueues, Breadth-first search
