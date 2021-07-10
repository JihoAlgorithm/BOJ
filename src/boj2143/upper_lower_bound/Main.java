package boj2143.upper_lower_bound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {

        int T = read();
        List<Integer> A = getList(read());
        List<Integer> B = getList(read());
        Collections.sort(A);

        int size = A.size();
        long count = 0;

        for (int number : B) {

            int k = T - number;
            int ub = upperBound(k, A, 0, size);
            int lb = lowerBound(k, A, 0, size);

            count += ub - lb;

        }

        System.out.print(count);

    }

    private static List<Integer> getList(int size) throws Exception {

        List<Integer> list = new ArrayList<>((size * (size + 1)) >> 1);

        for (int i = 0; i < size; i++) list.add(read());
        getPrefixSum(list, size);

        return list;

    }

    private static void getPrefixSum(List<Integer> list, int size) {

        for (int i = 0; i < size; i++) {

            int sum = list.get(i);

            for (int j = i + 1; j < size; j++)
                list.add(sum += list.get(j));

        }


    }

    private static int m;

    private static int lowerBound(int k, List<Integer> list, int l, int r) {

        while (l < r)
            if (k > list.get(m = l + r >> 1)) l = m + 1;
            else r = m;

        return l;

    }

    private static int upperBound(int k, List<Integer> list, int l, int r) {

        while (l < r)
            if (k < list.get(m = l + r >> 1)) r = m;
            else l = m + 1;

        return l;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;

        if (isNegative) n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return isNegative ? ~n + 1 : n;

    }

}