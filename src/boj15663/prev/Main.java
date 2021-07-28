package boj15663.prev;

import java.util.Arrays;

class Main {

    private static int N;
    private static int M;
    private static int visited;
    private static int[] numbers;
    private static int[] selected;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        numbers  = new int[N];
        selected = new int[M];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) numbers[i] = read();
        Arrays.sort(numbers);

        dfs(0);

        System.out.print(sb);

    }

    private static void dfs(int count) {

        if (count == M) {
            for (int number : selected) sb.append(number).append(' ');
            sb.append('\n');
            return;
        }

        int prevNumber = 0;

        for (int i = 0; i < N; i++) {

            if (((visited >> i) & 1) == 1) continue;

            int currentNumber = numbers[i];

            if (prevNumber == currentNumber) continue;

            visited |= 1 << i;
            prevNumber = selected[count] = currentNumber;
            dfs(count + 1);
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
