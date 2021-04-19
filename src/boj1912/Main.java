package boj1912;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), D[] = new int[N], T;
        int M = D[0] = read();

        for (int i = 1; i < N; i++)
            M = max(D[i] = max(D[i - 1] + (T = read()), T), M);

        System.out.print(M);

    }

    private static int max(int a, int b) { return a > b ? a : b; }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;

    }

}