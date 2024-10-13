import java.util.Arrays;
import java.util.Random;

public class Sorting {
    static final int MAX = 100000; // 定义数组大小
    static int[] a = new int[MAX]; // 创建大小为 100000 的数组

    // 快速排序
    public static void quickSort(int[] a, int l, int r) {
        if (l >= r) return;

        int i = l - 1;
        int j = r + 1;
        int x = a[(l + r) / 2]; // 分界点

        while (i < j) {
            do i++; while (a[i] < x);
            do j--; while (a[j] > x);
            if (i < j) {
                swap(a, i, j);
            }
        }
        quickSort(a, l, j);
        quickSort(a, j + 1, r);
    }

    // 堆排序
    public static void sift(int[] a, int low, int high) {
        int i = low;
        int j = 2 * i;
        int tmp = a[i];

        while (j <= high) {
            if (j < high && a[j] < a[j + 1]) j++;
            if (tmp < a[j]) {
                a[i] = a[j];
                i = j;
                j = 2 * i;
            } else {
                break;
            }
        }
        a[i] = tmp;
    }

    public static void heapSort(int[] a, int n) {
        for (int i = n / 2; i >= 1; i--) sift(a, i, n - 1); // n-1 for zero-index
        for (int i = n - 1; i >= 1; i--) { // n-1 for zero-index
            swap(a, 0, i); // swap(1, i) changed to swap(0, i)
            sift(a, 0, i - 1); // 0 for zero-index
        }
    }

    // 希尔排序
    public static void shellSort(int[] a, int n) {
        int d = n / 2;
        while (d > 0) {
            for (int i = d; i < n; i++) {
                int tmp = a[i];
                int j = i - d;
                while (j >= 0 && tmp < a[j]) {
                    a[j + d] = a[j];
                    j -= d;
                }
                a[j + d] = tmp;
            }
            d /= 2;
        }
    }

    // 归并排序
    public static void merge(int[] arr, int low, int mid, int high) {
        int[] R1 = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                R1[k++] = arr[i++];
            } else {
                R1[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            R1[k++] = arr[i++];
        }
        while (j <= high) {
            R1[k++] = arr[j++];
        }

        for (k = 0, i = low; i <= high; k++, i++) {
            arr[i] = R1[k];
        }
    }

    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    // 交换数组中的两个元素
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // 测试堆排序
        for (int i = 0; i < MAX; i++) { // Change to 0 to MAX-1
            a[i] = rand.nextInt(100);
        }
        long startTime = System.nanoTime();
        heapSort(a, MAX);
        long endTime = System.nanoTime();
        System.out.println("堆排序所用的时间为: " + (endTime - startTime) / 1_000_000.0 + "ms");

        // 测试归并排序
        for (int i = 0; i < MAX; i++) {
            a[i] = rand.nextInt(100);
        }
        startTime = System.nanoTime();
        mergeSort(a, 0, MAX - 1);
        endTime = System.nanoTime();
        System.out.println("归并排序所用的时间为: " + (endTime - startTime) / 1_000_000.0 + "ms");

        // 测试希尔排序
        for (int i = 0; i < MAX; i++) {
            a[i] = rand.nextInt(100);
        }
        startTime = System.nanoTime();
        shellSort(a, MAX);
        endTime = System.nanoTime();
        System.out.println("希尔排序所用的时间为: " + (endTime - startTime) / 1_000_000.0 + "ms");

        // 测试快速排序
        for (int i = 0; i < MAX; i++) {
            a[i] = rand.nextInt(100);
        }
        startTime = System.nanoTime();
        quickSort(a, 0, MAX - 1);
        endTime = System.nanoTime();
        System.out.println("快速排序所用的时间为: " + (endTime - startTime) / 1_000_000.0 + "ms");
    }
}
