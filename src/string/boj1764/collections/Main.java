package string.boj1764.collections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int NM = N + M, unknown = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < NM; i++)
            list.add(br.readLine());

        Collections.sort(list);

        for (int i = 1; i < NM; i++) {

            String a = list.get(i);
            String b = list.get(i - 1);

            if (a.equals(b)) {
                unknown++;
                sb.append(a).append('\n');
            }

        }

        System.out.println(unknown);
        System.out.print(sb);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}
