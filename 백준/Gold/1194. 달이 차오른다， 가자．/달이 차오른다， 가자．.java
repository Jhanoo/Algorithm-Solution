import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Pos {
		int x, y;
		int keys;
		int cnt;

		public Pos(int x, int y, int keys, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.keys = keys;
			this.cnt = cnt;
		}

	}

	public static int N, M;
	public static char[][] map;
	public static boolean[][][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][64];

		Pos start = null;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					start = new Pos(j, i, 0, 0);
				}
			}
		}

		System.out.println(bfs(start));
	}

	public static int bfs(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();

		q.offer(start);
		visited[start.y][start.x][0] = true;

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			int keys = tmp.keys;
			int cnt = tmp.cnt;

			if (map[y][x] == '1') {
				return cnt;
			}

			for (int i = 0; i < 4; i++) {
				int moveX = x + dx[i];
				int moveY = y + dy[i];

				if (0 <= moveX && moveX < M && 0 <= moveY && moveY < N && !visited[moveY][moveX][keys]
						&& map[moveY][moveX] != '#') {

					if (map[moveY][moveX] == '.' || map[moveY][moveX] == '0' || map[moveY][moveX] == '1') {
						q.offer(new Pos(moveX, moveY, keys, cnt + 1));
						visited[moveY][moveX][keys] = true;
					}
					// 이동하려는 위치가 문인 경우
					else if ('A' <= map[moveY][moveX] && map[moveY][moveX] <= 'F') {

						int door = 1 << (map[moveY][moveX] - 'A');
						// 열쇠가 있으면
						if ((door & keys) > 0) {
							q.offer(new Pos(moveX, moveY, keys, cnt + 1));
							visited[moveY][moveX][keys] = true;
						}

					}
					// 이동하려는 위치가 열쇠인 경우
					else if ('a' <= map[moveY][moveX] && map[moveY][moveX] <= 'f') {

						int key = 1 << (map[moveY][moveX] - 'a');
						q.offer(new Pos(moveX, moveY, keys | key, cnt + 1));
						visited[moveY][moveX][keys | key] = true;
						visited[moveY][moveX][keys] = true;
					}

				}
			}
		}

		return -1;
	}

}