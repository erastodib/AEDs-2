public class linear_search {

	public static void main (String[] args){
	
	
		//Pesquisa linear = iterar sobre uma coleção um elemento por vez
		//Complexidade: O(n)
		
		//Desvantagens: Lento para grandes conjuntos de dados

		//Vantagens:
		//Rápido para buscas em conjuntos menores
		//O conjunto não precisa estar ordenado
		//Útil para estruturas de dados como Listas Encadeadas, que não têm random access (acesso direto)



		int[] array = {9, 1, 8, 2, 7, 3, 6, 4, 5};

		int index = linearSearch(array, 1);

		if (index != -1)
			System.out.println("Elemento encontrado no indice " + index);
		else System.out.println("Elemento não encontrado");
	}

	private static int linearSearch(int[] array, int value){
		for (int i = 0; i < array.length; i++){
			if (array[i] == value)
				return i;
		}

		return -1;

	}

}
