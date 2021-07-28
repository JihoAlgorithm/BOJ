package boj1368.kruskal;

public class LongHeap {

    private static int N;
    private static final int DEFAULT = -1;
    private static final int BIT = 9;
    private static final int MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        N = read();

        int[][] paddyField = new int[N + 1][N + 1];
        int[] disjointSet = new int[N + 1];
        PrimitiveLongHeap edges = new PrimitiveLongHeap();

        for (int u = 1; u <= N; u++)
            edges.offer(getEdge(u, 0, read()));

        disjointSet[0] = DEFAULT;
        for (int u = 1; u <= N; u++) {
            disjointSet[u] = DEFAULT;
            for (int v = 1; v <= N; v++)
                edges.offer(getEdge(u, v, read()));
            edges.poll();
        }

        System.out.print(kruskal(paddyField, disjointSet, edges));
        
    }

    private static long getEdge(int u, int v, long l) {
        return (l << BIT | u) << BIT | v;
    }

    private static int kruskal(int[][] paddyField, int[] set, PrimitiveLongHeap edges) {

        int mst = 0;

        while (!edges.isEmpty()) {

            long edge = edges.poll();

            int u = (int) edge & MASK;
            int v = (int) (edge >> BIT) & MASK;

            if (union(u, v, set)) mst += edge >> BIT >> BIT;

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

class PrimitiveLongHeap {

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

        if (parentValue < childValue) return false;

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
