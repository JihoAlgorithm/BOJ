package string.boj1764.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        PriorityQueue<String> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int NM = N + M;

        for (int i = 0; i < NM; i++)
            pq.offer(br.readLine());

        StringBuilder sb = new StringBuilder();
        String prev = pq.poll();
        int unknown = 0;

        while (!pq.isEmpty()) {

            String now = pq.poll();

            if (prev.equals(now)) {
                unknown++;
                sb.append(now).append('\n');
                if (!pq.isEmpty()) {
                    prev = pq.poll();
                    continue;
                }
            }

            prev = now;

        }

        System.out.println(unknown);
        System.out.print(sb);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
