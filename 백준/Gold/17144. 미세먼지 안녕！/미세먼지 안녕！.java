import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int R, C, T;
	public static int[][] map, copy;
	public static int[] purifier = { -1, -1, -1, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		copy = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				// 공기청정기 좌표[ x1, y1, x2, y2 ]
				if (map[i][j] == -1) {
					if (purifier[0] == -1) {
						purifier[0] = j;
						purifier[1] = i;
					} else {
						purifier[2] = j;
						purifier[3] = i;
					}
				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			purify(0, purifier[0], purifier[1]);
			purify(4, purifier[2], purifier[3]);
		}
		System.out.println(getLeftDirt());
	}

	public static void spread() {

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		for (int i = 0; i < R; i++) {
			System.arraycopy(map[i], 0, copy[i], 0, C);
		}

		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {

				int dirt = copy[y][x];

				// 먼지가 없으면 스킵
				if (dirt <= 0)
					continue;

				int moveAmount = dirt / 5; // 확산되는 먼지의 양

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int rx = x + dx[i];
					int ry = y + dy[i];

					// 맵 밖으로 나가거나 공기청정기 위치면 스킵
					if (rx < 0 || rx >= C || ry < 0 || ry >= R || map[ry][rx] == -1)
						continue;

					map[y][x] -= moveAmount; // 원래 위치에서 확산된 만큼 차감
					map[ry][rx] += moveAmount; // 이동한 위치에 확산된 먼지의 양 추가
				}
			}
		}
	}

	public static void purify(int direction, int x, int y) {
		// 상우하좌, 하우상좌 순서로 탐색
		int[] dx = { 0, 1, 0, -1, 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0, 1, 0, -1, 0 };

		int rx = x + dx[direction];
		int ry = y + dy[direction];

		while (true) {

			int tmpX = rx + dx[direction];
			int tmpY = ry + dy[direction];

			if (tmpX == x && tmpY == y) {
				map[ry][rx] = 0;
				return;
			}

			if (tmpX < 0 || tmpX >= C || tmpY < 0 || tmpY >= R) {
				direction++;
				continue;
			}

			if ((tmpX == C - 1 && tmpY == y)) {
				direction++;
			}

			map[ry][rx] = map[tmpY][tmpX];
			rx = tmpX;
			ry = tmpY;
		}
	}

	public static int getLeftDirt() {
		int sum = 0;
		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {

				int dirt = map[y][x];

				// 먼지가 없으면 스킵
				if (dirt <= 0)
					continue;

				sum += dirt;
			}
		}

		return sum;
	}

}