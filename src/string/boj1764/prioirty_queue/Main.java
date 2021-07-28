package string.boj1764.prioirty_queue;

import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {

            for (int i = 0; i < 20; i++)
                if (a[i] != b[i]) return a[i] - b[i];

            return 0;

        });

        int NM = N + M;

        for (int i = 0; i < NM; i++) {

            int c, x = 0;
            int[] name = new int[20];

            while ((c = System.in.read()) > 96)
                name[x++] = c;

            pq.offer(name);

            if (c == 13) System.in.read();

        }

        StringBuilder sb = new StringBuilder();
        int[] prev = pq.poll();
        boolean isSame;
        int unknown = 0;

        while (!pq.isEmpty()) {

            isSame = true;

            int[] now = pq.poll();

            for (int i = 0; i < 20; i++)
                if (prev[i] != now[i]) {
                    isSame = false;
                    break;
                }

            if (isSame) {
                unknown++;
                for (int k = 0; now[k] > 0; k++)
                    sb.append((char) now[k]);
                sb.append('\n');
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