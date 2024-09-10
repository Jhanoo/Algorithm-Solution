import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static int[][] a;

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		a = new int[N][N];

		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");

			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(s[j]);
				maxHeight = Math.max(a[i][j], maxHeight);
			}
		}

		int max = 1;

		// 1<=rain<=maxHeight 로 완탐
		for (int rain = 1; rain < maxHeight; rain++) {

			boolean[][] visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (a[i][j] <= rain) {
						visited[i][j] = true; // 잠긴 지역은 true
					}
				}
			}

			Queue<Pos> q = new ArrayDeque<>();

			int[] dx = { 1, 0, -1, 0 };
			int[] dy = { 0, -1, 0, 1 };

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						q.offer(new Pos(j, i));
						visited[i][j] = true;

						while (!q.isEmpty()) {
							Pos pos = q.poll();

							for (int k = 0; k < 4; k++) {
								int rx = pos.x + dx[k];
								int ry = pos.y + dy[k];

								if (rx < 0 || rx >= N || ry < 0 || ry >= N || visited[ry][rx]) {
									continue;
								}

								q.offer(new Pos(rx, ry));
								visited[ry][rx] = true;
							}
						}
						cnt++;
					}
				}

				max = Math.max(max, cnt);
			}
		}
		System.out.println(max);
	}

}