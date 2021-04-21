package boj9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = new StringBuilder(" ").append(br.readLine()).toString().toCharArray();
        char[] B = new StringBuilder(" ").append(br.readLine()).toString().toCharArray();

        int lenA = A.length;
        int lenB = B.length;

        int[][] DP = new int[lenA][lenB];

        for (int a = 1; a < lenA; a++)
        for (int b = 1; b < lenB; b++)
            if (A[a] == B[b]) DP[a][b] = DP[a - 1][b - 1] + 1;
            else DP[a][b] = max(DP[a - 1][b], DP[a][b - 1]);

        System.out.print(DP[lenA - 1][lenB - 1]);

    }

    private static int max(int a, int b) { return a > b ? a : b; }

}