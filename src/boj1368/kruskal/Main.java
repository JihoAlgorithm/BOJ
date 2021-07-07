package boj1368.kruskal;

import java.util.PriorityQueue;

class Main {

    private static int N;
    private static final int DEFAULT = -1;

    private static class Edge {

        int u, v, l;

        Edge(int u, int v, int l) {
            this.u = u;
            this.v = v;
            this.l = l;
        }

    }

    public static void main(String[] args) throws Exception {

        N = read();

        int[][] paddyField = new int[N + 1][N + 1];
        int[] costs = new int[N + 1];
        int[] disjointSet = new int[N + 1];
        PriorityQueue<Edge> edges = new PriorityQueue<>((u, v) -> u.l - v.l);

        for (int i = 1; i <= N; i++)
            edges.offer(new Edge(i, 0, read()));

        disjointSet[0] = DEFAULT;
        for (int u = 1; u <= N; u++) {
            disjointSet[u] = DEFAULT;
            for (int v = 1; v <= N; v++)
                edges.offer(new Edge(u, v, read()));
            edges.poll();
        }

        System.out.print(kruskal(paddyField, disjointSet, edges, costs));
        
    }

    private static long kruskal(int[][] paddyField, int[] set, PriorityQueue<Edge> edges, int[] costs) {

        long mst = 0;

        while (!edges.isEmpty()) {

            Edge edge = edges.poll();

            int u = edge.u;
            int v = edge.v;

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
