package boj2564;

class Main {

    public static void main(String[] args) throws Exception {

        int W = read();
        int H = read();
        int N = read();
        int L = W + H;

        int[] store = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            switch (read()) {
                case 1: store[i] = read();            break;
                case 2: store[i] = L + W - read();    break;
                case 3: store[i] = (L << 1) - read(); break;
                case 4: store[i] = W + read();        break;
            }
        }

        int DG = store[N];
        int dist = 0;

        for (int i = 0; i < N; i++) {
            int d = abs(store[i], DG);
            if (d >= L) d = (L << 1) - d;
            dist += d;
        }

        System.out.print(dist);

    }

    private static int abs(int a, int b) {
        if ((a -= b) < 0) a = ~a + 1;
        return a;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        if (c == 13) System.in.read();
        return n;
    }

}