import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static int N;
	public static int[][] a;
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int V = Integer.parseInt(s[2]);

		a = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			s = br.readLine().split(" ");

			int from = Integer.parseInt(s[0]);
			int to = Integer.parseInt(s[1]);

			a[from - 1][to - 1] = 1;
			a[to - 1][from - 1] = 1;
		}

		dfs(V - 1);
		visited = new boolean[N];
		sb.append("\n");
		bfs(V - 1);

		System.out.println(sb);
	}

	public static void dfs(int v) {

		visited[v] = true;
		sb.append(v + 1 + " ");

		for (int i = 0; i < N; i++) {
			if (a[v][i] != 0 && !visited[i]) {
				dfs(i);
			}
		}
	}

	private static void bfs(int v) {

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int tmp = q.poll();
			sb.append(tmp + 1 + " ");

			for (int i = 0; i < N; i++) {
				if (a[tmp][i] != 0 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}
}