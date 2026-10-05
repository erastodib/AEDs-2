public class binary_search{

	//Pesquisa binária = algoritmo ded pesquisa que encontra a posição de um valor x em um arrat ordenado. 
	//Metade do array é eliminado a cada passo, tornando sua complexidade logaritmica
	


	public static void main(String[] args){
		
		int array[] = new int[1000000];
		int target = 777777;

		for (int i = 0; i < array.length; i++)
			array[i] = i;
	

		int index = binarySearch(array, target);

		if (index != -1)
			System.out.println("Valor encontrado no indice " + index);
		else System.out.println("Valor nao encontrado!");
	}



	private static int binarySearch(int[] array, int target){
		int low = 0;
		int high = array.length - 1;

		while (low <= high){
		
			//int mid = (low + high) / 2
			int mid = low + (high - low) / 2; //Ambas as expresões produzem o mesmo resultado, mas a segunda é mais segura para evitar possíveis overflows onde low+high excede o valor máximo de int

			System.out.println("Meio: " + mid);

			int value = array[mid];

			if (value < target)
				low = mid + 1;
			else if (value > target)
				high = mid - 1;
			else return mid;
		}

		return -1;
	
	}


}
