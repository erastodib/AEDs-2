public class parc_heapSort_min_ini {

    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 3, 9, 4, 7, 6, 1};
        int k = 3; // Isolate and sort the 3 smallest elements at indices 0..k-1

        System.out.println("Original array:");
        printArray(arr);

        partialHeapSort(arr, k);

        System.out.println("\nAfter partial HeapSort (first " + k + " smallest sorted at front):");
        printArray(arr);
    }

    public static void partialHeapSort(int[] arr, int k) {
        int n = arr.length;
        if (k <= 0 || k > n) return;

        // 1. Build a max-heap using the first k elements: O(k)
        for (int i = k / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, k, i);
        }

        // 2. Scan remaining elements (indices k to n-1): O((n - k) log k)
        // If an element is smaller than the current max (arr[0]), swap and re-heapify
        for (int i = k; i < n; i++) {
            if (arr[i] < arr[0]) {
                swap(arr, 0, i);
                maxHeapify(arr, k, 0);
            }
        }

        // 3. Sort the first k elements in ascending order via standard HeapSort: O(k log k)
        for (int i = k - 1; i > 0; i--) {
            swap(arr, 0, i);
            maxHeapify(arr, i, 0);
        }
    }

    private static void maxHeapify(int[] arr, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, size, largest);
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
