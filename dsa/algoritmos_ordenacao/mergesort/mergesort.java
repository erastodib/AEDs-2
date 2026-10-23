import java.util.Random;

public class mergesort{
	public static void main(String[] args){
		
		Random rand = new Random();
		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);

		System.out.println("Before: ");
		printArray(arr);

		mergeSort(arr);

		System.out.println("After: ");
		printArray(arr);

	}

	private static void printArray(int[] arr){
		System.out.print("[ ");
		for (int i : arr)
			System.out.print(i + " ");
		System.out.print("]\n");
	}

	private static void mergeSort(int[] arr){

		int len = arr.length;

		if (len <= 1)
			return;

		int mid = len / 2;
		
		int[] esq = new int[mid];
		int[] dir = new int[len - mid];

		int i = 0; //Esquerda
		int j = 0; //Direita

		for (; i < len; i++){
			if (i < mid)
				esq[i] = arr[i];
			else{
				dir[j] = arr[i];
				j++;
			}
		}

		mergeSort(esq);
		mergeSort(dir);
		
		merge(arr, esq, dir);
	}

	private static void merge(int[] arr, int[] esq, int[] dir){
			
		int l_len = esq.length;
		int r_len = dir.length;
	
		int i = 0; //Merged array
		int l = 0, r = 0; //Left and Right arrays
				 
		while (l < l_len && r < r_len){
			if(esq[l] <= dir[r]){
				arr[i] = esq[l];
				l++;
			}
			else {
				arr[i] = dir[r];
				r++;
			}
			i++;
		}

		while (l < l_len)
			arr[i++] = esq[l++];
		
		while (r < r_len)
			arr[i++] = dir[r++];


	}

}
