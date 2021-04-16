package boj17471;

public class Main {

    private static final int P = 0, A = 1, TOTAL = 0, ANS = 0;

    public static void main(String[] args) throws Exception {

        // 구역 개수
        int N = read();

        // 필요한 객체 생성
        int[][] graph = new int[2][N + 1];
        int[] queue = new int[N];

        // 정답 최대값으로 초기화
        graph[A][ANS] = Integer.MAX_VALUE;

        // 인구 수 저장
        for (int i = 1; i <= N; i++) graph[P][TOTAL] += (graph[P][i] = read());

        // 인접행렬 - A인덱스는 비트마스크로 구현
        for (int i = 1; i <= N; i++)
        for (int j = read(); j > 0; j--)
            graph[A][i] |= 1 << read();

        // 조합 생성
        int half = N >> 1;
        for (int i = 1; i <= half; i++) partition(graph, N, i, 1, 0, 0, queue);

        // 값 비교
        if (graph[A][ANS] == Integer.MAX_VALUE) graph[A][ANS] = -1;
        System.out.print(graph[A][ANS]);

    }

    private static void partition(int[][] graph, int N, int num, int start, int cnt, int selected, int[] queue) {

        if (cnt == num) {

            // 인구 수
            int population = 0;

            // 생성된 조합대로 그래프가 완전히 양분되는지 확인
            if ((population = isLinked(graph, N, cnt, selected, queue)) < 0 && isLinked(graph, N, N - cnt, ~selected, queue) < 0) {

                // 인구 수 차이 구하기
                int diff = abs(~population << 1, graph[P][TOTAL]);

                // 인수 수 차이 최소값 갱신
                if (graph[A][ANS] > diff) graph[A][ANS] = diff;

            }

            // 종료
            return;

        }

        // 단순 조합 생성 부분
        for (int i = start; i <= N; i++) {
            selected |= 1 << i;
            partition(graph, N, num, i + 1, cnt + 1, selected, queue);
            selected -= 1 << i;
        }

    }

    private static int isLinked(int[][] graph, int N, int cnt, int selected, int[] queue) {

        // 큐 포인터
        int offer = 0, poll = -1, population = 0;

        // 선택된 조합 중 하나의 구역 선정하기
        int visited = 0;
        for (int i = 1; i <= N; i++)
            if ((selected & (1 << i)) != 0) {
                visited |= 1 << i;
                queue[offer] = i;
                population += graph[P][i];
                break;
            }

        // 큐가 빌 때까지 반복
        while (offer != poll) {

            // 방문한 구역 세기
            if (--cnt == 0) return ~population;

            // 큐에서 빼기
            if (++poll == N) poll = 0;
            int v = queue[poll];

            // 인접한 구역 정보
            int adjacent = graph[A][v];

            // 다른 구역 접근
            for (int u = 1; u <= N; u++) {

                // 유효성 판단
                if (valid(u, adjacent, selected, visited)) continue;

                // 방문처리
                visited |= 1 << u;

                // 큐에 넣기
                if (++offer == N) offer = 0;
                queue[offer] = u;
                population += graph[P][u];

            }

        }

        return population;

    }

    private static boolean valid(int i, int adjacent, int selected, int visited) {
        if ((adjacent & (1 << i)) == 0) return true; // 인접한지
        if ((visited  & (1 << i)) != 0) return true; // 이미 방문했는지
        if ((selected & (1 << i)) == 0) return true; // 선택된 조합인지
        return false;
    }

    private static int abs(int a, int b) { return (a -= b) < 0 ? ~a + 1 : a; }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

}