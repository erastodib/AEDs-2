import java.util.Random;

public class parc_mergeSort {

    public static void main(String[] args) {
        int[] arr = {94, 91, 99, 39, 10, 70, 26, 77, 39, 61};
        int k = 3;

        System.out.println("Before:");
        printArray(arr);

        partialMergeSort(arr, k);

        System.out.println("After:");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i : arr) System.out.print(i + " ");
        System.out.print("]\n");
    }

    private static void partialMergeSort(int[] arr, int k) {
        int len = arr.length;
        if (len <= 1 || k <= 0) return;

        int mid = len / 2;
        int[] esq = new int[mid];
        int[] dir = new int[len - mid];

        for (int i = 0; i < len; i++) {
            if (i < mid) esq[i] = arr[i];
            else dir[i - mid] = arr[i];
        }

        // BOTH halves MUST be sorted for merge to work properly
        partialMergeSort(esq, k);
        partialMergeSort(dir, k);

        merge(arr, esq, dir, k);
    }

    private static void merge(int[] arr, int[] esq, int[] dir, int k) {
        int l_len = esq.length;
        int r_len = dir.length;
        int i = 0, l = 0, r = 0;

        // 1. Only merge up to k elements into the front
        while (l < l_len && r < r_len && i < k) {
            if (esq[l] <= dir[r]) arr[i++] = esq[l++];
            else arr[i++] = dir[r++];
        }

        while (l < l_len && i < k) arr[i++] = esq[l++];
        while (r < r_len && i < k) arr[i++] = dir[r++];

        // 2. Preserve remaining elements so nothing is lost or corrupted
        while (l < l_len) arr[i++] = esq[l++];
        while (r < r_len) arr[i++] = dir[r++];
    }
}
