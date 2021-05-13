package boj1655;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;

class BOJ1655_PriorityQueue {

    public static void main(String[] args) throws Exception {

        int N = read();

        Queue<Integer> left = new PriorityQueue<>((x, y) -> y - x);
        Queue<Integer> right = new PriorityQueue<>();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (N-- > 0) {

            int n = read();

            int l = left.size();
            int r = right.size();

            if (l == r) {
                left.offer(n);
                l++;
            } else {
                right.offer(n);
                r++;
            }

            if (l > 0 && r > 0 && left.peek() > right.peek()) {
                l = left.poll();
                r = right.poll();
                left.offer(r);
                right.offer(l);
            }

            bw.write(Integer.toString(left.peek()));
            bw.newLine();

        }

        bw.close();

    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative)
            n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return isNegative ? ~n + 1 : n;
    }
}