package boj15961;

public class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), D = read(), K = read(), C = read();
        int[] dish = new int[N];
        int[] kind = new int[D + 1];

        for (int i = 0; i < N; i++) dish[i] = read();

        kind[C]++;
        int cnt = 1;
        for (int i = 0; i < K; i++)
            if (kind[dish[i]]++ == 0) cnt++;

        int max = cnt;
        C = N - K;

        for (int i = 0; i < C; i++) {
            if (--kind[dish[i]] == 0) cnt--;
            if (++kind[dish[K + i]] == 1) cnt++;
            if (max < cnt) max = cnt;
        }

        D = 0;

        for (int i = C; i < N; i++) {
            if (--kind[dish[i]] == 0) cnt--;
            if (++kind[dish[D++]] == 1) cnt++;
            if (max < cnt) max = cnt;
        }

        System.out.println(max);

    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

}