import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static class Edge {
		int from, to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]\n";
		}

		@Override
		public int hashCode() {
			return Objects.hash(weight);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			return ((from == other.from && to == other.to) || (from == other.to && to == other.from))
					&& weight == other.weight;
		}
	}

	public static int M, N;
	public static int map[][];
	public static int visited[][];
	public static Set<Edge> edges;
	public static HashMap<Integer, Integer> parents;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new int[M][N];
		edges = new HashSet<Edge>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		int idx = 1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				connect(i, j, idx++);
			}
		}

		makeEdge();

		List<Edge> edgeList = new ArrayList<Edge>(edges);
		edgeList.sort((o1, o2) -> o1.weight - o2.weight);

		parents = new HashMap<Integer, Integer>();
		for (Edge e : edges) {
			parents.putIfAbsent(e.from, e.from);
			parents.putIfAbsent(e.to, e.to);
		}

		int w = kruskal(edgeList);

		System.out.println(w);
	}

	public static void connect(int r, int c, int idx) {

		if (map[r][c] == 0 || visited[r][c] != 0)
			return;

		visited[r][c] = idx;

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		for (int i = 0; i < 4; i++) {

			if (0 <= r + dy[i] && r + dy[i] < M && 0 <= c + dx[i] && c + dx[i] < N) {
				connect(r + dy[i], c + dx[i], idx);
			}
		}
	}

	public static void makeEdge() {

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {

				if (visited[i][j] > 0) {
					for (int k = 0; k < 4; k++) {

						int r = i + dy[k];
						int c = j + dx[k];

						while (0 <= r && r < M && 0 <= c && c < N) {

							if (visited[r][c] == visited[i][j]) {
								break;
							} else if (visited[r][c] == 0) {
								r += dy[k];
								c += dx[k];
							} else {
								int row = Math.abs(r - i) - 1;
								int col = Math.abs(c - j) - 1;

								int w = row > col ? row : col;

								edges.add(new Edge(visited[i][j], visited[r][c], w));
								break;
							}
						}
					}

				}

			}
		}
	}

	public static int findSet(int v) {

		if (parents.get(v) == v)
			return v;

		int p = findSet(parents.get(v));
		parents.put(v, p);

		return p;
	}

	public static boolean union(int a, int b) {

		int aParent = findSet(a);
		int bParent = findSet(b);

		if (aParent == bParent)
			return false;

		parents.put(aParent, bParent);

		return true;
	}

	public static int kruskal(List<Edge> edgeList) {

		int w = 0;
		int cnt = 0;
		for (Edge e : edgeList) {

			if (e.weight >= 2 && union(e.from, e.to)) {
				w += e.weight;

				if (++cnt == parents.size() - 1)
					break;
			}
		}

		Set<Integer> tmpSet = parents.keySet();
		Iterator<Integer> it = tmpSet.iterator();

		int tmp = -1;
		if (it.hasNext())
			tmp = findSet(it.next());
		while (it.hasNext()) {
			if (tmp != findSet(it.next())) {
				w = -1;
				break;
			}

		}

		if (w == 0)
			w = -1;

		return w;

	}
}