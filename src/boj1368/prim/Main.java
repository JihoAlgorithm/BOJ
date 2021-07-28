package boj1368.prim;

class Main {

    private static final int BIT = 9;
    private static final int MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        int N = read();
        int[][] paddyField = new int[N][N];

        PrimitiveIntegerHeap edges = new PrimitiveIntegerHeap();

        for (int u = 0; u < N; u++)
            edges.offer(read() << BIT | u);

        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                int cost = read();
                if (cost > 0) paddyField[u][v] = cost;
            }
        }

        System.out.print(prim(paddyField, N, edges));
        
    }

    private static int prim(int[][] paddyField, int N, PrimitiveIntegerHeap edges) {

        int mst = 0;
        int edgeCount = N;

        BinaryVisited visited = new BinaryVisited(N);

        while (edgeCount > 0) {

            int edgeStatus = edges.poll();
            int u = edgeStatus & MASK;

            if (visited.isVisited(u)) continue;

            mst += edgeStatus >> BIT;
            edgeCount--;

            for (int v = 0; v < N; v++)
                edges.offer(paddyField[u][v] << BIT | v);

            visited.visit(u);

        }

        return mst;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
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


class PrimitiveIntegerHeap {

    private int size = 0;
    private int length = 16;
    private int[] heap = new int[length];

    int size() {
        return size;
    }

    int get(int index) {
        return heap[index + 1];
    }

    boolean isEmpty() {
        return size == 0;
    }

    void offer(int element) {

        if (++size == length)
            expandHeapSize();

        heap[size] = element;

        int index = size << 1;

        while ((index >>= 1) > 1)
            if (!swap(index)) break;

    }

    int poll() {

        int index = 1;
        int element = heap[index];
        heap[index] = heap[size--];

        while ((index <<= 1) <= size) {
            if (index < size && heap[index] > heap[index + 1]) index++;
            if (!swap(index)) break;
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

        int[] tempHeap = heap;
        heap = new int[length << 1];

        for (int i = 0; i < length; i++)
            heap[i] = tempHeap[i];

        length <<= 1;

    }

}