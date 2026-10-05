import java.util.*;

public class fila_prioridade_bi_2{

	public static void main(String[] args){
	
		Queue<String> queue = new PriorityQueue<>(Collections.reverseOrder());

		queue.offer("B");
		queue.offer("C");
		queue.offer("A");
		queue.offer("F");
		queue.offer("D");


		while (!queue.isEmpty())
			System.out.println(queue.poll());
		
	}

}
