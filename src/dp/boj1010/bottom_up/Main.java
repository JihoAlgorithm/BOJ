package dp.boj1010.bottom_up;

class Main {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int[][] dp = new int[30][30];

        for (int i = 0; i < 30; i++)
            dp[0][i] = 1;

        for (int i = 1; i < 30; i++)
            for (int j = i; j < 30; j++)
                dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];

        int T = read();

        while (T-- > 0)
            sb.append(dp[read()][read()]).append('\n');

        System.out.print(sb);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
