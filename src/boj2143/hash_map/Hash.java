package boj2143.hash_map;

class Hash {

    private static final int H_MAX  = ~(-1 << 20);
    private static final int STEP   = 31;
    private static final int NO_KEY = -1;

    private static int[] hash = new int[H_MAX + 1];
    private static int[] data = new int[H_MAX + 1];

    public static void main(String[] args) throws Exception {

        int aSize, bSize, sum, key, T = read();
        int[] A = getArray(aSize = read());
        int[] B = getArray(bSize = read());

        for (int i = sum = 0; i < aSize; i++, sum = 0)
            for (int j = i; j < aSize; j++)
                setHash(sum += A[j]);

        long count = sum = 0;

        for (int i = 0; i < bSize; i++, sum = 0) {
            for (int j = i; j < bSize; j++) {
                key = getKey(T - (sum += B[j]));
                if (key != NO_KEY) count += hash[key];
            }
        }

        System.out.print(count);

    }

    private static int getKey(int value) {

        int key = value & H_MAX;

        while (hash[key] > 0)
            if (data[key] == value) return key;
            else key = (key + STEP) & H_MAX;

        return NO_KEY;

    }

    private static void setHash(int value) {

        int key = value & H_MAX;

        while (hash[key] > 0)
            if (data[key] == value) break;
            else key = (key + STEP) & H_MAX;

        hash[key]++;
        data[key] = value;

    }

    private static int[] getArray(int size) throws Exception {

        int[] array = new int[size];

        for (int i = 0; i < size; i++) array[i] = read();

        return array;

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