package string.boj9012.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

    private static final String YES = "YES\n";
    private static final String NO = "NO\n";
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        Stack<Character> stack = new Stack<>();

        while (T-- > 0) {

            char[] ps = br.readLine().toCharArray();

            for (char brace : ps) {

                if (valid(stack, brace)) {
                    stack.pop();
                    continue;
                }

                stack.push(brace);

            }

            sb.append(stack.isEmpty() ? YES : NO);
            stack.clear();

        }

        System.out.print(sb);

    }

    private static boolean valid(Stack<Character> stack, char brace) {
        if (stack.isEmpty()) return false;
        if (stack.peek() == CLOSE) return false;
        if (brace == OPEN) return false;
        return true;
    }

}
