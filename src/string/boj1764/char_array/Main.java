package string.boj1764.char_array;

import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();
        char[] name;

        PriorityQueue<char[]> pq = new PriorityQueue<>((a, b) -> {

            for (int i = 0; i < 20; i++)
                if (a[i] != b[i]) return a[i] - b[i];

            return 0;

        });

        int NM = N + M;

        for (int i = 0; i < NM; i++) {

            int c, x = 0;
            name = new char[20];

            while ((c = System.in.read()) > 96)
                name[x++] = (char) c;

            pq.offer(name);

            if (c == 13) System.in.read();

        }

        StringBuilder sb = new StringBuilder();
        char[] prev = pq.poll();
        boolean isSame;
        int unknown = 0;

        while (!pq.isEmpty()) {

            isSame = true;

            char[] now = pq.poll();

            for (int i = 0; i < 20; i++)
                if (prev[i] != now[i]) {
                    isSame = false;
                    break;
                }

            if (isSame) {
                unknown++;
                for (int i = 0; now[i] > 0; i++)
                    sb.append(now[i]);
                sb.append('\n');
            }

            if (isSame && !pq.isEmpty()) prev = pq.poll();
            else prev = now;

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
