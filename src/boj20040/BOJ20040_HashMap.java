package boj20040;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class BOJ20040_HashMap {

    private static int N;
    private static final int MASK = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        List<Set<Integer>> sets = new ArrayList<>((N = read()) >> 1);
        int[] info = new int[N + 1];

        int M = read(), p, c, answer = 0;
        Set<Integer> pset, cset;

        for (int t = 1; t <= M; t++) {

            pset = getSet(info, sets, p = read(), (info[p] & 1) == 1);
            cset = getSet(info, sets, c = read(), (info[c] & 1) == 1);

            if (pset == cset) {
                answer = t;
                break;
            } else {
                pset.addAll(cset);
                info[c] = info[p];
            }

        }

        System.out.print(answer);

    }

    private static Set<Integer> getSet(int[] info, List<Set<Integer>> sets, int v, boolean visited) {

        if (visited)
            return sets.get((info[v] >> 1) & MASK);

        info[v] = (info[N]++ << 1) | 1;
        Set<Integer> set = new HashSet<>();
        set.add(v);
        sets.add(set);

        return set;

    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }

}
