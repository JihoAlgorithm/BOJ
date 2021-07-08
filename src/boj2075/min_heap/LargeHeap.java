package boj2075.min_heap;

public class LargeHeap {

    public static void main(String[] args) throws Exception {

        int N = read();
        Heap maxHeap = new Heap(N);

        for (int i = 0; i < N; i++) maxHeap.offer(read());

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = read();
                if (num > maxHeap.peek()) {
                    maxHeap.offer(num);
                    maxHeap.poll();
                }
            }
        }

        System.out.print(maxHeap.poll());

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

    private static class Heap {

        private int size;
        private int length;
        private int[] heap;
    
        Heap(int N) {
            size = 0;
            length = N + 1;
            heap = new int[length + 2];
        }

        int peek() {
            return heap[1];
        }
    
        void offer(int element) {
    
            heap[++size] = element;
    
            int index = size << 1;
    
            while ((index >>= 1) > 1)
                if (!swap(index)) break;
    
        }
    
        int poll() {
    
            int index = 1;
            
            int element = heap[index];
    
            heap[index] = heap[size--];
    
            while ((index <<= 1) <= size) {
                if (index < size && heap[index] > heap[index + 1]) index++;
                if (!swap(index)) break;
            }
    
            return element;
    
        }
    
        private boolean swap(int childIndex) {
    
            int parentIndex = childIndex >> 1;
    
            int parentValue = heap[parentIndex];
            int childValue = heap[childIndex];
    
            if (parentValue < childValue) return false;
    
            heap[parentIndex] = childValue;
            heap[childIndex] = parentValue;
    
            return true;
    
        }
    
    }
    
}