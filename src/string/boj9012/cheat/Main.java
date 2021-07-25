package string.boj9012.cheat;

class Main {

    private static final String YES = "YES\n";
    private static final String NO = "NO\n";

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        int c, T = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            T = (T << 3) + (T << 1) + (c & 15);

        if (c == 13) System.in.read();

        while (T-- > 0) sb.append(isVPS() ? YES : NO);

        System.out.print(sb);

    }

    private static boolean isVPS() throws Exception {

        int c, braces = 0;
        boolean flag = false;

        while ((c = System.in.read()) > 32) {
            if (c == 40) braces++;
            else braces--;
            if  (braces < 0) {
                flag = true;
                break;
            }
        }

        if (flag) {
            while (System.in.read() > 32); System.in.read();
            return false;
        }

        System.in.read();
        return braces == 0;

    }
}
