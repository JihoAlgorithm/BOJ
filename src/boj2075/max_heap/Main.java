package boj2075.max_heap;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read();
        Heap maxHeap = new Heap(N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxHeap.offer(~read() + 1);
                if (maxHeap.isFull()) maxHeap.poll();
            }
        }

        System.out.print(~maxHeap.poll() + 1);

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
            length = N;
            heap = new int[length + 2];
        }
    
        boolean isFull() {
            return size > length;
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
                if (index < size && heap[index] < heap[index + 1]) index++;
                if (!swap(index)) break;
            }
    
            return element;
    
        }
    
        private boolean swap(int childIndex) {
    
            int parentIndex = childIndex >> 1;
    
            int parentValue = heap[parentIndex];
            int childValue = heap[childIndex];
    
            if (parentValue > childValue) return false;
    
            heap[parentIndex] = childValue;
            heap[childIndex] = parentValue;
    
            return true;
    
        }
    
    }
    
}