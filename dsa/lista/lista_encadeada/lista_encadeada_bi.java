import java.util.*;

public class lista_encadeada_bi{

	public static void main(String[] args){
		
		LinkedList<String> linkedList = new LinkedList<String>();

	/* Pode ser tratada como pilha (FILO)

		linkedList.push("A");
		linkedList.push("B");
		linkedList.push("C");
		linkedList.pop();
	*/

	/* Ou fila (FIFO:)
	*/

		linkedList.offer("A");
		linkedList.offer("B");
		linkedList.offer("C");
		linkedList.offer("D");
		linkedList.offer("F");
		//linkedList.poll();
		
		linkedList.add(4, "E");
		//linkedList.remove("E");
		//System.out.println(linkedList.indexOf("F"));

		System.out.println(linkedList.peekFirst());
		System.out.println(linkedList.peekLast());
		linkedList.addFirst("0");
		linkedList.addLast("G");

		System.out.println(linkedList);

		String first = linkedList.removeFirst();
		String last = linkedList.removeLast();

		System.out.println(linkedList + "\nF: " + first + "\nF: "+ last);
	}

}
