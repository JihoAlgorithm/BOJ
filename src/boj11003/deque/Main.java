package boj11003.deque;

import java.util.ArrayDeque;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int L = read();

        ArrayDeque<Number> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {

            int v = read();

            if (!dq.isEmpty() && dq.peekFirst().index + L <= i)
                dq.pollFirst();

            while (!dq.isEmpty() && dq.peekLast().value > v)
                dq.pollLast();

            dq.offer(new Number(i, v));

            sb.append(dq.peekFirst().value).append(' ');

        }

        System.out.print(sb);

    }

    private static class Number {

        int index, value;

        Number(int i, int v) {
            index = i;
            value = v;
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
