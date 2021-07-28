package boj2014.heap;

import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws Exception {

        int K = read();
        int N = read();

        PriorityQueue<Long> pq = new PriorityQueue<>();
        long[] number = new long[K];

        for (int i = 0; i < K; i++)
            pq.offer(number[i] = read());

        long top = 0;

        while (N-- > 0) {

            top = pq.poll();

            for (int i = 0; i < K; i++) {

                pq.offer(top * number[i]);

                if (top % number[i] == 0) break;

            }

        }

        System.out.print(top);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}