package boj1774.kruskal;

class Main {

    private static final int BIT = 10;
    private static final int MASK = 1023;

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        Coordinate[] coordinates = new Coordinate[N + 1];

        for (int i = 1; i <= N; i++)
            coordinates[i] = new Coordinate(read(), read());

        int[] disjointSet = new int[N + 1];
        for (int i = 1; i <= N; i++)
            disjointSet[i] = i;

        int edgeCount = 1;
        for (int i = 0; i < M; i++)
            if (union(read(), read(), disjointSet))
                edgeCount++;

        Heap edges = new Heap();

        for (int i = 1; i < N; i++)
            for (int j = i + 1; j <= N; j++)
                edges.offer(getEncodeValue(i, j, coordinates[i], coordinates[j]));

        double pathLength = kruskal(disjointSet, edges, edgeCount, N);

        System.out.printf("%.2f", pathLength);

    }

    private static double kruskal(int[] set, Heap edges, int edgeCount, int N) {

        double mst = 0;

        while (edgeCount < N) {

            long edgeStatus = edges.poll();
            int v = (int) ((edgeStatus >> BIT) & MASK);
            int w = (int) (edgeStatus & MASK);

            if (union(v, w, set)) {
                edgeStatus = edgeStatus >> BIT >> BIT;
                mst += Math.sqrt(edgeStatus);
                edgeCount++;
            }

        }

        return mst;

    }

    private static long getEncodeValue(int v, int w, Coordinate a, Coordinate b) {
        long x = a.x - b.x;
        long y = a.y - b.y;
        long value = x * x + y * y;
        return (value << BIT | v) << BIT | w;
    }

    private static boolean union(int v, int w, int[] set) {

        v = find(v, set);
        w = find(w, set);

        if (v == w)
            return false;

        set[v] = w;

        return true;

    }

    private static int find(int v, int[] set) {

        if (set[v] == v)
            return v;

        return set[v] = find(set[v], set);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13)
            System.in.read();

        return n;

    }

    private static class Coordinate {

        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

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

        long[] tmpHeap = heap;
        long[] newHeap = heap = new long[length << 1];

        for (int i = 1; i < length; i++)
            newHeap[i] = tmpHeap[i];

        length <<= 1;

    }

}
