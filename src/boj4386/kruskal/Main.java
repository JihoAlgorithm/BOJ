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

        for (int integer, fractional, i = 0; i < N; i++) {

            integer = read(POINT);
            if ((fractional = read(SPACE)) < 10) fractional = (fractional << 3) + (fractional << 1);
            stars[i][X] = (integer << 6) + (integer << 5) + (integer << 2) + fractional;

            integer = read(POINT);
            if ((fractional = read(SPACE)) < 10) fractional = (fractional << 3) + (fractional << 1);
            stars[i][Y] = (integer << 6) + (integer << 5) + (integer << 2) + fractional;

        }
        
        for (int u = 0; u + 1 < N; u++)
            for (int v = u + 1; v < N; v++)
                edges.offer(new Edge(u, v, getLength(u, v, stars)));

        System.out.print(kruskal(graph, edges));

    }

    private static double kruskal(double[][] graph, Heap edges) {

        double mst = 0;
        int edgeCount = N;

        int[] disjointSet = new int[N];
        for (int i = 0; i < N; i++) disjointSet[i] = DEFAULT;

        while (edgeCount > 1) {

            Edge edge = edges.poll();

            if (union(edge.v >> BIT, edge.v & MASK, disjointSet)) {
                mst += edge.d;
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

    private static double getLength(int u, int v, int[][] stars) {
        long dx = stars[u][X] - stars[v][X];
        long dy = stars[u][Y] - stars[v][Y];
        return sqrt((dx * dx + dy * dy) * 0.0001);
    }

    private static double sqrt(double n) {

        double x = PRECISION;

        for (int i = 0; i < PRECISION; i++)
            x = 0.5 * (n / x + x);

        return x;

    }

    private static int read(int delim) throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > delim)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

    private static class Edge {

        int v;
        double d;
    
        Edge(int u, int v, double d) {
            this.v = u << BIT | v;
            this.d = d;
        }
    
    }
    
    private static class Heap {
    
        private int size = 0;
        private int length = 16;
        private Edge[] heap = new Edge[length];
    
        void offer(Edge element) {
    
            if (++size == length)
                expandHeapSize();
    
            heap[size] = element;
    
            int index = size << 1;
    
            while ((index >>= 1) > 1)
                if (!swap(index)) break;
    
        }
    
        Edge poll() {
    
            int index = 1;
            Edge element = heap[index];
            heap[index] = heap[size--];
    
            while ((index <<= 1) <= size) {
                if (index < size && heap[index].d > heap[index + 1].d) index++;
                if (!swap(index)) break;
            }
    
            return element;
    
        }
    
        private boolean swap(int childIndex) {
    
            int parentIndex = childIndex >> 1;
    
            Edge parentValue = heap[parentIndex];
            Edge childValue = heap[childIndex];
    
            if (parentValue.d < childValue.d)
                return false;
    
            heap[parentIndex] = childValue;
            heap[childIndex] = parentValue;
    
            return true;
    
        }
    
        private void expandHeapSize() {
    
            Edge[] tempHeap = heap;
            heap = new Edge[length << 1];
    
            for (int i = 0; i < length; i++)
                heap[i] = tempHeap[i];
    
            length <<= 1;
    
        }
    
    }

}