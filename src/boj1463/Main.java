package boj1463;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        System.out.print(D(N, 0));

    }

    private static int D(int n, int c) {
        if (n < 2) return c;
        return min(D(n >> 1, c + 1 + (n & 1)), D(n / 3, c + 1 + (n % 3)));
    }

    private static int min(int a, int b) { return a < b ? a : b; }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}