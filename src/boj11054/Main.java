package boj11054;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int[] arr = new int[N], lis = new int[N], tmp = new int[N];

        int k, l = 0;
        for (int i = 0; i < N; i++) {

            arr[i] = read();

            if ((k = binarySearch(lis, 0, l - 1, arr[i])) == l) l++;
            lis[k] = arr[i];

            tmp[i] = l;

        }

        int max = l = 0;
        for (int i = N - 1; i >= 0; i--) {

            if ((k = binarySearch(lis, 0, l - 1, arr[i])) == l) l++;
            lis[k] = arr[i];

            if (max < l + tmp[i]) max = l + tmp[i];

        }

        System.out.print(max - 1);

    }

    private static int binarySearch(int[] a, int l, int r, int v) {

        int m;

        while (l <= r)
            if (a[m = l + r >> 1] < v) l = m + 1;
            else r = m - 1;

        return l;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}