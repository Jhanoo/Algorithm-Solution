import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public static int[] dp;
	public static Node[] nodes;

	public static final int INF = Integer.MAX_VALUE;

	public static class Node {
		int v;
		List<Edge> link;

		public Node(int v) {
			super();
			this.v = v;
			this.link = new ArrayList<>();
		}
	}

	public static class Edge implements Comparable<Edge> {
		int from, to;
		int cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public int solution(int N, int[][] road, int K) {

		nodes = new Node[N + 1]; // 마을 번호 1부터 시작, 0은 안씀
		dp = new int[N + 1]; // 1번 마을에서 n번 마을까지 가는데 걸리는 시간

		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i); // 각 마을 init
			dp[i] = INF; // 최대값으로 init
		}

		for (int i = 0; i < road.length; i++) {
			int a = road[i][0]; // a마을
			int b = road[i][1]; // b마을
			int c = road[i][2]; // c시간

			nodes[a].link.add(new Edge(a, b, c)); // a마을에 b마을을 c시간의 edge로 연결
			nodes[b].link.add(new Edge(b, a, c)); // b마을에 a마을을 c시간의 edge로 연결
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		// PQ에 초기값으로 1번 마을의 간선들 enqueue
		for (int i = 0; i < nodes[1].link.size(); i++) {
			pq.offer(nodes[1].link.get(i));
		}
		dp[1] = 0; // 1번 마을이므로 0시간으로 초기화

		while (!pq.isEmpty()) {
			// Priority Queue 이므로 cost가 가장 작은 Edge가 dequeue됨
			Edge tmp = pq.poll();

			// tmp 간선을 따라갔을 때 cost가 K시간 초과면 스킵
			if (dp[tmp.from] + tmp.cost > K)
				continue;
			// tmp 간선을 따라갔을 때 값이 이전 cost 보다 같거나 크면 스킵 ( != 최단 시간)
			if (dp[tmp.to] <= dp[tmp.from] + tmp.cost)
				continue;

			// 작으면 갱신 ( == 최단시간)
			dp[tmp.to] = dp[tmp.from] + tmp.cost;

			// 갱신된 마을에 연결된 간선들 enqueue
			for (int i = 0; i < nodes[tmp.to].link.size(); i++) {
				pq.offer(nodes[tmp.to].link.get(i));
			}
		}

		int cnt = 0; // 배달 가능한 마을 개수
		for (int i = 1; i < N + 1; i++) {
			if (dp[i] < INF) {
				cnt++; // 최단시간이 초기값 INF에서 갱신 됐으면 +1
			}
		}

		return cnt;
	}
}