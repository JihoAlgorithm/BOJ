package boj2805;

import java.io.*;
import java.util.*;

class OnCollection {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> trees = new ArrayList<>(N);

        st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens())
            trees.add(Integer.parseInt(st.nextToken()));

        Collections.sort(trees);

        int m = 0, l = 0, r = trees.get(N - 1);
        boolean flag = false;

        while (l <= r) {

            m = (l + r) >> 1;
            int idx = Collections.binarySearch(trees, m);
            if (idx < 0) idx = ~idx;

            long sum = 0;
            for (int i = idx; i < N; i++)
                sum += trees.get(i) - m;

            if (sum > M) l = m + 1;
            else {
                r = m - 1;
                if (sum == M) flag = true;
            }

        }

        if (flag) l += 1;
        System.out.print(l - 1);

    }

}
