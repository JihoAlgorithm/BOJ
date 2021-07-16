package boj15655.collection.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();

        int N = read();
        int M = read();

        List<Integer> numbers = new ArrayList<>(N);
        Stack<Integer> selected = new Stack<>();

        for (int i = 0; i < N; i++) numbers.add(read());

        Collections.sort(numbers);

        Stack<State> dfs = new Stack<>();

        dfs.push(new State(0, 1));

        while (!dfs.isEmpty()) {

            State state = dfs.peek();

            if (state.count == M) {

                dfs.stream().forEach(s -> sb.append(numbers.get(s.index)).append(' '));
                sb.append('\n');
                state = dfs.pop();

                if (state.index + 1 < N)
                    dfs.push(new State(state.index + 1, state.count));

                continue;

            }

            if (state.index >= N) continue;

            dfs.push(new State(state.index + 1, state.count + 1));

        }

        System.out.print(sb);

    }

    private static class State {

        int index;
        int count;

        State(int index, int count) {
            this.index = index;
            this.count = count;
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
