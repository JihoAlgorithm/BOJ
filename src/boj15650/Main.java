package boj15650;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {

        int[] in = new int[2];
        in[0] = System.in.read() - 48; System.in.read();
        in[1] = System.in.read() - 48;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] res = new char[in[1] << 1];
        for (int i = 0; i < in[1]; i++)
            res[(i << 1) + 1] = ' ';

        comb(0, 0, new boolean[in[0]], res, in, bw);

        bw.close();

    }

    private static void comb(int s, int c, boolean[] v, char[] n, int[] in, BufferedWriter bw) throws IOException {

        if (c == in[1]) {

            bw.write(n);
            bw.newLine();

            return;

        }

        for (int i = s; i < in[0]; i++) {

            if(v[i]) continue;

            v[i] = true;
            n[c << 1] = (char) (i + 49);
            comb(i, c + 1, v, n, in, bw);
            v[i] = false;

        }

    }

}