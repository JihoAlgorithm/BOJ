package boj11053;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int[] D = new int[N];
        
        int len = 0;
        for (int i = 0; i < N; i++) {
            int v = read();
            int k = binarySearch(D, 0, len - 1, v);
            D[k] = v;
            if (k == len) len++;
        }

        System.out.print(len);
    }

    private static int binarySearch(int[] D, int l, int r, int key) {

        while (l <= r) {

            int m = l + r >> 1;

            if (D[m] < key) l = m + 1;
            else r = m - 1;

        }

        return l;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}