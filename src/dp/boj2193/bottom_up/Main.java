package dp.boj2193.bottom_up;

class Main {

    public static void main(String[] args) throws Exception {

        int N = System.in.read() & 15;
        int c = System.in.read() & 15;

        if (c < 10) N = (N << 3) + (N << 1) + c;

        long[] dp = new long[N + 1];

        bottomUp(dp, N);

        System.out.print(dp[N]);

    }

    private static void bottomUp(long[] dp, int N) {

        dp[1] = 1;

        for (int i = 2; i <= N; i++)
            dp[i] = dp[i - 2] + dp[i - 1];

    }

}
