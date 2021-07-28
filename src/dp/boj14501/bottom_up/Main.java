package dp.boj14501.bottom_up;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read() + 1;

        int[] T = new int[N];
        int[] P = new int[N];
        int[] dp = new int[N + 1];

        for (int i = 1; i < N; i++) {
            T[i] = read();
            P[i] = read();
        }

        bottomUp(N, T, P, dp);

        int maxSum = 0;

        for (int i = 1; i < N + 1; i++)
            if (maxSum < dp[i])
                maxSum = dp[i];

        System.out.print(maxSum);

    }

    private static void bottomUp(int N, int[] T, int[] P, int[] dp) {

        for (int i = 1; i < N; i++) {

            int expiration = i + T[i];

            if (expiration > N) continue;

            int prefixSum = P[i] + dp[i];

            for (int j = expiration; j < N + 1; j++)
                if (dp[j] < prefixSum)
                    dp[j] = prefixSum;

        }

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
