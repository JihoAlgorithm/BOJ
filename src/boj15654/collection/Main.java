package boj15654.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Main {

    private static StringBuilder sb;
    private static int N, M, visited;
    private static Stack<Integer> selected;
    private static List<Integer> numbers;

    public static void main(String[] args) throws Exception {

        sb = new StringBuilder();
        N = read();
        M = read();

        numbers = new ArrayList<>(N);
        selected = new Stack<>();

        for (int i = 0; i < N; i++) numbers.add(read());

        Collections.sort(numbers);

        dfs(0);

        System.out.print(sb);

    }

    private static void dfs(int count) throws Exception {

        if (count == M) {
            selected.stream().forEach(s -> sb.append(s).append(' '));
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            visited |= 1 << i;
            selected.push(numbers.get(i));
            dfs(count + 1);
            selected.pop();
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