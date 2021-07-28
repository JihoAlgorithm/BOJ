package boj2075.normal;

import java.util.PriorityQueue;

class Peek {

    public static void main(String[] args) throws Exception {

        int N = read();
        PriorityQueue<Integer> pq = new PriorityQueue<>(N + 1);

        for (int i = 0; i < N; i++) pq.offer(read());

        for (int r = 1; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int num = read();
                if (num > pq.peek()) {
                    pq.offer(num);
                    pq.poll();
                }
            }
        }

        System.out.print(pq.poll());

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;

        if (isNegative) n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return isNegative ? ~n + 1 : n;

    }
    
}
