package boj1368.kruskal;

class NonAPI {

    private static int N;
    private static final int DEFAULT = -1;
    private static final int BIT = 9;
    private static final int MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        N = read();

        int[][] paddyField = new int[N + 1][N + 1];
        int[] disjointSet = new int[N + 1];
        Heap edges = new Heap();

        for (int i = 1; i <= N; i++)
            edges.offer(new Edge(i, read()));

        disjointSet[0] = DEFAULT;
        for (int u = 1; u <= N; u++) {
            disjointSet[u] = DEFAULT;
            for (int v = 1; v <= N; v++)
                edges.offer(new Edge(u | v << BIT, read()));
            edges.poll();
        }

        System.out.print(kruskal(paddyField, disjointSet, edges));
        
    }

    private static long kruskal(int[][] paddyField, int[] set, Heap edges) {

        long mst = 0;

        while (!edges.isEmpty()) {

            Edge edge = edges.poll();

            int u = edge.c & MASK;
            int v = edge.c >> BIT;

            if (union(u, v, set)) mst += edge.l;

        }

        return mst;

    }

    private static boolean union(int u, int v, int[] set) {

        if ((u = find(u, set)) == (v = find(v, set)))
            return false;

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

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}

class Edge {

    int c, l;

    Edge(int c, int l) {
        this.c = c;
        this.l = l;
    }

}

class Heap {

    private int size = 0;
    private int length = 16;
    private Edge[] heap = new Edge[length];

    boolean isEmpty() {
        return size == 0;
    }

    void offer(Edge edge) {

        if (++size == length)
            expandHeapSize();

        heap[size] = edge;

        int index = size << 1;

        while ((index >>= 1) > 1)
            if (!swap(index)) break;

    }

    Edge poll() {

        int index = 1;
        Edge edge = heap[index];
        heap[index] = heap[size--];

        while ((index <<= 1) <= size) {
            if (index < size && heap[index].l > heap[index + 1].l) index++;
            if (!swap(index)) break;
        }

        return edge;

    }

    private boolean swap(int childIndex) {

        int parentIndex = childIndex >> 1;

        Edge parentEdge = heap[parentIndex];
        Edge childEdge = heap[childIndex];

        if (parentEdge.l < childEdge.l)
            return false;

        heap[parentIndex] = childEdge;
        heap[childIndex] = parentEdge;

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