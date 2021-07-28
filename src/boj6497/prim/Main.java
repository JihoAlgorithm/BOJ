package boj6497.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    private static class Edge {

        int v, c;

        Edge(int v, int c) {
            this.v = v;
            this.c = c;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        List<List<Edge>> graph = new ArrayList<>(200000);
        
        while (true) {
            
            st = new StringTokenizer(br.readLine(), " ");
            
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int totalCost = 0;
            
            if (M + N == 0) break;

            for (int i = 0; i < M; i++) graph.add(new ArrayList<>());

            while (N-- > 0) {

                st = new StringTokenizer(br.readLine(), " ");

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph.get(u).add(new Edge(v, c));
                graph.get(v).add(new Edge(u, c));

                totalCost += c;

            }

            sb.append(totalCost - prim(graph, M)).append('\n');

            graph.clear();

        }

        System.out.print(sb);

    }

    private static int prim(List<List<Edge>> graph, int M) {

        int mst = 0, edgeCount = M;

        PriorityQueue<Edge> pq = new PriorityQueue<>((u, v) -> u.c - v.c);
        boolean[] visited = new boolean[M];
        List<Edge> adjList;
        Edge edge;

        pq.offer(new Edge(0, 0));

        while (edgeCount > 0) {

            edge = pq.poll();
            int u = edge.v;

            if (visited[u]) continue;
            visited[u] = true;

            mst += edge.c;
            edgeCount--;

            adjList = graph.get(u);

            for (Edge e : adjList)
                if (!visited[e.v]) pq.offer(e);

        }

        return mst;

    }
    
}
