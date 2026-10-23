public class old_quicksort{

//	QuickSort = move elementos menores à esquerda de um pivô
//	Divide o array recursivamente em 2 partes
//
//	Complexidade de tempo: Melhor caso: O(n Log n)
//				Caso médio: O(n Log n)
//				Pior caso: O(n²) se já ordenado
//
//	Complexidade espacial = O(log(n)) devido à recursão

	public static void main(String[] args){
	
		int[] arr = {8, 2, 5, 3, 9, 4, 7, 6, 1};

		quickSort(arr, 0, arr.length - 1);

		for (int i : arr)
			System.out.print(i + " ");
	}

	private static void quickSort(int[] arr, int ini, int fim){

		if (fim <= ini) return;
		
		int pivo = partition(arr, ini, fim);
		quickSort(arr, ini, pivo - 1);
		quickSort(arr, pivo + 1, fim);

	}

	private static int partition(int[] arr, int ini, int fim){
	
		int pivo = arr[fim];
		int i = ini - 1;

		for (int j = ini; j <= fim - 1; j++){
			if (arr[j] < pivo){
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		i++;
		int temp = arr[i];
		arr[i] = arr[fim];
		arr[fim] = temp;
		return i;
	}

}
