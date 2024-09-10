import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Pipe {
		int x, y;
		int direction; // 0: 가로, 1: 세로, 2: 대각

		public Pipe(int x, int y, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

	}

	public static int N;
	public static boolean[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().equals("1") ? true : false;
			}
		}

		int[] dx = { 1, 0, 1 };
		int[] dy = { 0, 1, 1 };

		Queue<Pipe> q = new ArrayDeque<>();

		q.offer(new Pipe(1, 0, 0));
		int cnt = 0;
		while (!q.isEmpty()) {
			Pipe p = q.poll();

			if (p.x == N - 1 && p.y == N - 1) {
				cnt++;
				continue;
			}

			for (int i = 0; i < 3; i++) {

				// 가로방향일 때 세로 || 세로방향일 때 가로면 스킵
				if ((p.direction == 0 && i == 1) || (p.direction == 1 && i == 0))
					continue;

				int rx = p.x + dx[i];
				int ry = p.y + dy[i];

				if (rx < 0 || rx >= N || ry < 0 || ry >= N || map[ry][rx])
					continue;

				if ((i == 2) && (map[ry - 1][rx] || map[ry][rx - 1]))
					continue;

				q.offer(new Pipe(rx, ry, i));
			}
		}

		System.out.println(cnt);
	}

}