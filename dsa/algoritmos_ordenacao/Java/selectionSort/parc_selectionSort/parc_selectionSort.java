public class parc_selectionSort{

    public static void main(String[] args) {
        int array[] = {9, 1, 8, 2, 7, 3, 6, 4, 5};
        int k = 3; // Find and sort only the 3 smallest elements

        partialSelectionSort(array, k);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    static void partialSelectionSort(int array[], int k) {
        // Limit the outer loop to run k times instead of array.length - 1
        for (int i = 0; i < k && i < array.length - 1; i++) {
            int minIx = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIx]) {
                    minIx = j;
                }
            }

            swap(array, i, minIx);
        }
    }

    static void swap(int array[], int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
