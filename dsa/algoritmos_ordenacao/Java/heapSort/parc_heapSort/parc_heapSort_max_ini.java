public class parc_heapSort_max_ini {

    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 3, 9, 4, 7, 6, 1};
        int k = 3; // Isolate and sort the 3 largest elements at indices 0..k-1

        System.out.println("Original array:");
        printArray(arr);

        partialHeapSortMax(arr, k);

        System.out.println("\nAfter partial HeapSort (first " + k + " largest sorted at front):");
        printArray(arr);
    }

    public static void partialHeapSortMax(int[] arr, int k) {
        int n = arr.length;
        if (k <= 0 || k > n) return;

        // 1. Build a min-heap using the first k elements: O(k)
        for (int i = k / 2 - 1; i >= 0; i--) {
            minHeapify(arr, k, i);
        }

        // 2. Scan remaining elements (indices k to n-1): O((n - k) log k)
        // If an element is larger than the current min (arr[0]), swap and re-heapify
        for (int i = k; i < n; i++) {
            if (arr[i] > arr[0]) {
                swap(arr, 0, i);
                minHeapify(arr, k, 0);
            }
        }

        // 3. Sort the first k elements in descending order: O(k log k)
        for (int i = k - 1; i > 0; i--) {
            swap(arr, 0, i);
            minHeapify(arr, i, 0);
        }
    }

    private static void minHeapify(int[] arr, int size, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] < arr[smallest]) {
            smallest = left;
        }

        if (right < size && arr[right] < arr[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(arr, i, smallest);
            minHeapify(arr, size, smallest);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
