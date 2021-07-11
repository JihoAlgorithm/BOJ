package boj2014.heap;

class MyHeap {

    public static void main(String[] args) throws Exception {

        int K = read();
        int N = read();
        long top = 0;

        Heap pq = new Heap();
        int[] number = new int[K];

        for (int i = 0; i < K; i++)
            pq.offer(number[i] = read());

        while (N-->0) {

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

        return n;

    }

}

class Heap {

    private static final int MAX = 200000;

    int size = 0;
    private long[] heap = new long[MAX];

    void offer(long element) {

        if (size + 1 == MAX) return;

        heap[++size] = element;

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

}