package boj9184;

public class MainBottomUp {

    private static final String head = "w(", split = ", ", tail = ") = ";
    private static final int SIZE = 20;

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int[][][] W = new int[SIZE+1][SIZE+1][SIZE+1];

        for (int i = 0; i <= SIZE; i++)
        for (int j = 0; j <= SIZE; j++)
            W[0][i][j] = W[i][0][j] = W[i][j][0] = 1;

        for (int a = 1; a <= SIZE; a++)
        for (int b = 1; b <= SIZE; b++)
        for (int c = 1; c <= SIZE; c++)
            if (a < b && b < c) W[a][b][c] = W[a][b][c-1] + W[a][b-1][c-1] - W[a][b-1][c];
            else W[a][b][c] = W[a-1][b][c] + W[a-1][b-1][c] + W[a-1][b][c-1] - W[a-1][b-1][c-1];

        while (true) {

            int ans, a = read(), b = read(), c = read();

            if (a == -1 && b == -1 && c == -1) break;

            if (a < 1 || b < 1 || c < 1)
                ans = 1;
            else if (a > SIZE || b > SIZE || c > SIZE)
                ans = W[SIZE][SIZE][SIZE];
            else
                ans = W[a][b][c];

            sb.append(head)
                .append(a).append(split)
                .append(b).append(split)
                .append(c).append(tail)
                .append(ans).append('\n');

        }

        System.out.print(sb);

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