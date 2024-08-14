import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	public static int V;
	public static Edge[] edges;
	public static int[] parents;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 3124.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] s = br.readLine().split(" ");
			V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);

			edges = new Edge[E];

			for (int i = 0; i < E; i++) {
				s = br.readLine().split(" ");
				int a = Integer.parseInt(s[0]);
				int b = Integer.parseInt(s[1]);
				int weight = Integer.parseInt(s[2]);

				edges[i] = new Edge(a, b, weight);
			}

			Arrays.sort(edges);

			makeSet();

			long result = 0; // MST 비용
			int count = 0; // 연결 간선 수
			for (Edge edge : edges) {

				if (union(edge.from, edge.to)) { // 싸이클이 발생하지 않았으면 (같은 집합에 없다면)
					result += edge.weight;

					if (++count == V - 1) {
						break;
					}
				}
			}

			sb.append(result + "\n");
		}

		System.out.println(sb);
	}

	public static void makeSet() {
		parents = new int[V + 1];

		for (int i = 1; i < V + 1; i++) {
			parents[i] = i;
		}
	}

	public static int findSet(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = findSet(parents[a]);
	}

	public static boolean union(int a, int b) {

		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;

		return true;
	}
}