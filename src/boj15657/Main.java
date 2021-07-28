package boj15657;

import java.util.Arrays;

class Main {

    private static int N;
    private static int M;
    private static int[] inArray;
    private static int[] outArray;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        inArray  = new int[N];
        outArray = new int[M];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) inArray[i] = read();
        Arrays.sort(inArray);

        dfs(0, 0);

        System.out.print(sb);

    }

    private static void dfs(int start, int count) {

        if (count == M) {
            for (int n : outArray) sb.append(n).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = start; i < N; i++) {
            outArray[count] = inArray[i];
            dfs(i, count + 1);
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