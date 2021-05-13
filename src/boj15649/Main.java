package boj15649;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Main {

    private static int N, M;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        N = System.in.read() - 48; System.in.read();
        M = System.in.read() - 48;

        dfs(0, new boolean[N], new char[M]);

        bw.close();

    }

    private static void dfs(int cnt, boolean[] visited, char[] tmp) throws IOException {

        if (cnt == M) {

            for (int i = 0; i < M ; i++)
                bw.append(tmp[i]).append(' ');

            bw.newLine();
            return;

        }

        for (int i = 0; i < N; i++) {

            if (visited[i]) continue;

            visited[i] = true;
            tmp[cnt] = (char) (i + 48);
            dfs(cnt + 1, visited, tmp);
            visited[i] = false;

        }

    }

}