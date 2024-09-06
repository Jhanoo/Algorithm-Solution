import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int K, W, H;
	public static boolean visited[][][];
	public static boolean map[][];

	public static class Pos {
		int x, y;
		int cnt;
		int k;

		public Pos(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}

	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new boolean[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < W; j++) {
				map[i][j] = st.nextToken().equals("0") ? false : true;
			}
		}

		System.out.println(bfs());
	}

	public static int bfs() {

		int[] dx = { 1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2 }; // 0~3 : 4방향, 4~11: jump
		int[] dy = { 0, 1, 0, -1, -1, -2, -2, -1, 1, 2, 2, 1 }; // 0~3 : 4방향, 4~11: jump

		Queue<Pos> q = new ArrayDeque<>();

		q.offer(new Pos(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			int cnt = tmp.cnt;
			int k = tmp.k;

			if (x == W - 1 && y == H - 1) {
				return cnt;
			}

			for (int i = 0; i < 12; i++) {
				int moveX = x + dx[i];
				int moveY = y + dy[i];

				if (moveX < 0 || moveX >= W || moveY < 0 || moveY >= H || visited[moveY][moveX][k]
						|| map[moveY][moveX]) {
					continue;
				}

				int nextK = k;
				if (i >= 4) { // 말처럼 이동
					nextK++;
				}

				if (nextK > K || visited[moveY][moveX][nextK]) {
					continue;
				}

				q.offer(new Pos(moveX, moveY, cnt + 1, nextK));
				visited[moveY][moveX][nextK] = true;
			}

		}

		return -1;
	}

}