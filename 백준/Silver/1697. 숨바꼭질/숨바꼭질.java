import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static class Pos {
		int x;
		int dir;
		int cnt;

		public Pos(int x, int dir, int cnt) {
			super();
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}

	}

	public static StringBuilder sb = new StringBuilder();
	public static int N, K, min;
	public static int[] dp;
	public static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);

		dp = new int[100001];
		visited = new boolean[100001];
		min = Math.abs(N - K);

		move(N);
		sb.append(min + "\n");

		System.out.println(sb);
	}

	public static void move(int pos) {

		Queue<Pos> q = new ArrayDeque<>();

		q.offer(new Pos(pos, 0, 0));
		visited[pos] = true;

		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			int cnt = tmp.cnt;

			if (cnt >= min)
				continue;

			if (tmp.x == K) {
				min = cnt < min ? cnt : min;
				continue;
			} else if (tmp.x > K) {
				min = tmp.x - K + cnt < min ? tmp.x - K + cnt : min;
				continue;
			}

			if (tmp.x >= 1 && tmp.dir != 1 && tmp.x > 1 && !visited[tmp.x - 1]) {
				q.offer(new Pos(tmp.x - 1, -1, tmp.cnt + 1));
				visited[tmp.x - 1] = true;
			}

			if (tmp.x <= 100000 && tmp.dir != -1 && !visited[tmp.x + 1]) {
				q.offer(new Pos(tmp.x + 1, 1, tmp.cnt + 1));

				visited[tmp.x + 1] = true;
			}

			if (tmp.x <= 50000 && !visited[tmp.x * 2]) {
				q.offer(new Pos(tmp.x * 2, 0, tmp.cnt + 1));
				visited[tmp.x * 2] = true;
			}
		}

	}
}