package dp.boj2193.top_down;

class Main {

    private static long[] dp;

    public static void main(String[] args) throws Exception {

        int N = System.in.read() & 15;
        int c = System.in.read() & 15;

        if (c < 10) N = (N << 3) + (N << 1) + c;

        dp = new long[N + 1];

        topDown(N);

        System.out.print(dp[N]);

    }

    private static long topDown(int N) {

        if (N < 3) return dp[N] = 1;
        if (dp[N] > 0) return dp[N];

        dp[N] += topDown(N - 2);
        dp[N] += topDown(N - 1);

        return dp[N];

    }

}
