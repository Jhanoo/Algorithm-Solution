import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static Pos[] a;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());

			a = new Pos[n + 2];

			StringTokenizer st;
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				a[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			if (getDist(a[0], a[n + 1]) <= 1000) {
				System.out.println("happy");
				continue;
			}

			boolean[] visited = new boolean[n + 2];
			Queue<Pos> q = new ArrayDeque<>();
			q.offer(a[0]);
			visited[0] = true;

			while (!q.isEmpty()) {
				Pos p = q.poll();

				for (int i = 1; i < n + 2; i++) {

					if (visited[i] || getDist(p, a[i]) > 1000)
						continue;

					q.offer(a[i]);
					visited[i] = true;
				}
			}

			if (visited[n + 1] == true) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}

	public static int getDist(Pos a, Pos b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

}