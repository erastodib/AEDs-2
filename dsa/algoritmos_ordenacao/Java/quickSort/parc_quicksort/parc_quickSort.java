import java.util.Random;

public class parc_quickSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(100);

        int k = 3; // Isolate and sort only the 3 smallest elements at indices 0..k-1

        System.out.println("Before:");
        printArray(arr);

        partialQuicksort(arr, k);

        System.out.println("After partial QuickSort (first " + k + " smallest elements sorted):");
        printArray(arr);
    }

    private static void partialQuicksort(int[] arr, int k) {
        partialQuicksort(arr, 0, arr.length - 1, k);
    }

    private static void partialQuicksort(int[] arr, int lowIndex, int highIndex, int k) {
        if (lowIndex >= highIndex)
            return;

        int pivotIndex = new Random().nextInt(highIndex - lowIndex + 1) + lowIndex;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, highIndex);

        int lp = lowIndex;
        int hp = highIndex;

        while (lp < hp) {
            while (arr[lp] <= pivot && lp < hp)
                lp++;

            while (arr[hp] >= pivot && lp < hp)
                hp--;

            swap(arr, lp, hp);
        }

        swap(arr, lp, highIndex);

        // 1. Recurse left ONLY if the left region overlaps with the first k positions
        if (lowIndex < k) {
            partialQuicksort(arr, lowIndex, lp - 1, k);
        }

        // 2. Recurse right ONLY if the pivot index is before position k - 1
        if (lp < k - 1) {
            partialQuicksort(arr, lp + 1, highIndex, k);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i : arr)
            System.out.print(i + " ");
        System.out.print("]\n");
    }
}
