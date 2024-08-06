import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int N;
	public static int M;
	public static int cctvTotalNum;
	public static int[] nums;
	public static int[][] map;
	public static int min = 64;

	public static int up = 8;
	public static int down = 4;
	public static int left = 2;
	public static int right = 1;

	public static void watch(int cnt) {

		if (cnt == cctvTotalNum || cctvTotalNum == 0) {
			int ans = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						ans++;
					}
				}
			}

			min = min < ans ? min : ans;
			return;
		}

		int cctvNum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int direction;
				if (map[i][j] == 1) {
					if (cctvNum++ == cnt) {
						direction = up;
						watchDirection(cnt, i, j, direction);
						direction = down;
						watchDirection(cnt, i, j, direction);
						direction = left;
						watchDirection(cnt, i, j, direction);
						direction = right;
						watchDirection(cnt, i, j, direction);
					}
				} else if (map[i][j] == 2) {
					if (cctvNum++ == cnt) {
						direction = up | down;
						watchDirection(cnt, i, j, direction);
						direction = left | right;
						watchDirection(cnt, i, j, direction);
					}
				} else if (map[i][j] == 3) {
					if (cctvNum++ == cnt) {
						direction = up | right;
						watchDirection(cnt, i, j, direction);
						direction = right | down;
						watchDirection(cnt, i, j, direction);
						direction = down | left;
						watchDirection(cnt, i, j, direction);
						direction = left | up;
						watchDirection(cnt, i, j, direction);
					}
				} else if (map[i][j] == 4) {
					if (cctvNum++ == cnt) {
						direction = up | right | down;
						watchDirection(cnt, i, j, direction);
						direction = right | down | left;
						watchDirection(cnt, i, j, direction);
						direction = down | left | up;
						watchDirection(cnt, i, j, direction);
						direction = left | up | right;
						watchDirection(cnt, i, j, direction);
					}
				} else if (map[i][j] == 5) {
					if (cctvNum++ == cnt) {
						direction = up | right | down | left;
						watchDirection(cnt, i, j, direction);
					}
				}
			}
		}

	}

	public static void watchDirection(int cnt, int i, int j, int direction) {

		if ((direction & up) == up) {
			for (int k = i - 1; k >= 0; k--) {
				if (map[k][j] == 6) {
					break;
				}

				if (map[k][j] <= 0) {
					map[k][j] -= 1;
				}
			}
		}

		if ((direction & down) == down) {
			for (int k = i + 1; k < N; k++) {
				if (map[k][j] == 6) {
					break;
				}

				if (map[k][j] <= 0) {
					map[k][j] -= 1;
				}
			}
		}

		if ((direction & left) == left) {
			for (int k = j - 1; k >= 0; k--) {
				if (map[i][k] == 6) {
					break;
				}

				if (map[i][k] <= 0) {
					map[i][k] -= 1;
				}
			}
		}

		if ((direction & right) == right) {
			for (int k = j + 1; k < M; k++) {
				if (map[i][k] == 6) {
					break;
				}

				if (map[i][k] <= 0) {
					map[i][k] -= 1;
				}
			}
		}

		watch(cnt + 1);

		if ((direction & up) == up) {
			for (int k = i - 1; k >= 0; k--) {
				if (map[k][j] == 6) {
					break;
				}

				if (map[k][j] <= 0) {
					map[k][j] += 1;
				}
			}
		}

		if ((direction & down) == down) {
			for (int k = i + 1; k < N; k++) {
				if (map[k][j] == 6) {
					break;
				}

				if (map[k][j] <= 0) {
					map[k][j] += 1;
				}
			}
		}

		if ((direction & left) == left) {
			for (int k = j - 1; k >= 0; k--) {
				if (map[i][k] == 6) {
					break;
				}

				if (map[i][k] <= 0) {
					map[i][k] += 1;
				}
			}
		}

		if ((direction & right) == right) {
			for (int k = j + 1; k < M; k++) {
				if (map[i][k] == 6) {
					break;
				}

				if (map[i][k] <= 0) {
					map[i][k] += 1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(s[j]);
				map[i][j] = n;

				if (n != 0 && n != 6) {
					cctvTotalNum++;
				}
			}
		}

		watch(0);
		System.out.println(min);
	}

}