package boj20040;

class BOJ20040_UnionFind {

    public static void main(String[] args) throws Exception {

        int N = read(), M = read(), s[] = new int[N + 1], v, w, round;

        for (round = 0; round < M; round++) {
            if (s[v = read() + 1] == 0)
                s[v] = v;
            if (s[w = read() + 1] == 0)
                s[w] = w;
            if (union(v, w, s))
                break;
        }

        System.out.print(round == M ? 0 : round + 1);

    }

    private static boolean union(int v, int w, int[] s) {
        v = find(v, s);
        w = find(w, s);
        if (v == w)
            return true;
        s[v] = s[w];
        return false;
    }

    private static int find(int v, int[] s) {
        return s[v] == v ? v : (s[v] = find(s[v], s));
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}