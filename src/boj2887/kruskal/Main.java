package boj2887.kruskal;

class Main {

    private static final int DEFAULT = -1;
    private static final long BIT = 17;
    private static final long MASK = ~(-1L << BIT);

    public static void main(String[] args) throws Exception {

        int N = read();

        Heap xHeap = new Heap();
        Heap yHeap = new Heap();
        Heap zHeap = new Heap();

        for (int i = 1; i <= N; i++) {
            xHeap.offer(getVertexEncodeValue(read(), i));
            yHeap.offer(getVertexEncodeValue(read(), i));
            zHeap.offer(getVertexEncodeValue(read(), i));
        }

        Heap edges = new Heap();
        setEdges(xHeap, edges);
        setEdges(yHeap, edges);
        setEdges(zHeap, edges);

        long sumOfPathLength = kruskal(edges, N);

        System.out.print(sumOfPathLength);

    }

    private static long kruskal(Heap edges, int N) {

        long mst = 0;

        int[] disjointSet = new int[N + 1];
        for (int i = 1; i <= N; i++)
            disjointSet[i] = DEFAULT;

        while (N > 1) {

            long edgeStatus = edges.poll();

            int u = (int) ((edgeStatus >> BIT) & MASK);
            int v = (int) (edgeStatus & MASK);

            if (union(u, v, disjointSet)) {
                mst += edgeStatus >> BIT >> BIT;
                N--;
            }

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

    private static int find(int v, int[] s) {
        return s[v] < 0 ? v : (s[v] = find(s[v], s));
    }

    private static void setEdges(Heap heap, Heap edges) {

        long u, v, d, uStatus, vStatus;

        uStatus = heap.poll();

        while (!heap.isEmpty()) {

            vStatus = heap.poll();

            u = uStatus & MASK;
            v = vStatus & MASK;
            d = (vStatus >> BIT) - (uStatus >> BIT);

            edges.offer(getEdgeEncodeValue(u, v, d));

            uStatus = vStatus;

        }

    }

    private static long getEdgeEncodeValue(long u, long v, long d) {
        return (d << BIT | u) << BIT | v;
    }

    private static long getVertexEncodeValue(long value, int vertex) {
        return value << BIT | vertex;
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;

        if (isNegative)
            n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13)
            System.in.read();

        return isNegative ? ~n + 1 : n;

    }

}

class Heap {

    private int size = 0;
    private int length = 16;
    private long[] heap = new long[length];

    boolean isEmpty() {
        return size == 0;
    }

    void offer(long element) {

        if (++size == length)
            expandHeapSize();

        heap[size] = element;

        int index = size << 1;

        while ((index >>= 1) > 1)
            if (!swap(index))
                break;

    }

    long poll() {

        int index = 1;
        long element = heap[index];

        heap[index] = heap[size--];

        while ((index <<= 1) <= size) {
            if (index < size && heap[index] > heap[index + 1])
                index++;
            if (!swap(index))
                break;
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

        for (int i = 1; i < length; i++)
            heap[i] = tempHeap[i];

        length <<= 1;

    }

}
