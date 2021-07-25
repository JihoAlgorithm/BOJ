package string.boj1764.normal;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {

            for (int i = 0; i < 20; i++)
                if (a[i] != b[i]) return a[i] - b[i];

            return 0;

        });

        int[][] nonHear = getList(N, pq);
        int[][] nonLook = getList(M, pq);

        int unknown = 0, i = 0, j = 0;

        while (i < N && j < M) {

            if (nonHear[i][0] != nonLook[j][0]) {
                if (nonHear[i][0] > nonLook[j][0]) j++;
                else i++;
                continue;
            }

            boolean isSame = true;

            for (int k = 1; k < 20; k++) {
                if (nonHear[i][k] != nonLook[j][k]) {
                    if (nonHear[i][k] > nonLook[j][k]) j++;
                    else i++;
                    isSame = false;
                    break;
                }
            }

            if (isSame) {
                pq.offer(nonLook[j]);
                i++;
                j++;
                unknown++;
            }

        }

        StringBuilder sb = new StringBuilder();

        sb.append(unknown).append('\n');

        int[] name;

        while (!pq.isEmpty()) {

            name = pq.poll();

            for (int k = 0; name[k] > 0; k++)
                sb.append((char) name[k]);

            sb.append('\n');

        }

        System.out.print(sb);

    }

    private static int[][] getList(int num, PriorityQueue<int[]> pq) throws Exception {

        int[][] list = new int[num][20];
        int[] name;

        for (int i = 0; i < num; i++) {

            int c, x = 0;
            name = list[i];

            while ((c = System.in.read()) > 96)
                name[x++] = c;

            pq.offer(name);

            if (c == 13) System.in.read();

        }

        for (int i = 0; i < num; i++) list[i] = pq.poll();

        return list;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}