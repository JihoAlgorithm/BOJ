package boj1932;

class Main {

    public static void main(String[] args) throws Exception {

        int A, B, N = read(), max, DP[][] = new int[2][N];
        A = B = max = 0;

        DP[0][0] = read();

        for (int i = 1; i < N; i++) {

            A = i & 1;
            B = i + 1 & 1;

            DP[A][0] = DP[B][0] + read();

            for (int j = 1; j <= i; j++)
                DP[A][j] = max(DP[B][j-1], DP[B][j]) + read();

        }

        for (int i = 0; i < N; i++) if (max < DP[A][i]) max = DP[A][i];

        System.out.print(max);

    }

    private static int max(int a, int b) { return a > b ? a: b; }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}