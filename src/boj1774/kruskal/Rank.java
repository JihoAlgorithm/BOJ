package boj1774.kruskal;

public class Rank {

    private static final int DEFAULT = -1;
    private static final int BIT = 10;
    private static final int MASK = ~(-1 << BIT);
    private static final long CBIT = 32;
    private static final long CMASK = ~(-1L << CBIT);

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        long[] coordinates = new long[N + 1];

        for (int i = 1; i <= N; i++)
            coordinates[i] = (long) read() << CBIT | read();

        int[] disjointSet = new int[N + 1];
        for (int i = 1; i <= N; i++)
            disjointSet[i] = DEFAULT;

        int edgeCount = 1;
        for (int i = 0; i < M; i++)
            if (union(read(), read(), disjointSet))
                edgeCount += 1;

        Heap edges = new Heap();

        for (int i = 1; i < N; i++)
            for (int j = i + 1; j <= N; j++)
                edges.offer(getEncodeValue(i, j, coordinates[i], coordinates[j]));

        double pathLength = kruskal(disjointSet, edges, N - edgeCount);

        System.out.printf("%.2f", pathLength);

    }

    private static double kruskal(int[] set, Heap edges, int edgeCount) {

        double mst = 0;

        while (edgeCount > 0) {

            long edgeStatus = edges.poll();
            int v = (int) ((edgeStatus >> BIT) & MASK);
            int w = (int) (edgeStatus & MASK);

            if (union(v, w, set)) {
                mst += sqrt4(edgeStatus >> BIT >> BIT);
                edgeCount -= 1;
            }

        }

        return mst;

    }

    private static double sqrt(long num) {

        double x = 2;

        for (int i = 0; i < 10; i++)
            x = (x + (num / x)) / 2;

        return x;
    }

    private static double sqrt4(long n) {

        int i = 1;

        while ((i * i) <= n)
            i++;

        i--;

        double d = n - i * i;
        double p = d / (2 * i);
        double a = i + p;

        return a - (p * p) / (2 * a);

    }

    private static double sqrt5(long n) {

        double a = 0, b = 0;

        while ((a * a) <= n)
            a += 0.1;

        for (int i = 0; i < 18; i++) {
            b = n;
            b /= a;
            b += a;
            b /= 2;
            a = b;
        }

        return b;

    }

    private static long getEncodeValue(int v, int w, long a, long b) {
        long x = (a >> CBIT) - (b >> CBIT);
        long y = (a & CMASK) - (b & CMASK);
        return ((x * x + y * y) << BIT | v) << BIT | w;
    }

    private static boolean union(int v, int w, int[] set) {

        v = find(v, set);
        w = find(w, set);

        if (v == w)
            return false;

        if (set[v] < set[w]) {
            set[v] += set[w];
            set[w] = v;
        } else {
            set[w] += set[v];
            set[v] = w;
        }

        return true;

    }

    private static int find(int v, int[] s) {
        return s[v] < 0 ? v : (s[v] = find(s[v], s));
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13)
            System.in.read();

        return n;

    }

}