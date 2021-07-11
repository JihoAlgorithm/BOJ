package boj2143.hash_map;

import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) throws Exception {

        int T = read();

        int aSize = read();
        int[] A = getArray(aSize, true);
        
        int bSize = read();
        int[] B = getArray(bSize, false);

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < bSize; i++) {
            int sum = 0;
            for (int j = i; j < bSize; j++) {
                sum += B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        long count = 0;

        for (int sum : A)
            count += map.getOrDefault(T - sum, 0);

        System.out.print(count);

    }

    private static int[] getArray(int size, boolean flag) throws Exception {

        int[] array;

        if (flag)
            array = new int[(size * (size + 1)) >> 1];
        else
            array = new int[size];

        for (int i = 0; i < size; i++)
            array[i] = read();

        if (flag) getPrefixSum(array, size);

        return array;

    }

    private static void getPrefixSum(int[] array, int size) {

        int k = size;

        for (int i = 0; i < size; i++) {

            int sum = array[i];

            for (int j = i + 1; j < size; j++)
                array[k++] = sum += array[j];

        }

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;

        if (isNegative) n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return isNegative ? ~n + 1 : n;

    }

}
