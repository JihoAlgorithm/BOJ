package boj2075.intention;

class Main {

    private static final int BIT = 11;
    private static final int MASK = 2047;

    public static void main(String[] args) throws Exception {

        int r, c, N = read();
        int[][] matrix = new int[N][N];

        for (r = 0; r < N; r++)
            for (c = 0; c < N; c++)
                matrix[r][c] = read();

        Heap heap = new Heap(N);

        r = N - 1;
        for (c = 0; c < N; c++)
            heap.offer(new Coord(r, c, matrix[r][c]));

        int NthNumber = 0;

        for (int i = 0; i < N; i++) {
            
            Coord top = heap.poll();

            r = top.coord >> BIT;
            c = top.coord & MASK;
            NthNumber = top.value;

            if (r > 0) heap.offer(new Coord(r - 1, c, matrix[r - 1][c]));

        }

        System.out.print(NthNumber);

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

    private static class Coord {

        int coord, value;

        Coord(int r, int c, int v) {
            coord = r << BIT | c;
            value = v;
        }

        boolean compare(Coord c) {
            return this.value < c.value;
        }

    }

    private static class Heap {

        private int size;
        private Coord[] heap;

        Heap(int N) {
            size = 0;
            heap = new Coord[N + 2];
        }
    
        void offer(Coord element) {

            heap[++size] = element;

            int index = size << 1;

            while ((index >>= 1) > 1)
                if (!swap(index)) break;

        }

        Coord poll() {

            int index = 1;
            
            Coord element = heap[index];
    
            heap[index] = heap[size--];
    
            while ((index <<= 1) <= size) {
                if (index < size && heap[index].compare(heap[index + 1])) index++;
                if (!swap(index)) break;
            }
    
            return element;
    
        }
    
        private boolean swap(int childIndex) {
    
            int parentIndex = childIndex >> 1;
    
            Coord parentValue = heap[parentIndex];
            Coord childValue = heap[childIndex];
    
            if (childValue.compare(parentValue)) return false;
    
            heap[parentIndex] = childValue;
            heap[childIndex] = parentValue;
    
            return true;
    
        }

    }

}
