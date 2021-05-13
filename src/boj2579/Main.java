package boj2579;

class Main {

    public static void main(String[] args) throws Exception {

        int t = 0, N = read();
        int[] D = new int[N + 2];

        D[1] = read();

        if (N > 1) D[2] = D[1] + (t = read());

        for (int i = 3; i <= N; i++)
            D[i] = max(D[i-3] + t, D[i-2]) + (t = read());

        System.out.print(D[N]);

    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        if (c == 13) System.in.read();
        return n;
    }

}