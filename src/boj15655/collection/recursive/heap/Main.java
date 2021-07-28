package boj15655.collection.recursive.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Main {

    private static int N;
    private static int M;
    private static int visited;
    private static List<Integer> numbers;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        numbers = new ArrayList<>(N);
        sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>(N);

        for (int i = 0; i < N; i++) pq.offer(read());
        for (int i = 0; i < N; i++) numbers.add(pq.poll());

        dfs(0, 0);

        System.out.print(sb);

    }

    private static void dfs(int start, int count) {

        if (count == M) {

            for (int i = 0; i < N; i++)
                if (((visited >> i) & 1) == 1)
                    sb.append(numbers.get(i)).append(' ');

            sb.append('\n');

            return;

        }

        for (int i = start; i < N; i++) {
            visited |= 1 << i;
            dfs(i + 1, count + 1);
            visited ^= 1 << i;
        }

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
