package boj1300.binary_search;

class Main {

    private static int N;

    public static void main(String[] args) throws Exception {

        N = read();

        int K = read(), left = 1, right = K, mid;

        while (left <= right)
            if (getSmallerNumbersCount(mid = left + right >> 1) < K)
                left = mid + 1;
            else
                right = mid - 1;

        System.out.print(left);

    }

    private static int getSmallerNumbersCount(int mid) {

        int count = 0;

        for (int i = 1; i <= N; i++)
            count += min(mid / i, N);

        return count;

    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}