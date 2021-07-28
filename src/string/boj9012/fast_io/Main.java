package string.boj9012.fast_io;

class Main {

    private static final String YES = "YES\n";
    private static final String NO = "NO\n";

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int c, T = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            T = (T << 3) + (T << 1) + (c & 15);

        if (c == 13) System.in.read();

        boolean[] stack = new boolean[50];

        while (T-- > 0) {

            int top = 0;

            while ((c = System.in.read()) > 32)
                if (valid(stack, top, c)) top--;
                else stack[top++] = c == '(';

            if (c == 13) System.in.read();

            sb.append(top == 0 ? YES : NO);

        }

        System.out.print(sb);

    }

    private static boolean valid(boolean[] stack, int top, int brace) {
        if (top == 0 || !stack[top - 1] || brace == '(') return false;
        return true;
    }

}
