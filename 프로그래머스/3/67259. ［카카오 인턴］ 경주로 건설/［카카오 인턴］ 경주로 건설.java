import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public static int N;
	public static int[][] dp;
	public static final int INF = Integer.MAX_VALUE - 600;

	public static class Pos implements Comparable<Pos> {
		int x, y;
		int cost;
		int direction; // 0: 시작점, 1: 가로, 2: 세로

		public Pos(int x, int y, int cost, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.direction = direction;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", cost=" + cost + ", direction=" + direction + "]";
		}

	}

	public int solution(int[][] board) {

		N = board.length; // 3<=N<=25
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF); // INF값으로 초기화
		}

		int answer = dijkstra(board);
		return answer;
	}

	public static int dijkstra(int[][] board) {

		PriorityQueue<Pos> pq = new PriorityQueue<>();

		pq.offer(new Pos(0, 0, 0, 0));

		int[] dx = { 1, 0, -1, 0 }; // 가로 방향 인덱스: 0, 2
		int[] dy = { 0, -1, 0, 1 }; // 세로 방향 인덱스: 1, 3

		while (!pq.isEmpty()) {

			Pos pos = pq.poll();
			int x = pos.x;
			int y = pos.y;
			int cost = pos.cost;
			int direction = pos.direction; // 0: 시작점, 1: 가로, 2: 세로

			// 현재 cost가 dp[y][x]+600(코너를 돌 경우 600이 추가되므로) 보다 크거나 같으면 continue
			if (dp[y][x] + 600 <= cost)
				continue;

			// 작은 경우 갱신
			dp[y][x] = Math.min(dp[y][x], cost);

			// 도착 지점에 도달한 경우
			if (x == N - 1 && y == N - 1) {
				return dp[y][x];
			}

			// 4방향 탐색 (우하좌상 순서)
			for (int i = 0; i < 4; i++) {
				int rx = x + dx[i];
				int ry = y + dy[i];

				// 맵 밖으로 나가거나, 이미 방문한 경우 continue
				if (rx < 0 || rx >= N || ry < 0 || ry >= N || board[ry][rx] == 1)
					continue;

				// 이전 도로가 가로인데 세로로 건설할 때 (코너)
				if (direction == 1 && i % 2 == 1) {
					pq.offer(new Pos(rx, ry, cost + 600, direction + 1));
				}
				// 이전 도로가 세로인데 가로로 건설할 때 (코너)
				else if (direction == 2 && i % 2 == 0) {
					pq.offer(new Pos(rx, ry, cost + 600, direction - 1));
				}
				// 직선도로 건설할 때
				else {
					pq.offer(new Pos(rx, ry, cost + 100, i % 2 + 1));
				}
			}
		}

		return -1;
	}
}