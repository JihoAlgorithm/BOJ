package boj2580;

import java.util.LinkedList;

class Main {

    private static final int BIT = (1 << 2);
    private static final int MASK = ~(-1 << BIT);
    private static final int ROW = 0, COL = 1, BOX = 2;

    public static void main(String[] args) throws Exception {

        int[][] sudoku = new int[9][9];
        LinkedList<Integer> zeroQueue = new LinkedList<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sudoku[r][c] = System.in.read() - 48;
                if (sudoku[r][c] == 0)
                    zeroQueue.add((r << BIT) | c);
                System.in.read(); // SPACE
            }
            System.in.read();
        }

        while (!zeroQueue.isEmpty()) {

            int x = zeroQueue.poll();
            int y = x & MASK;
            x >>= BIT;

            switch (check(sudoku, x, y)) {
                case ROW: fillRow(sudoku, x, y); break;
                case COL: fillCol(sudoku, x, y); break;
                case BOX: fillBox(sudoku, x, y); break;
                default: zeroQueue.offer((x << BIT) | y); break;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sb.append(sudoku[r][c]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());

    }

    private static void fillRow(int[][] sudoku, int x, int y) {

        int numbers = 0;

        for (int i = 0; i < 9; i++)
            numbers |= 1 << sudoku[x][i];

        for (int n = 1; n < 10; n++)
            if ((numbers & (1 << n)) == 0) {
                sudoku[x][y] = n;
                return;
            }

    }

    private static void fillCol(int[][] sudoku, int x, int y) {

        int numbers = 0;

        for (int i = 0; i < 9; i++)
            numbers |= 1 << sudoku[i][y];

        for (int n = 1; n < 10; n++)
            if ((numbers & (1 << n)) == 0) {
                sudoku[x][y] = n;
                return;
            }

    }

    private static void fillBox(int[][] sudoku, int x, int y) {

        int row = (x / 3) * 3 + 3;
        int col = (y / 3) * 3 + 3;

        System.out.println(x + " " + y);
        System.out.println(row + " " + col);

        int numbers = 0;

        for (int r = row - 3; r < row; r++)
        for (int c = col - 3; c < col; c++)
            numbers |= 1 << sudoku[r][c];
        
        for (int n = 1; n < 10; n++) {
            if ((numbers & (1 << n)) == 0) {
                sudoku[x][y] = n;
                return;
            }
        }

    }

    private static int check(int[][] sudoku, int x, int y) {

        int row = 0, col = 0;

        for (int i = 0; i < 9; i++) {
            if (sudoku[x][i] == 0) row++;
            if (sudoku[i][y] == 0) col++;
        }

        if (row == 1) return ROW;
        if (col == 1) return COL;

        row = (x / 3) * 3 + 3;
        col = (y / 3) * 3 + 3;

        int zero = 0;

        for (int r = row - 3; r < row; r++) {
            for (int c = col - 3; c < col; c++) {
                if(sudoku[r][c] == 0) zero++;
            }
        }

        if (zero == 1) return BOX;

        return -1; // 값을 확정으로 채울 수 없음

    }

}
