package boj17472.prim;

class AdjacencyMatrix {
    
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
            
        int[][] graph = new int[ISLAND + 1][ISLAND + 1];
        for (int u = 1; u <= ISLAND; u++)
            for (int v = 1; v <= ISLAND; v++)
                graph[u][v] = MASK;
            
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] > 0) setEdges(r, c, map[r][c], map, graph);

        System.out.print(prim(map, graph, queue));

    }


    private static int prim(int[][] map, int[][] graph, Heap queue) {
        
        int mst = 0;
        int edgeCount = ISLAND;
        long visited = 0;

        queue.offer(1);

        while (!queue.isEmpty()) {

            int edgeStatus = queue.poll();
            int u = edgeStatus & MASK;

            if ((visited & (1L << u)) != 0) continue;

            visited |= 1L << u;
            mst += edgeStatus >> BIT;
            edgeCount--;

            for (int v = 1; v <= ISLAND; v++)
                if (graph[u][v] < MASK) queue.offer(graph[u][v] << BIT | v);

        }

        return edgeCount > 0 ? -1 : mst;

    }


    private static void setEdges(int r, int c, int islandNumber, int[][] map, int[][] graph) {

        for (int d = 0; d < 4; d++) {

            int nr = r;
            int nc = c;
            int pathLength = 0;
            int mapValue;

            while (true) {

                if (isOut(nr += dr[d], nc += dc[d]) || (mapValue = map[nr][nc]) == islandNumber)
                    break;

                if (mapValue > 0) {
                    if (graph[islandNumber][mapValue] > pathLength && pathLength > 1)
                        graph[islandNumber][mapValue] = pathLength;
                    break;
                }

                pathLength++;

            }

        }

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
