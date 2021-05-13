package boj1194;

class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), M = read();

        int[][] map = new int[N][M];
        long[][] visited = new long[N][M];
        
        int offer = 0;
        int poll = -1;
        int qsize = (N + M) << 5;
        int[] queue = new int[qsize];
        
        int BIT = 6;
        int MASK = 63;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ((map[i][j] = System.in.read()) == 48) {
                    queue[offer] = i | (j << BIT);
                    visited[i][j] = 1;
                    map[i][j] = 46;
                }
            }
            System.in.read(); // CR
            System.in.read();
        }

        while (offer != poll) {

            if (++poll == qsize) poll = 0;
            int temp = queue[poll];

            int x = temp & MASK; temp >>= BIT;
            int y = temp & MASK; temp >>= BIT;

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];
                int keys = temp & MASK;
                int node;

                if (nx < 0 || ny < 0 || nx == N || ny == M || (node = map[nx][ny]) == 35) continue;
                if ((visited[nx][ny] & (1L << keys)) != 0) continue;
                if (64 < node && node < 71 && (keys & 1 << (node - 65)) == 0) continue;

                int move = (temp >> BIT) + 1;
                if (node == 49) {
                    System.out.print(move);
                    return;
                }

                visited[nx][ny] |= 1L << keys;

                if (node > 96) keys |= 1 << (node - 97);

                if (++offer == qsize) offer = 0;
                queue[offer] = nx | (ny << BIT) | (keys << (BIT << 1)) | (move << (BIT << 1) << BIT);

            }

        }

        System.out.print(-1);

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;

    }

}