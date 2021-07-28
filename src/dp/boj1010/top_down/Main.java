package dp.boj1010.top_down;

class Main {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int[][] binomial = new int[30][30];
        int T = read();

        while (T-- > 0) {

            // 0 < N ≤ M < 30
            int N = read();
            int M = read();

            topDown(N, M, binomial);

            sb.append(binomial[N][M]).append('\n');

        }

        System.out.print(sb);

    }

    private static int topDown(int N, int M, int[][] binomial) {

        if (N < 1) return binomial[N][M] = 1;
        if (M < N) return 0;
        if (binomial[N][M] > 0) return binomial[N][M];

        binomial[N][M] += topDown(N - 1, M - 1, binomial);
        binomial[N][M] += topDown(N, M - 1, binomial);

        return binomial[N][M];

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
