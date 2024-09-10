import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Pos {
		int x, y, z;
		int day;

		public Pos(int x, int y, int z, int day) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][][] box = new int[H][N][M];

		Queue<Pos> q = new ArrayDeque<Pos>();

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					box[h][i][j] = Integer.parseInt(st.nextToken());

					if (box[h][i][j] == 1) {
						q.offer(new Pos(j, i, h, 1));
					}
				}
			}
		}
		int[] dx = { 1, 0, -1, 0, 0, 0 };
		int[] dy = { 0, -1, 0, 1, 0, 0 };
		int[] dz = { 0, 0, 0, 0, 1, -1 };

		// BFS
		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int i = 0; i < 6; i++) {
				int rx = p.x + dx[i];
				int ry = p.y + dy[i];
				int rz = p.z + dz[i];

				if (rx < 0 || rx >= M || ry < 0 || ry >= N || rz < 0 || rz >= H || box[rz][ry][rx] != 0)
					continue;

				q.offer(new Pos(rx, ry, rz, p.day + 1));
				box[rz][ry][rx] = p.day + 1;
			}
		}

		int day = 0;
		for (int i = 0; i < H * M * N; i++) {

			if (box[i / (M * N)][(i / M) % N][i % M] == 0) {
				day = -1;
				System.out.println(day);
				return;
			}

			day = Math.max(day, box[i / (M * N)][(i / M) % N][i % M]);
		}

		System.out.println(--day);
	}

}