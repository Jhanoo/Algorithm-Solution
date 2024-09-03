import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public static class Edge {
		int from, to;
		int cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}

	}

	public static Edge[] edges;
	public static Map<Integer, Integer> roots;

	public int solution(int n, int[][] costs) {
		int k = costs.length;
		edges = new Edge[k];
		roots = new HashMap<>();

		for (int i = 0; i < n; i++)
			roots.put(i, i);

		for (int i = 0; i < k; i++)
			edges[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);

		Arrays.sort(edges, (o1, o2) -> o1.cost - o2.cost);

		// kruskal
		int cnt = 0;
		int totalCost = 0;
		for (int i = 0; i < costs.length; i++) {

			if (unionFind(edges[i].from, edges[i].to)) {
				totalCost += edges[i].cost;
				cnt++;
			}

			if (cnt == n - 1)
				break;
		}

		return totalCost;
	}

	public static int findRoot(int v) {

		if (v == roots.get(v))
			return v;

		roots.put(v, findRoot(roots.get(v)));
		return roots.get(v);
	}

	public static boolean unionFind(int a, int b) {
		int rootA = findRoot(a);
		int rootB = findRoot(b);

		if (rootA == rootB)
			return false;

		roots.put(rootB, rootA);

		return true;
	}
}