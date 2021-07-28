package boj17472.kruskal;

class Main {

    private static int N, M, ISLAND;
    private static int[][] map;
    private static Heap edges;

    private static final int[] dr = { -1, 1, 0, 0 };
    private static final int[] dc = { 0, 0, -1, 1 };

    private static final int DEFAULT = -1;
    private static final int BIT = 4;
    private static final int MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        setMap();
        setEdge();
        System.out.print(kruskal());

    }

    private static int kruskal() {

        int mst = 0;
        int edgeCount = ISLAND;

        int[] disjointSet = new int[ISLAND + 1];
        for (int i = 1; i <= ISLAND; i++)
            disjointSet[i] = DEFAULT;

        while (!edges.isEmpty()) {

            int edgeStatus = edges.poll();

            int u = (edgeStatus >> BIT) & MASK;
            int v = edgeStatus & MASK;

            if (union(u, v, disjointSet)) {
                mst += edgeStatus >> BIT >> BIT;
                edgeCount--;
            }

        }

        return edgeCount > 1 ? -1 : mst;

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

    private static void setMap() throws Exception {

        N = read();
        M = read();
        map = new int[N][M];

        int[] visited = new int[N];

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                map[r][c] = read();

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] > 0 && !isVisited(r, c, visited))
                    dfs(r, c, ++ISLAND, map, visited);

    }

    private static void dfs(int r, int c, int islandNumber, int[][] map, int[] visited) {

        map[r][c] = islandNumber;
        visited[r] |= 1 << c;

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr, nc) || map[nr][nc] == 0 || isVisited(nr, nc, visited))
                continue;

            dfs(nr, nc, islandNumber, map, visited);

        }

    }

    private static void setEdge() {

        edges = new Heap();

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] > 0)
                    setEdge(r, c);

    }

    private static void setEdge(int r, int c) {

        int islandNumber = map[r][c];
        int mapValue, nr, nc, len;

        for (int d = 0; d < 4; d++) {

            nr = r;
            nc = c;
            len = 0;

            while (true) {

                if (isOut(nr += dr[d], nc += dc[d]) || (mapValue = map[nr][nc]) == islandNumber)
                    break;

                if (mapValue > 0) {
                    if (len > 1)
                        edges.offer(getEdgeEncodeValue(islandNumber, mapValue, len));
                    break;
                }

                len++;

            }

        }

    }

    private static int getEdgeEncodeValue(int u, int v, int d) {
        return (d << BIT | u) << BIT | v;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

    private static boolean isVisited(int r, int c, int[] visited) {
        return (visited[r] & (1 << c)) != 0;
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

        int[] tempHeap = heap;
        heap = new int[length << 1];

        for (int i = 0; i < length; i++)
            heap[i] = tempHeap[i];

        length <<= 1;

    }

}