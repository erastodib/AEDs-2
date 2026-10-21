public class insertionSort_parc{

	public static void main(String[] args){
		int vet[] = {8, 3, 6, 2, 5, 9};
		int n = vet.length; //6
		int k = 3; //dos 6, quero encontrar apenas os 3 menores

		insertionSort_parc(vet, n, k);
		
		for (int i = 0; i < n; i++)
			System.out.print(vet[i] + " ");
	}

	static void insertionSort_parc(int[] vet, int n, int k){
		
		for(int i = 1; i < n; i++){
			int tmp = vet[i];
			int j;
			if (i <= k)
				j = i - 1;
			else
				j = k-1;

			while(j >= 0 && vet[j] > tmp){
				vet[j+1] = vet[j];
				j--;
			}

			vet[j+1] = tmp;
		}

	}


}
