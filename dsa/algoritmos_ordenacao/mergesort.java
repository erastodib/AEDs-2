public class mergesort{


	// MergeSort = divide o array em 2 recursivamente, ordena, recombina
	// Complexidade de tempo: O(n Log n)
	// Complexidade Espacial: O(n)

	public static void main(String[] args){

		int[] arr = {8, 2, 5, 3, 4, 7, 6, 1};

		mergeSort(arr);

		for (int i : arr)
			System.out.print(i + " ");

	}

	private static void mergeSort(int[] arr){
		
		int len = arr.length;

		if (len <= 1) return; //caso base
					 
		int mid = len / 2;
		int[] lArr = new int[mid];
		int[] rArr = new int[len - mid];

		int i = 0; //L
		int j = 0; //R

		for (; i < len; i++){
			if (i < mid)
				lArr[i] = arr[i];
			else{
				rArr[j] = arr[i];
				j++;
			}
		}
		mergeSort(lArr);
		mergeSort(rArr);
		merge(lArr, rArr, arr);
	}

	private static void merge(int[] lArr, int[] rArr, int[] arr){

		int lLen = arr.length/2;
		int rLen = arr.length - lLen;
		int i = 0, l = 0, r = 0; //indices

		while (l < lLen && r < rLen){
			if(lArr[l] < rArr[r]){
				arr[i] = lArr[l];
				i++;
				l++;
			}
			else {
				arr[i] = rArr[r];
				i++;
				r++;
			}
		}
		while (l < lLen){
			arr[i] = lArr[l];
			i++;
			l++;
		}
		while (r < rLen){
			arr[i] = rArr[r];
			i++;
			r++;
		}

	
	}

}
