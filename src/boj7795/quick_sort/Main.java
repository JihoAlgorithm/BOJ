package boj7795.quick_sort;

class Main {

    public static void main(String[] args) throws Exception {

        int T = read();

        int[] A = new int[20000];
        int[] B = new int[20000];

        StringBuilder sb = new StringBuilder();

        while (T-->0) {

            int N = read();
            int M = read();

            for (int i = 0; i < N; i++) A[i] = read();
            for (int i = 0; i < M; i++) B[i] = read();

            quickSort(B, 0, M - 1);

            int count = 0;

            for (int i = 0; i < N; i++)
                count += lowerBound(A[i], B, 0, M);

            sb.append(count).append('\n');

        }

        System.out.print(sb);

    }

    private static int lowerBound(int key, int[] array, int l, int r) {

        int m;

        while (l < r)
            if (key > array[m = l + r >> 1]) l = m + 1;
            else r = m;

        return l;

    }

    private static void quickSort(int[] arr, int l, int r) {

        if (l >= r) return;

        int m = partition(arr, l, r);
        quickSort(arr, l, m - 1);
        quickSort(arr, m, r);

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

    private static void swap(int[] arr, int l, int r) {
        int t  = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }


    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
