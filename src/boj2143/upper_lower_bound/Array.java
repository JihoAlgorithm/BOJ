package boj2143.upper_lower_bound;

public class Array {

    public static void main(String[] args) throws Exception {

        int T = read();
        int[] A = getArray(read());
        int[] B = getArray(read());

        int length = A.length;
        quickSort(A, 0, length - 1);

        long count = 0;

        for (int number : B) {

            int k = T - number;
            int ub = upperBound(k, A, 0, length);
            int lb = lowerBound(k, A, 0, length);

            count += ub - lb;

        }

        System.out.print(count);

    }

    private static int[] getArray(int size) throws Exception {

        int[] array = new int[(size * (size + 1)) >> 1];

        for (int i = 0; i < size; i++) array[i] = read();
        getPrefixSum(array, size);

        return array;

    }

    private static void getPrefixSum(int[] array, int size) {

        int k = size;

        for (int i = 0; i < size; i++) {

            int sum = array[i];

            for (int j = i + 1; j < size; j++)
                array[k++] = sum += array[j];

        }

    }

    private static int m;

    private static int lowerBound(int key, int[] array, int l, int r) {

        while (l < r)
            if (key > array[m = l + r >> 1]) l = m + 1;
            else r = m;

        return l;

    }

    private static int upperBound(int key, int[] array, int l, int r) {

        while (l < r)
            if (key < array[m = l + r >> 1]) r = m;
            else l = m + 1;

        return l;

    }

    private static void quickSort(int[] arr, int l, int r) {

        if (l >= r) return;

        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p, r);

    }

    private static int partition(int[] arr, int l, int r) {

        int p = arr[l + r >> 1];

        while (l <= r) {

            while (arr[l] < p) l++;
            while (arr[r] > p) r--;

            if (l <= r) swap(arr, l++, r--);

        }

        return l;

    }

    private static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;

        if (isNegative) n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return isNegative ? ~n + 1 : n;

    }

}