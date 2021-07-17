package boj15656;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    private static int N;
    private static int M;
    private static List<Integer> list;
    private static int[] selected;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        list = new ArrayList<>(N);
        selected = new int[M];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) list.add(read());
        Collections.sort(list);

        dfs(0);

        System.out.print(sb);

    }

    private static void dfs(int count) {

        if (count == M) {
            for (int number : selected)
                sb.append(number).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            selected[count] = list.get(i);
            dfs(count + 1);
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
