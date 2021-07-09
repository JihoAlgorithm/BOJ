package boj4386.prim;

class Main {

    private static int N;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int POINT = 46;
    private static final int SPACE = 32;
    private static final int PRECISION = 10;

    public static void main(String[] args) throws Exception {

        N = read(SPACE);

        int[][] stars = new int[N][2];
        long[][] graph = new long[N][N];

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
                graph[u][v] = graph[v][u] = getLength(u, v, stars);

        System.out.print(prim(graph));

    }

    private static double prim(long[][] graph) {

        double mst = 0;

        int edgeCount = N;
        Heap edges = new Heap();
        BinaryVisited visited = new BinaryVisited(N);

        edges.offer(new Edge(0, 0));

        while (edgeCount > 0) {

            Edge edge = edges.poll();
            int u = edge.vertex;

            if (visited.isVisited(u)) continue;
            visited.visit(u);

            mst += sqrt(edge.length * 0.0001);
            edgeCount--;

            for (int v = 0; v < N; v++)
                if (!visited.isVisited(v))
                    edges.offer(new Edge(v, graph[u][v]));

        }
        
        return mst;

    }

    private static long getLength(int u, int v, int[][] stars) {
        long dx = stars[u][X] - stars[v][X];
        long dy = stars[u][Y] - stars[v][Y];
        return dx * dx + dy * dy;
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

}

class BinaryVisited {

    private int[] visited;

    private static final int B = 5;
    private static final int M = ~(-1 << B);

    BinaryVisited(int N) {
        visited = new int[(N >> B) + 1];
    }

    boolean isVisited(int v) {
        return (visited[v >> B] & (1 << (v & M))) != 0;
    }

    void visit(int v) {
        visited[v >> B] |= 1 << (v & M);
    }

}

class Edge {

    int vertex;
    long length;

    Edge(int v, long l) {
        vertex = v;
        length = l;
    }

}

class Heap {

    private int size = 0;
    private int length = 16;
    private Edge[] heap = new Edge[length];

    boolean isEmpty() {
        return size == 0;
    }

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
            if (index < size && heap[index].length > heap[index + 1].length) index++;
            if (!swap(index)) break;
        }

        return element;

    }

    private boolean swap(int childIndex) {

        int parentIndex = childIndex >> 1;

        Edge parentValue = heap[parentIndex];
        Edge childValue = heap[childIndex];

        if (parentValue.length < childValue.length)
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