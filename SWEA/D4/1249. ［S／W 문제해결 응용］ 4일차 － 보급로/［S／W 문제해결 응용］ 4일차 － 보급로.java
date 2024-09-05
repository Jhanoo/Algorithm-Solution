import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

	public static int N;
	public static int[][] dp;
	public static Pos[][] a;
	public static boolean[][] visited;

	public static final int INF = Integer.MAX_VALUE;

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

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			dp = new int[N][N];
			a = new Pos[N][N];

			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], INF);
			}
			dp[0][0] = 0;

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					a[i][j] = new Pos(j, i, s.charAt(j) - '0');
				}
			}

			int[] dx = { 1, 0, -1, 0 };
			int[] dy = { 0, -1, 0, 1 };

			// Dijkstra
			PriorityQueue<Pos> pq = new PriorityQueue<>();

			Pos S = a[0][0];
			dp[0][0] = S.cost;
			pq.offer(S);

			while (!pq.isEmpty()) {
				Pos tmp = pq.poll();

				for (int i = 0; i < 4; i++) {
					int x = tmp.x + dx[i];
					int y = tmp.y + dy[i];

					if (0 <= x && x < N && 0 <= y && y < N) {
						if (dp[y][x] > dp[tmp.y][tmp.x] + a[y][x].cost) {
							dp[y][x] = dp[tmp.y][tmp.x] + a[y][x].cost;
							pq.offer(a[y][x]);
						}
					}
				}
			}

			sb.append(dp[N - 1][N - 1]);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}