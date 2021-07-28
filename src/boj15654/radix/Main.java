package boj15654.radix;

public class Main {

    private static StringBuilder sb;
    private static int N, M, visited;
    private static int[] selected, numbers;

    public static void main(String[] args) throws Exception {

        sb = new StringBuilder();
        N = read();
        M = read();

        numbers = new int[N];
        selected = new int[M];

        for (int i = 0; i < N; i++) numbers[i] = read();

        radixSort(numbers);

        dfs(0);

        System.out.print(sb);

    }

    private static void dfs(int count) throws Exception {

        if (count == M) {
            for (int number : selected)
                sb.append(number).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            visited |= 1 << i;
            selected[count] = numbers[i];
            dfs(count + 1);
            visited ^= 1 << i;
        }

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        if (c == 13) System.in.read();

        return n;

    }

    private static void radixSort(int[] array) {

        Queue[] bucket = new Queue[10];

        int maxSize = 5;
        int pow = 1;
        int index = 0;

        for (int i = 0; i < 10; i++) bucket[i] = new Queue();
        
        for (int i = 1; i <= maxSize; i++)  {

            for (int j = 0; j < array.length; j++) {
                int number = array[j];
                bucket[number / pow % 10].offer(number);
            }

            for (int k = 0; k < 10; k++) {

                int bucketSize = bucket[k].size();

                for (int n = 0; n < bucketSize; n++)
                    array[index++] = bucket[k].poll();

            }

            index = 0;
            pow = (pow << 3) + (pow << 1);

        }

    }

}

class Queue {

    private static final int Q = 15;

    int[] queue = new int[Q + 1];
    int offer = -1;
    int poll = -1;

    public int size() {
        return offer - poll;
    }

    public void offer(int num) {
        queue[++offer & Q] = num;
    }

    public int poll() {

        return queue[++poll & Q];

    }

}