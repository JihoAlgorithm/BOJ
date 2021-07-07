package boj4386.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Simple {

    private static final int X = 0, Y = 1;

    private static class Edge {

        int u;
        double l;

        Edge(int u, double l) {
            this.u = u;
            this.l = l;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        double[][] stars = new double[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            stars[i][X] = Double.parseDouble(st.nextToken());
            stars[i][Y] = Double.parseDouble(st.nextToken());
        }

        double[][] graph = new double[N][N];

        for (int u = 0; u < N - 1; u++) {
            for (int v = u + 1; v < N; v++) {
                double dx = stars[u][X] - stars[v][X];
                double dy = stars[u][Y] - stars[v][Y];
                graph[u][v] = graph[v][u] = sqrt(dx * dx + dy * dy);
            }
        }

        System.out.print(prim(graph, N));

    }

    private static double prim(double[][] graph, int N) {

        double mst = 0;
        
        PriorityQueue<Edge> edges = new PriorityQueue<>((u, v) -> (int) (u.l - v.l));
        boolean[] visited = new boolean[N];

        int edgeCount = N;
        edges.offer(new Edge(0, 0));

        while (edgeCount > 0) {

            Edge edge = edges.poll();
            int u = edge.u;

            if (visited[u]) continue;
            visited[u] = true;

            mst += edge.l;
            edgeCount--;

            for (int v = 0; v < N; v++)
                edges.offer(new Edge(v, graph[u][v]));

        }

        return mst;

    }

    private static final int PRECISION = 20;

    private static double sqrt(double n) {
        
        double x = PRECISION;

        for (int i = 0; i < PRECISION; i++)
            x = 0.5 * (n / x + x);

        return x;

    }
    
}