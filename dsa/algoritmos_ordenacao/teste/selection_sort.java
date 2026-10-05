public class selection_sort{

	public static void main(String[] args){
	
		int array[] = {7,1,4,8,5,2,3,6,9,4,8,3,24,9,9,3,2,7,3,9};

		selectionSort(array);

		for (int i : array)
			System.out.print(i + " ");
	
	}

	static void selectionSort(int array[]){
	
		for (int i = 0; i < array.length - 1; i++){
			int minIx = i;

			for (int j = i+1; j < array.length; j++){
				if (array[j] < array[minIx])
					minIx = j;
			}

			swap(array, i, minIx);
		}

	}


	static void swap(int array[], int a, int b){
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}


}
