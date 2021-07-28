package backtrack.boj15664.arrays;

import java.util.Arrays;

class Main {

    private static int N, M;
    private static int[] visited, numbers, selected;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        numbers = new int[N];
        selected = new int[M];
        visited  = new int[10002];

        int index = 0;
        int duplicatedNumberCount = 0;

        for (int i = 0; i < N; i++) {
            int currentNumber = read();
            if (visited[currentNumber] > 0) {
                visited[currentNumber]++;
                duplicatedNumberCount++;
                currentNumber = 10001;
            }
            visited[numbers[index++] = currentNumber]++;
        }

        Arrays.sort(numbers);
        N -= duplicatedNumberCount;

        dfs(0, 0);

        System.out.print(sb);

    }

    private static void dfs(int start, int count) {

        if (count == M) {

            for (int number : selected)
                sb.append(number).append(' ');

            sb.append('\n');

            return;

        }

        for (int i = start; i < N; i++) {

            int currentNumber = numbers[i];

            if (visited[currentNumber] > 0) {

                visited[currentNumber]--;

                selected[count] = currentNumber;
                dfs(i, count + 1);

                visited[currentNumber]++;

            }

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