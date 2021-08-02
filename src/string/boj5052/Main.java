package string.boj5052;

class Main {

    private static final String YES = "YES\n";
    private static final String NO = "NO\n";

    public static void main(String[] args) throws Exception {

        int T = (int) read();

        long[] list = new long[10000];

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            int N = (int) read();
            long min = 9999999999L;

            for (int i = 0; i < N; i++)
                if (min > (list[i] = read()))
                    min = list[i];

            int minSize = getSize(min);
            boolean isSame = false;

            for (int i = 0; i < N; i++) {

                if (min == list[i])
                    continue;

                int diffSize = getSize(list[i]) - minSize;

                while (diffSize-- > 0)
                    list[i] /= 10;

                if (min == list[i]) {
                    isSame = true;
                    break;
                }

            }

            sb.append(isSame ? NO : YES);

        }

        System.out.print(sb);

    }

    private static int getSize(long num) {

        int size = 0;

        while ((num /= 10) > 0)
            size++;

        return size;

    }

    private static long read() throws Exception {

        int c;
        long n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13)
            System.in.read();

        return n;

    }

}
