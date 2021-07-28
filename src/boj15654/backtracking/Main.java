package boj15654.backtracking;

class Main {

    private static StringBuilder sb;
    private static int N, M, visited;
    private static int[] numbers, selected;

    public static void main(String[] args) throws Exception {

        sb = new StringBuilder();
        N = read();
        M = read();

        numbers = new int[N];
        selected = new int[M];

        for (int i = 0; i < N; i++) numbers[i] = read();

        quickSort(numbers, 0, N - 1);

        dfs(0);

        System.out.print(sb);

    }

    private static void dfs(int count) throws Exception {

        if (count == M) {

            for (int i = 0; i < M; i++)
                sb.append(selected[i]).append(' ');

            sb.append('\n');

            return;

        }

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            visited |= 1 << i;
            selected[count] = numbers[i];
            dfs(count + 1);
            visited ^= 1 << i;
        }

    }

    private static void quickSort(int[] arr, int l, int r) {

        if (l >= r) return;

        int m = partition(arr, l, r);
        quickSort(arr, l, m - 1);
        quickSort(arr, m, r);

    }

    private static int partition(int[] arr, int l, int r) {

        int p = arr[l + r >> 1];

        while (l <= r) {

            while (arr[l] < p) l++;
            while (arr[r] > p) r--;

            if (l <= r) swap(arr, l++, r--);

        }

        return l;

    }

    private static void swap(int[] arr, int l, int r) {
        int t  = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
