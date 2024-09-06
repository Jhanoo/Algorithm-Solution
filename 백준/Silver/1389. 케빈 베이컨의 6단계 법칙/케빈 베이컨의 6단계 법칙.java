import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static boolean[][] friend;
	public static Set<Integer>[] adjList;

	public static class Node {
		int v;
		int cnt;

		public Node(int v, int cnt) {
			super();
			this.v = v;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		friend = new boolean[N + 1][N + 1];
		adjList = new HashSet[N + 1];

		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new HashSet<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friend[a][b] = true;
			friend[b][a] = true;
			adjList[a].add(b);
			adjList[b].add(a);
		}

		System.out.println(bfs());

	}

	public static int bfs() {

		Queue<Node> q = new ArrayDeque<>();
		Node[] baconNums = new Node[N];

		for (int i = 1; i <= N; i++) {

			boolean[] visited = new boolean[N + 1];
			int baconNum = 0;

			q.offer(new Node(i, 0));
			visited[i] = true;

			while (!q.isEmpty()) {

				Node tmp = q.poll();
				baconNum += tmp.cnt;

				for (Integer adj : adjList[tmp.v]) {

					if (!visited[adj]) {
						q.offer(new Node(adj, tmp.cnt + 1));
						visited[adj] = true;
					}
				}
			}

			baconNums[i - 1] = new Node(i, baconNum);
		}

		Arrays.sort(baconNums, (o1, o2) -> {
			return o1.cnt == o2.cnt ? o1.v - o2.v : o1.cnt - o2.cnt;
		});

		return baconNums[0].v;
	}

}