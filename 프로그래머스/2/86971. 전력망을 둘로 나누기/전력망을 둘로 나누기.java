import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
   
	public static int N;
	public static boolean[][] edges;

	public int solution(int n, int[][] wires) {

		N = n;
		edges = new boolean[n + 1][n + 1];
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n - 1; i++) {
			int from = wires[i][0];
			int to = wires[i][1];
			edges[from][to] = true;
			edges[to][from] = true;
		}

		for (int i = 0; i < n - 1; i++) {
			int a = wires[i][0];
			int b = wires[i][1];

			edges[a][b] = false;
			edges[b][a] = false;

			int cntA = bfs(a);
			int cntB = bfs(b);

			edges[a][b] = true;
			edges[b][a] = true;

			min = Math.min(min, Math.abs(cntA - cntB));
		}

		return min;
	}

	public static int bfs(int a) {

		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N + 1];

		q.offer(a);
		visited[a] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int v = q.poll();
			cnt++;

			for (int j = 1; j <= N; j++) {

				if (edges[v][j] && !visited[j]) {
					q.offer(j);
					visited[j] = true;
				}
			}
		}

		return cnt;
	}
}