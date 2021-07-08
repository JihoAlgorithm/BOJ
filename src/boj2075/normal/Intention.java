package boj2075.normal;

import java.util.PriorityQueue;

class Intention {

    public static void main(String[] args) throws Exception {

        int N = read();
        int[][] numbers = new int[N][N];
        PriorityQueue<Number> pq = new PriorityQueue<>(N + 1);

        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                numbers[r][c] = read();

        for (int c = 0; c < N; c++) {
            int r = N - 1;
            pq.offer(new Number(r, c, numbers[r][c]));
        }

        int ntnNumber = 0;

        for (int i = 0; i < N; i++) {

            Number number = pq.poll();
            int r = number.r;
            int c = number.c;

            ntnNumber = number.value;

            if (r-- > 0) pq.offer(new Number(r, c, numbers[r][c]));

        }

        System.out.print(ntnNumber);

    }

    private static class Number implements Comparable<Number> {

        int r, c, value;

        Number(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.value = v;
        }

        @Override
        public int compareTo(Number o) {
            return o.value - this.value;
        }

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
