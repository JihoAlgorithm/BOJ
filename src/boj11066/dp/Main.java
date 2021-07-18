package boj11066.dp;

// import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[16][16];
        int[] sum = new int[16];

        int T = read();

        while (T-->0) {

            int N = read();

            for (int i = 1; i <= N; i++) sum[i] = sum[i - 1] + read();

            for (int c = 1; c < N; c++) {

                for (int u = 1; u <= N; u++) {

                    int v = u + c;
                    int d = sum[v] - sum[u - 1];

                    dp[u][v] = -1 >>> 1;

                    for (int w = u; w < v; w++) {

                        int cost = dp[u][w] + dp[w + 1][v] + d;

                        if (dp[u][v] > cost)
                            dp[u][v] = cost;

                    }

                }

            }

            sb.append(dp[1][N]).append('\n');

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

    // private static String[] printArrays(int[][] array, int N) {

    //     String[] arrayStrings = new String[N + 1];

    //     for (int i = 1; i <= N; i++)
    //         arrayStrings[i] = Arrays.toString(array[i]);

    //     return arrayStrings;

    // }

}
