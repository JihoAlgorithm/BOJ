package string.boj5430.normal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {

    private static final char REVERSE = 'R';
    private static final char DELETE = 'D';
    private static final String ERROR = "error";

    public static void main(String[] args) {

        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {

            int T = Integer.parseInt(br.readLine());

            while (T-- > 0) {

                char[] p = br.readLine().toCharArray();
                int n = Integer.parseInt(br.readLine());
                Deque<Integer> x = new ArrayDeque<>(n);

                boolean isError = false;
                boolean isFront = true;
                
                StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
                for (int i = 0; i < n; i++)
                    x.offer(Integer.parseInt(st.nextToken()));

                for (int i = 0; i < p.length; i++) {

                    if (x.size() < 1 && p[i] == DELETE) {
                        isError = true;
                        break;
                    }

                    if (p[i] == REVERSE) {
                        isFront = !isFront;
                    } else {
                        if (isFront)
                            x.poll();
                        else
                            x.pollLast();
                    }

                }

                if (isError) bw.append(ERROR);
                else {
                    int size = x.size() - 1;
                    bw.append("[");
                    if (size < 0) {
                        bw.append("]");
                    } else {
                        while (size-- > 0)
                            bw.append((isFront ? x.poll() : x.pollLast()) + ",");
                        bw.append(x.poll() + "]");
                    }
                }

                bw.newLine();

            }

        } catch (Exception e) {}

    }

}
