import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static boolean[][] edges;
	public static int[] cc;

	public static final int INF = Integer.MAX_VALUE;

	public static class Node implements Comparable<Node> {
		int v;
		int dist;

		public Node(int v, int dist) {
			super();
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			edges = new boolean[N][N];
			cc = new int[N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					edges[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				}
			}

			for (int i = 0; i < N; i++) {

				int[] dp = new int[N];
				Arrays.fill(dp, INF);
				
				// Dijkstra
				PriorityQueue<Node> pq = new PriorityQueue<Node>();

				pq.offer(new Node(i, 0));
				dp[i] = 0;

				while (!pq.isEmpty()) {
					Node tmp = pq.poll();

					for (int j = 0; j < N; j++) {

						if (edges[tmp.v][j]) {

							if (dp[j] <= dp[tmp.v] + 1) {
								continue;
							}
							pq.offer(new Node(j, tmp.dist + 1));
							dp[j] = dp[tmp.v] + 1;
						}
					}
				}

				for (int dist : dp)
					cc[i] += dist;

			}

			int min = INF;
			for (int i : cc) {
				min = Math.min(i, min);
			}

			sb.append(min);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}