package boj2014.heap;

class MyHeap {

    public static void main(String[] args) throws Exception {

        int K = read();
        int N = read();

        Heap pq = new Heap(N);
        long[] number = new long[K];

        for (int i = 0; i < K; i++)
            pq.offer(number[i] = read());

        long top = 0;

        while (N-- > 0) {

            top = pq.poll();

            for (int i = 0; i < K; i++) {

                pq.offer(top * number[i]);

                if (top % number[i] == 0) break;

            }

        }

        System.out.print(top);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

}

class Heap {

    private int size = 0;
    private int length = 0;
    private long[] heap;

    Heap(int N) {
        length = N << 1;
        heap = new long[length];
    }

    void offer(long element) {

        if (++size == length)
            expandHeapSize();

        heap[size] = element;

        int index = size << 1;

        while ((index >>= 1) > 1)
            if (!swap(index)) break;

    }

    long poll() {

        int index = 1;
        long element = heap[index];
        heap[index] = heap[size--];

        while ((index <<= 1) <= size) {
            if (index < size && heap[index] > heap[index + 1]) index++;
            if (!swap(index)) break;
        }

        return element;

    }

    private boolean swap(int childIndex) {

        int parentIndex = childIndex >> 1;

        long parentValue = heap[parentIndex];
        long childValue = heap[childIndex];

        if (parentValue < childValue) return false;

        heap[parentIndex] = childValue;
        heap[childIndex] = parentValue;

        return true;

    }

    private void expandHeapSize() {

        long[] tempHeap = heap;
        heap = new long[length + (length >> 1)];

        for (int i = 1; i < length; i++)
            heap[i] = tempHeap[i];

        length += length >> 1;

    }

}