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

// *********************************************************************************************
//	Lista Encadeada = armazena Nós em 2 partes (dados + endereço)
// 	Os Nós são localizados em endereços de memória não-consecutivos
// 	Os elementos são ligados através do uso de ponteiros
//
// 				Lista Simplesmente Encadeada:
//
// 	       Nó		      Nó 		     Nó
// 	[dado  |  endereço] -> [dado  |  endereço] -> [dado  |  endereço]
//
//
// 				Lista Duplamente Encadeada:
//
// 		     Nó		                     Nó 		             Nó
// 	[endereço | dado | endereço] -> [endereço | dado | endereço] -> [endereço | dado | endereço]
//
//
//	Vantagens:
//	1. Estrutura de Dados Dinâmica (aloca memória necessária enquanto executa)
//	2. Métodos de inserir e deletar têm complexidade O(1)
//	3. Desperdiça pouca memória
//
//	Desvantagens:
//	1. Usa mais memória (ponteiro adicional)
//	2. Sem acesso aleatório de elementos (não possui índice [i])
//	3. Acessar/pesquisar elementos consume mais tempo O(n)
//
//	Aplicações:
//	1. Implementar pilhas/filas
//	2. Navegação por GPS
//	3. Playlists de música
//
// *********************************************************************************************
