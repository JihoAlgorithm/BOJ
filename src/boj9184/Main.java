package boj9184;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][][] DP = new int[21][21][21];

        while (true) {

            int a = read(), b = read(), c = read();

            if (a == -1 && b == -1 && c == -1) break;

            bw.write("w(" + a + ", " + b + ", " + c + ") = " + recur(a, b, c, DP));
            bw.newLine();

        }

        bw.close();

    }

    private static int recur(int a, int b, int c, int[][][] DP) {

        if (a < 1 || b < 1 || c < 1)
            return 1;

        if (a > 20 || b > 20 || c > 20)
            return DP[20][20][20] > 0 ? DP[20][20][20] : (DP[20][20][20] = recur(20, 20, 20, DP));

        if (DP[a][b][c] > 0)
            return DP[a][b][c];

        if (a < b && b < c)
            return DP[a][b][c] = recur(a, b, c - 1, DP) + recur(a, b - 1, c - 1, DP) - recur(a, b - 1, c, DP);

        else
            return DP[a][b][c] = recur(a - 1, b, c, DP) + recur(a - 1, b - 1, c, DP) + recur(a - 1, b, c - 1, DP) - recur(a - 1, b - 1, c - 1, DP);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return isNegative ? ~n + 1 : n;
    
    }

}