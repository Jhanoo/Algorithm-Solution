import java.util.PriorityQueue;

class Solution {
    public static class Pos implements Comparable<Pos> {
		int x, y;
		int cost;

		public Pos(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cost - o.cost;
		}
	}

	public static int N, H, totalCost;
	public static boolean[][] visited;

	public int solution(int[][] land, int height) {

		N = land.length;
		H = height;
		totalCost = 0;

		visited = new boolean[N][N];
		bfs(land);

		return totalCost;
	}

	public static void bfs(int[][] land) {

		// 비용이 작은 것부터 뽑기 위해 우선순위 큐 사용
		PriorityQueue<Pos> pq = new PriorityQueue<>();

		pq.offer(new Pos(0, 0, 0));

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		while (!pq.isEmpty()) {
			Pos p = pq.poll();

			// 이미 방문했으면 스킵
			if (visited[p.y][p.x])
				continue;

			visited[p.y][p.x] = true; // 방문 처리

			totalCost += p.cost; // 총 비용에 설치 비용 더하기

			for (int i = 0; i < 4; i++) {
				int rx = p.x + dx[i];
				int ry = p.y + dy[i];

				// 지형 밖으로 나가거나 이미 방문했으면 스킵
				if (rx < 0 || rx >= N || ry < 0 || ry >= N || visited[ry][rx])
					continue;

				int diff = Math.abs(land[ry][rx] - land[p.y][p.x]); // 높이차
				int cost = diff > H ? diff : 0; // 높이 차이가 H보다 크면 사다리 비용, 같거나 작으면 비용 0

				pq.offer(new Pos(rx, ry, cost));
			}
		}
	}
}