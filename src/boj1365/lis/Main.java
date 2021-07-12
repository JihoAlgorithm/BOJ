package boj1365.lis;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int index = 1;
        int[] lis = new int[N + 1];

        for (int from = 1; from <= N; from++) {

            int to = read();

            if (to > lis[index - 1])
                lis[index++] = to;
            else
                lis[lowerBound(to, lis, 1, index)] = to;

        }

        System.out.println(N - index + 1);

    }

    private static int lowerBound(int key, int[] array, int l, int r) {

        int m;

        while (l < r)
            if (key > array[m = l + r >> 1]) l = m + 1;
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
