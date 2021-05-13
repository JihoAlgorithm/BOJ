package boj9461;

class Main {

    public static void main(String[] args) throws Exception {

        int T = read();
        StringBuilder sb = new StringBuilder();

        while (T-->0) {

            int N = read();

            if (N < 4) sb.append(1).append('\n');

            else {

                long[] D = new long[N];
                D[0] = D[1] = D[2] = 1;

                for (int i = 3; i < N; i++)
                    D[i] = D[i - 3] + D[i - 2];

                sb.append(D[N - 1]).append('\n');

            }

        }

        System.out.print(sb);

    }

    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        if (c == 13) System.in.read();
        return n;
    }

}
