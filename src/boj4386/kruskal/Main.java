package boj4386.kruskal;

class Main {

    private static int N;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int BIT = 7;
    private static final int MASK = 127;
    private static final int POINT = 46;
    private static final int SPACE = 32;
    private static final int DEFAULT = -1;
    private static final int PRECISION = 10;

    public static void main(String[] args) throws Exception {

        N = read(SPACE);

        int[][] stars = new int[N][2];
        double[][] graph = new double[N][N];
        Heap edges = new Heap();

        for (int n, f, i = 0; i < N; i++) {

            n = read(POINT);
            if ((f = read(SPACE)) < 10) f = (f << 3) + (f << 1);
            stars[i][X] = (n << 6) + (n << 5) + (n << 2) + f;

            n = read(POINT);
            if ((f = read(SPACE)) < 10) f = (f << 3) + (f << 1);
            stars[i][Y] = (n << 6) + (n << 5) + (n << 2) + f;

        }
        
        for (int u = 0; u + 1 < N; u++)
            for (int v = u + 1; v < N; v++)
                edges.offer(getEdge(u, v, stars));

        System.out.print(kruskal(graph, edges));

    }

    private static double kruskal(double[][] graph, Heap edges) {

        double mst = 0;
        int edgeCount = N;

        int[] disjointSet = new int[N];
        for (int i = 0; i < N; i++) disjointSet[i] = DEFAULT;

        while (edgeCount > 1) {

            long edge = edges.poll();

            int u = (int) ((edge >> BIT) & MASK);
            int v = (int) (edge & MASK);

            if (union(u, v, disjointSet)) {
                mst += sqrt(edge >> BIT >> BIT);
                edgeCount--;
            }

        }
        
        return mst;

    }

    private static boolean union(int u, int v, int[] set) {

        u = find(u, set);
        v = find(v, set);

        if (u == v) return false;

        if (set[u] < set[v]) {
            set[u] += set[v];
            set[v] = u;
        } else {
            set[v] += set[u];
            set[u] = v;
        }

        return true;
    }

    private static int find(int u, int[] s) {
        return s[u] < 0 ? u : (s[u] = find(s[u], s));
    }

    private static long getEdge(int u, int v, int[][] stars) {
        return (getDistance(u, v, stars) << BIT | u) << BIT | v;
    }

    private static long getDistance(int u, int v, int[][] stars) {
        long dx = stars[u][X] - stars[v][X];
        long dy = stars[u][Y] - stars[v][Y];
        return dx * dx + dy * dy;
    }

    private static double sqrt(double d) {

        double x = PRECISION;
        d *= 0.0001;

        for (int i = 0; i < PRECISION; i++)
            x = 0.5 * (d / x + x);

        return x;

    }

    private static int read(int delim) throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > delim)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}

class Heap {
    
    private int size = 0;
    private int length = 16;
    private long[] heap = new long[length];

    void offer(long element) {

        if (++size == length)
            expandHeapSize();

        heap[size] = element;

        int index = size << 1;

        while ((index >>= 1) > 1)
            if (!swap(index)) break;

    }

    long poll() {

        int index = 1;
        long element = heap[index];
        heap[index] = heap[size--];

        while ((index <<= 1) <= size) {
            if (index < size && heap[index] > heap[index + 1]) index++;
            if (!swap(index)) break;
        }

        return element;

    }

    private boolean swap(int childIndex) {

        int parentIndex = childIndex >> 1;

        long parentValue = heap[parentIndex];
        long childValue = heap[childIndex];

        if (parentValue < childValue)
            return false;

        heap[parentIndex] = childValue;
        heap[childIndex] = parentValue;

        return true;

    }

    private void expandHeapSize() {

        long[] tempHeap = heap;
        heap = new long[length << 1];

        for (int i = 0; i < length; i++)
            heap[i] = tempHeap[i];

        length <<= 1;

    }

}