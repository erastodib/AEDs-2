public class bubblesort {

	//Bubblesort = algoritmo de ordenação quadrático onde pares de elementos adjacentes são comparados, e seus elementos são trocados se não estiverem em ordem

	public static void main(String[] args){
	
		int array[] = {9, 1, 8, 2, 7, 3, 6, 4, 5};

		bubbleSort(array);

		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	
	}


	static void bubbleSort(int array[]){

		for (int i = 0; i < array.length - 1; i++){
			for (int j = 0; j < array.length - i - 1; j++){
				if (array[j] > array[j+1])
					swap(array, j, j+1);
			}
		}
	}


	static void swap(int[] array, int a, int b){
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}
