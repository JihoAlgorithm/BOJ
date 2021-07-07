package boj17472.prim;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Readability {

    private static int N, M, ISLAND;
    private static int[][] map;
    private static List<List<Bridge>> graph;

    private static final int DEFAULT = -1;
    private static final int[] dr = { -1, 1, 0, 0 };
    private static final int[] dc = { 0, 0, -1, 1 };

    private static class Bridge {

        int vertex, length;

        Bridge(int v, int l) {
            this.vertex = v;
            this.length = l;
        }

    }

    public static void main(String[] args) throws Exception {

        setMap();
        setBridges();
        System.out.println(prim());

    }

    private static int prim() {
        
        int mst = 0;
        int edgeCount = ISLAND;

        PriorityQueue<Bridge> queue = new PriorityQueue<>((u, v) -> u.length - v.length);
        List<Bridge> adjList;
        boolean[] visited = new boolean[ISLAND + 1];

        queue.offer(new Bridge(1, 0));

        while (!queue.isEmpty()) {

            Bridge bridge = queue.poll();
            int u = bridge.vertex;

            if (visited[u]) continue;

            visited[u] = true;
            mst += bridge.length;
            edgeCount--;

            adjList = graph.get(u);

            for (Bridge b : adjList)
                queue.offer(b);

        }

        return edgeCount > 0 ? -1 : mst;

    }

    private static void setMap() throws Exception {

        N = read();
        M = read();
        map = new int[N][M];

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (read() == 1) map[r][c] = DEFAULT;

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++)
                if (map[r][c] == DEFAULT) dfs(r, c, ++ISLAND);

    }

    private static void dfs(int r, int c, int islandNumber) {

        map[r][c] = islandNumber;

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isOut(nr, nc) && map[nr][nc] < 0)
                dfs(nr, nc, islandNumber);

        }

    }

    private static void setBridges() {

        graph = new ArrayList<>(ISLAND + 1);
        for (int i = 0; i <= ISLAND; i++) graph.add(new ArrayList<>());

        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++) 
                if (map[r][c] > 0) addBridge(r, c, map[r][c]);

    }

    private static void addBridge(int r, int c, int start) {

        int end, nr, nc, length;

        for (int d = 0; d < 4; d++) {

            nr = r;
            nc = c;
            length = 0;

            while (true) {

                nr += dr[d];
                nc += dc[d];

                if (isOut(nr, nc) || map[nr][nc] == start) break;

                end = map[nr][nc];

                if (end > 0) {
                    if (length > 1)
                        graph.get(start).add(new Bridge(end, length));
                    break;
                }

                length++;

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
