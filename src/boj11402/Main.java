package boj11402;

public class Main {

    public static void main(String[] args) throws Exception {

        long N = readL(), K = readL();
        int M = read();

        System.out.print(nCr(N, K, M));

    }

    private static long nCr(long N, long K, int M) {

        long A, F[] = new long[M];
        A = F[0] = 1;
        for (int i = 1; i < M; i++) F[i] = i * F[i - 1] % M;

        while (N != 0 || K != 0) {

            int n = (int) (N % M);
            int r = (int) (K % M);

            if (n < r) {
                A = 0;
                break;
            }

            A = A * F[n] % M;
            A = A * power((F[n - r] * F[r]) % M, M - 2, M) % M;

            N /= M;
            K /= M;

        }

        return A;

    }

    private static long power(long X, int Y, int P) {

        long A = 1;

        while (Y > 0) {
            if ((Y & 1) == 1) A = A * X % P;
            Y >>= 1;
            X = X * X % P;
        }

        return A;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

    private static long readL() throws Exception {

        int c;
        long n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}