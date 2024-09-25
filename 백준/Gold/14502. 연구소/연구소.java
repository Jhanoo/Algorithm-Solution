import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class P {
		int x, y;

		public P(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static int N, M, max, num;
	public static int[][] map;
	public static List<P> virus;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		virus = new ArrayList<P>();

		// M*N이 최대일 때 3개의 벽을 고르는 경우의 수: (8*8)C3 = (64*63*62)/(3*2) = 41,664
		// 바이러스 개수는 2~10
		// BFS: O(N+E) = 10+112 = 122
		// 최악의 경우: 41,664 * 122 = 5,083,008

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new P(j, i));
				} else if (map[i][j] == 0)
					num++;
			}
		}
		num -= 3;
		comb(0, 0);
		System.out.println(max);
	}

	public static void comb(int start, int cnt) {

		if (cnt == 3) {
			bfs();
			return;
		}

		for (int i = start; i < M * N; i++) {

			if (map[i / M][i % M] == 0) {
				map[i / M][i % M] = 1;
				comb(i + 1, cnt + 1);
				map[i / M][i % M] = 0;
			}
		}

	}

	public static void bfs() {
		Queue<P> q = new ArrayDeque<P>();

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		int[][] copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, copy[i], 0, M);
		}

		int tmpMax = num;

		for (P v : virus) {
			q.offer(v);

			while (!q.isEmpty()) {
				P p = q.poll();

				for (int i = 0; i < 4; i++) {
					int rx = p.x + dx[i];
					int ry = p.y + dy[i];

					if (rx < 0 || rx >= M || ry < 0 || ry >= N || copy[ry][rx] == 2 || copy[ry][rx] == 1)
						continue;

					q.offer(new P(rx, ry));
					copy[ry][rx] = 2;
					tmpMax--;
				}
			}
		}

		max = Math.max(max, tmpMax);
	}

}