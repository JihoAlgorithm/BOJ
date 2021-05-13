package boj1450;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), C = read(), size = N >> 1, temp = (N & 1) == 0 ? size : size + 1;

        int[] left = new int[size];
        int[] right = new int[temp];

        for (int i = 0; i < size; i++)
            left[i] = read();
        for (int i = 0; i < temp; i++)
            right[i] = read();

        int l = 1 << size;
        int r = 1 << temp;

        long[] leftSet = new long[l];
        long[] rightSet = new long[r];

        powerSet(0, 0, size, left, leftSet);
        powerSet(0, 0, temp, right, rightSet);

        quickSort(rightSet, 0, r - 1);

        int count = 0;
        size = l;
        temp = upperBound(rightSet, C, 0, r);

        for (int i = 0; i < size; i++) {

            long p = C - leftSet[i];
            if (p < 0)
                continue;

            count += upperBound(rightSet, p, 0, temp);

        }

        System.out.print(count);

    }

    private static int upperBound(long[] a, long k, int l, int r) {

        int m;

        while (l < r)
            if (a[m = l + r >> 1] <= k)
                l = m + 1;
            else
                r = m;

        return r;

    }

    private static void quickSort(long[] arr, int l, int r) {

        if (l >= r)
            return;

        int m = partition(arr, l, r);
        quickSort(arr, l, m - 1);
        quickSort(arr, m, r);

    }

    private static int partition(long[] arr, int l, int r) {

        long p = arr[l + r >> 1];

        while (l <= r) {

            while (arr[l] < p)
                l++;
            while (arr[r] > p)
                r--;

            if (l <= r)
                swap(arr, l++, r--);

        }

        return l;

    }

    private static void swap(long[] arr, int a, int b) {
        long t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    private static void powerSet(int cnt, int visited, int size, int[] origin, long[] set) {

        if (cnt == size) {

            long sum = 0;

            for (int i = 0; i < size; i++)
                if ((visited & (1 << i)) != 0)
                    sum += origin[i];

            set[visited] = sum;

            return;

        }

        powerSet(cnt + 1, visited | (1 << cnt), size, origin, set);
        powerSet(cnt + 1, visited, size, origin, set);

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
