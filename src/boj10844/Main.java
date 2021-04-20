package boj10844;

public class Main {

    private static final int M = 1000000000, LAST = 10;
    public static void main(String[] args) throws Exception {

        int N = read(), A, B, sum, DP[][] = new int[2][LAST + 1];
        A = sum = 0;

        for (int i = 1; i < LAST; i++) DP[A][i] = 1;

        for (int i = 1; i < N; i++) {

            A = i & 1;
            B = i + 1 & 1;

            DP[A][0] = DP[B][1];

            for (int j = 1; j < LAST; j++)
                DP[A][j] = (DP[B][j - 1] + DP[B][j + 1]) % M;

        }

        for (int i = 0; i < LAST; i++) sum += DP[A][i];

        System.out.print(sum % M);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}
