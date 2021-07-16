package boj15655.collection.recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Main {

    private static int N, M;
    private static List<Integer> numbers;
    private static Stack<Integer> selected;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        numbers = new ArrayList<>(N);
        selected = new Stack<>();

        for (int i = 0; i < N; i++) numbers.add(read());

        Collections.sort(numbers);

        dfs(0, 0);

        System.out.print(sb);

    }

    private static void dfs(int start, int count) throws Exception {

        if (count == M) {

            selected.stream().forEach(number -> sb.append(number).append(' '));
            sb.append('\n');

            return;

        }

        for (int i = start; i < N; i++) {

            selected.push(numbers.get(i));
            dfs(i + 1, count + 1);
            selected.pop();

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
