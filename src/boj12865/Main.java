package boj12865;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), K = read();
        int[] DP = new int[K + 1];

        for (int i = 0; i < N; i++) {
            int W = read(), V = read();
            for (int j = K; j >= W; j--)
                if (DP[j] < DP[j - W] + V) DP[j] = DP[j - W] + V;
        }

        System.out.print(DP[K]);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}
