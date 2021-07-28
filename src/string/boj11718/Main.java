package string.boj11718;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));) {

            String str = null;

            while ((str = br.readLine()) != null) {
                bw.write(str);
                bw.newLine();
            }

        } catch (Exception e) {
        }

    }

}
