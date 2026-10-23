import java.util.Random;

public class quicksort{
	
	public static void main(String[] args){
		Random rand = new Random();
		int[] arr = new int[10];

		for(int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);

		System.out.println("Before:");
		printArray(arr);

		quicksort(arr, 0, arr.length - 1);

		System.out.println("After:");
		printArray(arr);

	}

	private static void quicksort(int[] arr, int lowIndex, int highIndex){
		
		if (lowIndex >= highIndex)
			return;

		int pivot = arr[highIndex];

		int lp = lowIndex;
		int hp = highIndex;
	
		while (lp < hp){
			
			while(arr[lp] <= pivot && lp < hp)
				lp++;

			while (arr[hp] >= pivot && lp < hp)
				hp--;

			swap(arr, lp, hp);
		}

		swap(arr, lp, highIndex);

		quicksort(arr, lowIndex, lp - 1);
		quicksort(arr, lp + 1, highIndex);
		
			
	}

	private static void swap(int[] arr, int a, int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	private static void printArray(int[] arr){
		System.out.print("[ ");
		for (int i : arr)
			System.out.print(i + " ");
		System.out.print("]\n");
	}	



}
