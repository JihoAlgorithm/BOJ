package boj15652;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Main {

    private static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = System.in.read() - 48; System.in.read();
        M = System.in.read() - 48;

        char[] res = new char[M << 1];
        for (int i = 0; i < M; i++)
            res[(i << 1) + 1] = ' ';

        dfs(0, 0, res, bw);

        bw.close();

    }

    private static void dfs(int cnt, int start, char[] res, BufferedWriter bw) throws IOException {

        if (cnt == M) {

            bw.write(res);
            bw.newLine();
            return;

        }

        for (int i = start; i < N; i++) {

            res[cnt << 1] = (char) (i + 49);
            dfs(cnt + 1, i, res, bw);

        }

    }

}