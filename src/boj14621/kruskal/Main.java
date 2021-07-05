package boj14621.kruskal;

class Main {

    private static final int MAN = 13;
    private static final int DEFAULT = -1;
    private static final int BIT = 10;
    private static final int MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        BinaryVisited colleges = new BinaryVisited(N);

        for (int i = 1; i <= N; i++)
            if (read() == MAN)
                colleges.setMan(i);

        Heap edges = new Heap();

        for (int i = 0; i < M; i++) {

            int u = read();
            int v = read();
            int d = read();

            if (colleges.isMan(u) == colleges.isMan(v))
                continue;

            edges.offer(getEncodeValue(u, v, d));

        }

        int[] disjointSet = new int[N + 1];

        for (int i = 1; i <= N; i++)
            disjointSet[i] = DEFAULT;

        int sumOfLoveLine = kruskal(disjointSet, edges, N);

        System.out.print(sumOfLoveLine);

    }

    private static int kruskal(int[] set, Heap edges, int setCount) {

        int mst = 0;

        while (!edges.isEmpty()) {

            int edgeStatus = edges.poll();

            int u = (edgeStatus >> BIT) & MASK;
            int v = edgeStatus & MASK;

            if (union(u, v, set)) {
                mst += edgeStatus >> BIT >> BIT;
                setCount--;
            }

        }

        return setCount > 1 ? -1 : mst;

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

    private static int getEncodeValue(int u, int v, int d) {
        return (d << BIT | u) << BIT | v;
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

class BinaryVisited {

    private int[] visited;

    private static final int B = 5;
    private static final int M = ~(-1 << B);

    BinaryVisited(int N) {
        visited = new int[(N >> B) + 1];
    }

    boolean isMan(int v) {
        return (visited[v >> B] & (1 << (v & M))) != 0;
    }

    void setMan(int v) {
        visited[v >> B] |= 1 << (v & M);
    }

}

class Heap {

    private int size = 0;
    private int length = 16;
    private int[] heap = new int[length];

    boolean isEmpty() {
        return size == 0;
    }

    void offer(int element) {

        if (++size == length)
            expandHeapSize();

        heap[size] = element;

        int index = size << 1;

        while ((index >>= 1) > 1)
            if (!swap(index))
                break;

    }

    int poll() {

        int index = 1;
        int element = heap[index];

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

        int parentValue = heap[parentIndex];
        int childValue = heap[childIndex];

        if (parentValue < childValue)
            return false;

        heap[parentIndex] = childValue;
        heap[childIndex] = parentValue;

        return true;

    }

    private void expandHeapSize() {

        int[] tmpHeap = heap;
        heap = new int[length << 1];

        for (int i = 1; i < length; i++)
            heap[i] = tmpHeap[i];

        length <<= 1;

    }

}