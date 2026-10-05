import java.util.*;

public class fila_prioridade_bi_1{

	public static void main (String[] args){
	
	//	Priority Queue = Estrutura de dados FIFO que apresenta os elementos de maior prioridade
	//	antes dos elementos de menor prioridade
		

		//Queue<Double> queue = new LinkedList<>();
		//Queue<Double> queue = new PriorityQueue<>();
		Queue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());

		queue.offer(3.0);
		queue.offer(2.5);
		queue.offer(4.0);
		queue.offer(1.5);
		queue.offer(2.0);

		while(!queue.isEmpty()){
			System.out.println(queue.poll());
		}



	}

}
