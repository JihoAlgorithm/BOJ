package boj1904;

class Main {

    public static void main(String[] args) throws Exception {

        int C = 0, N = read();

        if (N == 1) C = 1;

        else if (N == 2) C = 2;

        else {

            int A = 1, B = 2;

            for (int i = 2; i < N; i++) {

                C = (A + B) % 15746;
                A = B;
                B = C;

            }

        }

        System.out.print(C);

    }

    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        if (c == 13) System.in.read();
        return n;
    }

}