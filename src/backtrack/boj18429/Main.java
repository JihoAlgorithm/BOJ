package backtrack.boj18429;

class Main {

    private static int N, K, visited, NON_MUSCLE_LOSE_SET;
    private static int[] weight;

    public static void main(String[] args) throws Exception {

        N = read();
        K = read();

        weight = new int[N];
        //select = new int[N];

        for (int i = 0; i < N; i++) weight[i] = read();

        dfs(0, 500 + K);

        System.out.println(NON_MUSCLE_LOSE_SET);

    }

    private static void dfs(int count, int muscle) {

        muscle -= K;

        if (muscle < 500) return;

        if (count == N) {
            NON_MUSCLE_LOSE_SET++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (((visited >> i) & 1) == 0) {
                visited |= 1 << i;
                dfs(count + 1, muscle + weight[i]);
                visited ^= 1 << i;
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
