package dp.boj9465.bottom_up;

class Main {

    public static void main(String[] args) throws Exception {

        int T, N, a, b, c;

        T = read();

        int[][] dp = new int[2][100000];
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            N = read();

            for (int i = 0; i < N; i++) dp[0][i] = read();
            for (int i = 0; i < N; i++) dp[1][i] = read();

            dp[0][1] += dp[1][0];
            dp[1][1] += dp[0][0];

            for (int i = 2; i < N; i++) {

                c = max(dp[0][i - 2], dp[1][i - 2]);
                a = max(c, dp[1][i - 1]);
                b = max(c, dp[0][i - 1]);

                dp[0][i] += a;
                dp[1][i] += b;

            }

            sb.append(max(dp[0][N - 1], dp[1][N - 1])).append('\n');

            
        }

        System.out.print(sb);

    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
