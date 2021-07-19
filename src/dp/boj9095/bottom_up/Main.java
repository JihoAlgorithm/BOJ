package dp.boj9095.bottom_up;

class Main {

    public static void main(String[] args) throws Exception {

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        bottomUp(dp);

        int T = read();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0)
            sb.append(dp[read()]).append('\n');

        System.out.print(sb);

    }

    private static void bottomUp(int[] dp) {

        int window = dp[1] + dp[2] + dp[3];

        for (int i = 4; i < 11; i++) {
            dp[i] = window;
            window = window - dp[i - 3] + dp[i];
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
