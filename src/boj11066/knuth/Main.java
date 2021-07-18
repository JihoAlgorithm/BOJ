package boj11066.knuth;

import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();

        int[] sum = new int[501];
        int[][] dp = new int[502][502];
        int[][] knuth = new int[502][502];

        int T = read();

        for (int i = 1; i <= 500; i++) knuth[i - 1][i] = i;

        while (T-->0) {

            int K = read();

            for (int i = 1; i <= K; i++) {
                sum[i] = sum[i - 1] + read();
                // knuth[i - 1][i] = i;
            }

            for (int c = 2; c <= K; c++) {

                for (int u = 0; u + c <= K; u++) {

                    int v = u + c;
                    int d = sum[v] - sum[u];

                    dp[u][v] = -1 >>> 1;

                    for (int w = knuth[u][v - 1]; w <= knuth[u + 1][v]; w++) {

                        int cost = dp[u][w] + dp[w][v] + d;

                        if (dp[u][v] > cost) {
                            dp[u][v] = cost;
                            knuth[u][v] = w;
                        }

                    }

                }

            }

            sb.append(dp[0][K]).append('\n');

        }

        System.out.print(sb);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3)+ (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

    private static String[] printArrays(int[][] array, int N) {

        String[] arrayStrings = new String[N + 1];

        for (int i = 0; i <= N; i++)
            arrayStrings[i] = Arrays.toString(array[i]);

        return arrayStrings;

    }

}
