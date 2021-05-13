package boj1655;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ1655_ArrayHeap {

    private static final boolean ASC = true, DESC = false;
    private static final int TOP = 1;

    public static void main(String[] args) throws Exception {

        int N = read();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int lSize = 0, rSize = 0;
        int[] leftHeap = new int[(N >> 1) + 2];
        int[] rightHeap = new int[(N >> 1) + 2];

        while (N-- > 0) {

            if (lSize > rSize) {
                rightHeap[++rSize] = read();
                heapify(rightHeap, rSize, ASC);
            } else {
                leftHeap[++lSize] = read();
                heapify(leftHeap, lSize, DESC);
            }

            if (rSize > 0 && leftHeap[TOP] > rightHeap[TOP]) {

                int l = leftHeap[TOP];
                int r = rightHeap[TOP];

                remove(leftHeap, lSize, DESC);
                remove(rightHeap, rSize, ASC);

                leftHeap[lSize] = r;
                rightHeap[rSize] = l;

                heapify(leftHeap, lSize, DESC);
                heapify(rightHeap, rSize, ASC);

            }

            bw.write(Integer.toString(leftHeap[TOP]));
            bw.newLine();

        }

        bw.close();

    }

    private static void heapify(int[] heap, int size, boolean isASC) {

        while (size > 1) {

            int p = heap[size >> 1];
            int c = heap[size];

            if (check(isASC, p, c)) {
                heap[size] = p;
                heap[size >>= 1] = c;
            } else
                break;

        }
    }

    private static void remove(int[] heap, int size, boolean isASC) {

        heap[TOP] = heap[size--];

        int i = 1;

        while ((i <<= 1) <= size) {

            if (i < size + 1 && check(isASC, heap[i], heap[i + 1]))
                i += 1;

            int p = heap[i >> 1];
            int c = heap[i];

            if (check(isASC, p, c)) {
                heap[i >> 1] = c;
                heap[i] = p;
            } else
                break;

        }

    }

    private static boolean check(boolean flag, int a, int b) {
        if (flag && a > b)
            return true;
        if (!flag && a < b)
            return true;
        return false;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative)
            n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return isNegative ? ~n + 1 : n;
    }

}
