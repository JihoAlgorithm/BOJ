package boj14888;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();

        int[] V = new int[3];
        V[0] = N;
        V[1] = Integer.MAX_VALUE;
        V[2] = Integer.MIN_VALUE;

        int[] A = new int[N];
        int[] O = new int[N];

        for (int i = 0; i < N; i++)
            A[i] = read();

        int oIdx = 1;
        for (int i = 0; i < 4; i++)
            for (int o = read(); o > 0; o--)
                O[oIdx++] = i;

        dfs(1, A[0], A, O, V, new boolean[N]);

        StringBuilder sb = new StringBuilder("\n");
        sb.insert(0, V[2]).append(V[1]);
        System.out.println(sb.toString());

    }

    private static void dfs(int cnt, int num, int[] A, int[] O, int[] V, boolean[] visited) {

        if (cnt == V[0]) {

            if (V[1] > num) V[1] = num;
            if (V[2] < num) V[2] = num;
            return;

        }

        for (int i = 1; i < V[0]; i++) {

            if (visited[i]) continue;

            visited[i] = true;

            int n = num;
            switch (O[i]) {
                case 0: n += A[cnt]; break;
                case 1: n -= A[cnt]; break;
                case 2: n *= A[cnt]; break;
                case 3: n /= A[cnt]; break;
            }

            dfs(cnt + 1, n, A, O, V, visited);

            visited[i] = false;

        }

    }

    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        if (n + 48 == '-') {
            n = ~(System.in.read() - 48) + 1;
            while ((c = System.in.read()) > 32) n = 10 * n - c + 48;
        } else while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        if (c == 13) System.in.read();
        return n;
    }

}