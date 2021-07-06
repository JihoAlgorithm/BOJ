package boj17472.prim;

class Main {

    private static int N, M, ISLAND;

    private static final int SIZE = 0;
    private static final int DEFAULT = -1;
    private static final int BIT = 4;
    private static final int MASK = 15;

    private static final int[] dr = { -1, 1, 0, 0 };
    private static final int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {

        N = read();
        M = read();

        int[][] map = new int[N][M];

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (read() == 1) map[r][c] = DEFAULT;

        Heap queue = new Heap();

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] < 0) bfs(r, c, ++ISLAND, map, queue);
            
        int[][] graph = new int[ISLAND + 1][7];
        graph[SIZE] = new int[ISLAND + 1];
            
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                setEdges(r, c, map[r][c], map, graph);

        System.out.print(prim(map, graph, queue));

    }


    private static int prim(int[][] map, int[][] graph, Heap queue) {
        
        int mst = 0;

        long visited = 0;
        int adjListSize;
        int[] adjList;

        queue.offer(1);

        while (!queue.isEmpty()) {

            int edgeStatus = queue.poll();
            int u = edgeStatus & MASK;

            if ((visited & (1L << u)) != 0) continue;

            visited |= 1L << u;
            mst += edgeStatus >> BIT;
            ISLAND--;

            adjList = graph[u];
            adjListSize = graph[SIZE][u];

            for (int i = 0; i < adjListSize; i++)
                queue.offer(adjList[i]);

        }

        return ISLAND > 0 ? -1 : mst;

    }


    private static void setEdges(int r, int c, int islandNumber, int[][] map, int[][] graph) {

        if (islandNumber == 0) return;

        for (int d = 0; d < 4; d++) {

            int nr = r;
            int nc = c;
            int pathLength = 0;
            int mapValue;

            while (true) {

                if (isOut(nr += dr[d], nc += dc[d]) || (mapValue = map[nr][nc]) == islandNumber)
                    break;

                if (mapValue > 0) {
                    if (pathLength > 1)
                        addEdge(graph, islandNumber, mapValue, pathLength);
                    break;
                }

                pathLength++;

            }

        }

    }

    private static void addEdge(int[][] graph, int u, int v, int pathLength) {

        int adjListSize = graph[SIZE][u]++;
        int adjListLength = graph[u].length;

        if (adjListSize == adjListLength)
            expandListSize(graph, u, adjListLength);

        graph[u][adjListSize] = pathLength << BIT | v;

    }


    private static void expandListSize(int[][] graph, int u, int adjListLength) {

        int[] tmpList = graph[u];
        int[] newList = graph[u] = new int[adjListLength << 1];

        for (int i = 0; i < adjListLength; i++)
            newList[i] = tmpList[i];

    }

    private static void bfs(int r, int c, int islandNumber, int[][] map, Heap queue) {

        queue.offer(r << BIT | c);
        map[r][c] = islandNumber;

        while (!queue.isEmpty()) {

            int coordinate = queue.poll();

            r = coordinate >> BIT;
            c = coordinate & MASK;

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isOut(nr, nc) && map[nr][nc] < 0) {
                    map[nr][nc] = islandNumber;
                    queue.offer(nr << BIT | nc);
                }

            }

        }

    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

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