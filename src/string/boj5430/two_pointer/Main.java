package string.boj5430.two_pointer;

class Main {

    private static final boolean DELETE = true;
    private static final boolean REVERSE = false;
    private static final String ERROR = "error";

    public static void main(String[] args) throws Exception {

        int T = read();

        int[] x = new int[100000];
        boolean[] p = new boolean[100000];

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            int c, numOperations = 0;

            while ((c = System.in.read()) > 64) {
                if (c == 68) p[numOperations] = DELETE;
                else p[numOperations] = REVERSE;
                numOperations++;
            }

            // if (c == CR) read LF;
            if (c == 13) System.in.read();

            int n = read();

            System.in.read(); // [

            if (n == 0) read(); // ]
            else for (int i = 0; i < n; i++) x[i] = read();

            boolean isError = false;
            boolean isFront = true;

            int head = 0;
            int tail = n - 1;

            for (int i = 0; i < numOperations; i++) {

                if (p[i] == REVERSE) {
                    isFront = !isFront;
                } else {
                    if (n-- == 0) {
                        isError = true;
                        break;
                    }
                    if (isFront) head++;
                    else tail--;
                }

            }

            if (isError)
                sb.append(ERROR);
            else {

                int size = tail - head + 1;

                sb.append('[');

                if (size > 0) {
                    if (isFront) while (size-- > 1) sb.append(x[head++]).append(',');
                    else while (size-- > 1) sb.append(x[tail--]).append(',');
                    sb.append(isFront ? x[head] : x[tail]);
                }

                sb.append(']');

            }

            sb.append('\n');

        }

        System.out.print(sb);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 44 && c < 93)
            n = (n << 3) + (n << 1) + (c & 15);

        // if (c == ]) read (CR or LF)
        if (c == 93) c = System.in.read();

        // if (c == CR) read LF
        if (c == 13) System.in.read();

        return n;

    }

}
