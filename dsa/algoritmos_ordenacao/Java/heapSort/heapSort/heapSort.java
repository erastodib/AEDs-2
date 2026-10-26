public class heapSort {

    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 3, 9, 4, 7, 6, 1};

        System.out.println("Before:");
        printArray(arr);

        heapSort(arr);

        System.out.println("After:");
        printArray(arr);
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 1. Build max-heap: O(n)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 2. Extract elements from heap one by one: O(n log n)
        for (int i = n - 1; i > 0; i--) {
            // Move current root (largest element) to the end
            swap(arr, 0, i);

            // Restore max-heap property on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int size, int i) {
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
            heapify(arr, size, largest);
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
