import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Node implements Comparable<Node> {
		int v;
		int cost;
		Node prev;

		public Node(int v, int cost, Node prev) {
			super();
			this.v = v;
			this.cost = cost;
			this.prev = prev;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] cost = new int[N + 1][N + 1];
		int[] dp = new int[N + 1];

		Arrays.fill(dp, 987654321);
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(cost[i], 987654321);
		}

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			cost[from][to] = Math.min(cost[from][to], c);
		}

		st = new StringTokenizer(br.readLine(), " ");

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0, null));
		dp[start] = 0;

		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			Node prev = tmp.prev;

			if (prev != null) {
				if (dp[tmp.v] <= tmp.cost)
					continue;
				
				dp[tmp.v] = tmp.cost;
			}

			if (tmp.v == end) {
				sb.append(dp[tmp.v] + "\n");
				String s = "" + tmp.v;
				int cnt = 1;

				while (prev != null) {
					s = prev.v + " " + s;
					cnt++;
					prev = prev.prev;
				}
				sb.append(cnt + "\n");
				sb.append(s);
				System.out.println(sb);
				return;
			}

			for (int i = 1; i <= N; i++) {
				if (cost[tmp.v][i] < 987654321) {
					pq.offer(new Node(i, tmp.cost + cost[tmp.v][i], tmp));
				}
			}
		}
	}

}