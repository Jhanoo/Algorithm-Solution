import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static class Edge {
		int from, to;
		double cost;

		public Edge(int from, int to, double cost) {
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

	public static int N;
	public static int[] roots;
	public static int[][] a;
	public static Edge[] edges;
	public static double E;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			a = new int[N][3];
			edges = new Edge[N * (N - 1) / 2];
			roots = new int[N];

			for (int i = 0; i < roots.length; i++) {
				roots[i] = i;
			}

			int idx = 0;
			String[] s = br.readLine().split(" ");
			String[] s1 = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				a[i] = new int[] { Integer.parseInt(s[i]), Integer.parseInt(s1[i]), idx++ };
			}
			idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					edges[idx++] = new Edge(a[i][2], a[j][2], getDistSquare(a[i], a[j]));
				}
			}
			E = Double.parseDouble(br.readLine());

			Arrays.sort(edges, (o1, o2) -> Double.compare(o1.cost, o2.cost));

			// kruskal
			int cnt = 0;
			double totalCost = 0;
			for (int i = 0; i < edges.length; i++) {

				if (unionFind(edges[i].from, edges[i].to)) {
					totalCost += edges[i].cost;
					cnt++;
				}

				if (cnt == N - 1)
					break;

			}
			sb.append(String.format("%.0f", totalCost * E));
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static double getDistSquare(int[] from, int[] to) {

		double dx = from[0] - to[0];
		double dy = from[1] - to[1];

		return dx * dx + dy * dy;
	}

	public static int findRoot(int v) {
		if (roots[v] == v)
			return v;

		return roots[v] = findRoot(roots[v]);
	}

	public static boolean unionFind(int a, int b) {

		int rootA = findRoot(a);
		int rootB = findRoot(b);

		if (rootA == rootB)
			return false;

		roots[rootB] = rootA;

		return true;
	}

}