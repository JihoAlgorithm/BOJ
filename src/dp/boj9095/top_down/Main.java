package dp.boj9095.top_down;

class Main {

    private static int[] dp;

    public static void main(String[] args) throws Exception {

        dp = new int[11];
        StringBuilder sb = new StringBuilder();

        int T = read();

        while (T-- > 0) {

            int n = read();

            count(n);

            sb.append(dp[n]).append('\n');

        }

        System.out.print(sb);

    }

    private static int count(int n) {

        if (n == 0) return 1;
        if (n < 0) return 0;
        if (dp[n] > 0) return dp[n];

        dp[n] += count(n - 1);
        dp[n] += count(n - 2);
        dp[n] += count(n - 3);

        return dp[n];

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
