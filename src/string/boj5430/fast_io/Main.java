package string.boj5430.fast_io;

import java.util.ArrayDeque;
import java.util.Deque;

class Main {

    private static final boolean DELETE = true;
    private static final boolean REVERSE = false;
    private static final String ERROR = "error";

    public static void main(String[] args) throws Exception {

        int T = read();

        Deque<Integer> x = new ArrayDeque<>(100000);
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

            if (n == 0)
                read(); // ]
            else for (int i = 0; i < n; i++) x.offer(read());

            boolean isError = false;
            boolean isFront = true;

            for (int i = 0; i < numOperations; i++) {

                if (x.size() < 1 && p[i] == DELETE) {
                    isError = true;
                    break;
                }

                if (p[i] == REVERSE) {
                    isFront = !isFront;
                } else {
                    if (isFront)
                        x.poll();
                    else
                        x.pollLast();
                }

            }

            if (isError)
                sb.append(ERROR);
            else {

                int size = x.size();

                sb.append('[');

                if (size > 0) {
                    while (size-- > 1)
                        sb.append((isFront ? x.poll() : x.pollLast())).append(',');
                    sb.append(x.poll());
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

/*
7
DDD
2
[1,2]
RDD
2
[1,2]
RDRD
3
[1,2,3]
RRRRRRR
3
[1,2,3]
R
0
[]
RRRRRRDRRRDRRRD
3
[1,2,3]
D
0
[]
 */

/*
error
[]
[2]
[3,2,1]
[]
[]
error
 */