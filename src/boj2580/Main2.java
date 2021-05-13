package boj2580;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Main2 {

    private static final int SIZE = 9, BIT = 4, MASK = 15;

    private static char[][] sudoku;
    private static int[] row, col, box;
    private static int zeroNum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new char[SIZE][];
        row = new int[SIZE];
        col = new int[SIZE];
        box = new int[SIZE];

        ArrayList<Integer> zeroList = new ArrayList<>();

        for (int x = 0; x < SIZE; x++) {

            sudoku[x] = br.readLine().toCharArray();

            for (int y = 0; y < SIZE; y++) {

                if (sudoku[x][y << 1] != 48) {
                    int n = sudoku[x][y << 1] - 48;
                    row[x] |= 1 << n;
                    col[y] |= 1 << n;
                    box[getBox(x, y)] |= 1 << n;
                } else {
                    zeroNum++;
                    zeroList.add((x << BIT) | y);
                }

            }

        }

        dfs(zeroList, 0);

    }

    private static void dfs(ArrayList<Integer> zeroList, int zero) throws IOException {

        if (zero == zeroNum) print();

        int x = zeroList.get(zero);
        int y = x & MASK;
        x >>= BIT;

        if (sudoku[x][y << 1] == '0') for (int n = 1; n <= SIZE; n++) {

            int z = getBox(x, y);
            if (valid(n, x, y, z)) continue;

            row[x] |= 1 << n;
            col[y] |= 1 << n;
            box[z] |= 1 << n;
            sudoku[x][y << 1] = (char) (n + 48);

            dfs(zeroList, zero + 1);

            row[x] -= 1 << n;
            col[y] -= 1 << n;
            box[z] -= 1 << n;
            sudoku[x][y << 1] = 48;

        }

    }

    private static boolean valid(int n, int x, int y, int z) {
        if ((row[x] & (1 << n)) != 0) return true;
        if ((col[y] & (1 << n)) != 0) return true;
        if ((box[z] & (1 << n)) != 0) return true;
        return false;
    }

    private static int getBox(int x, int y) {
        return (x / 3) * 3 + (y / 3);
    }

    private static void print() throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int r = 0; r < SIZE; r++) {
            bw.write(sudoku[r]);
            bw.newLine();
        }

        bw.close();
        System.exit(0);

    }

}