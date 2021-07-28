package boj2075.normal;

import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        PriorityQueue<Integer> pq = new PriorityQueue<>(N + 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pq.offer(read());
                if (pq.size() > N) pq.poll();
            }
        }

        System.out.println(pq.poll());

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
