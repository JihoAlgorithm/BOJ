package boj2805;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), M = read();
        int[] trees = new int[N];
        for (int i = 0; i < N; i++) trees[i] = read();

        // SORT
        quickSort(trees, 0, N - 1);

        // BINARY SEARCH
        int l = 0, r = trees[N - 1];
        boolean isEqual = false;

        while (l <= r) {

            int m = l + r >> 1;
            int k = upperBound(trees, m, 0, N);

            long sum = 0;
            for (int i = k; i < N; i++)
                sum += trees[i] - m;
            
            if (sum > M) l = m + 1;
            else {
                r = m - 1;
                if (sum == M) isEqual = true;
            }

        }

        System.out.print(isEqual ? l : l - 1);

    }

    private static int upperBound(int[] a, int k, int l, int r) {

        int m;

        while (l < r)
            if (a[m = l + r >> 1] <= k) l = m + 1;
            else r = m;

        return r;

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
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

}
