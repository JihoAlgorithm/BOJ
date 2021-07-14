package boj7795.binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {

        int T = read();

        List<Integer> A;
        List<Integer> B;

        StringBuilder sb = new StringBuilder();

        while (T-->0) {

            int N = read();
            int M = read();

            A = new ArrayList<>(N);
            B = new ArrayList<>(M);

            for (int i = 0; i < N; i++) A.add(read());
            for (int i = 0; i < M; i++) B.add(read());

            Collections.sort(B);

            int count = 0;

            for (int a : A)
                count += lowerBound(a, B, 0, M);

            sb.append(count).append('\n');

        }

        System.out.print(sb);

    }

    private static int lowerBound(int key, List<Integer> list, int l, int r) {

        int m;

        while (l < r)
            if (key > list.get(m = l + r >> 1)) l = m + 1;
            else r = m;

        return l;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
