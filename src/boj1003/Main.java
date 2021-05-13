package boj1003;

class Main {

    public static void main(String[] args) throws Exception {

        int T = read();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {

            int N = read();
            int Z = 1; // zero
            int O = 0; // one

            for (int j = 0; j < N; j++) {

                int K = O;
                O = Z + O;
                Z = K;

            }

            sb.append(Z).append(' ').append(O).append('\n');

        }

        System.out.print(sb);

    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }

}