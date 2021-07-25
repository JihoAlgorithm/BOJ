package string.boj9012.array;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    private static final String YES = "YES\n";
    private static final String NO = "NO\n";

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        char[] stack = new char[50];

        while (T-- > 0) {

            char[] ps = br.readLine().toCharArray();
            int top = 0;

            for (char brace : ps) {

                if (valid(stack, top, brace)) {
                    --top;
                    continue;
                }

                stack[top++] = brace;

            }

            sb.append(top == 0 ? YES : NO);

        }

        System.out.print(sb);

    }

    private static boolean valid(char[] stack, int top, char brace) {
        if (top == 0) return false;
        if (stack[top - 1] == ')') return false;
        if (brace == '(') return false;
        return true;
    }

}
