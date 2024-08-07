import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static int N;
	public static int M;
	public static List<CCTV> cctvs;
	public static int[][] map;
	public static int min = 64;

	public static int up = 8;
	public static int down = 4;
	public static int left = 2;
	public static int right = 1;

	public static class CCTV {
		int type;
		int row;
		int col;

		public CCTV(int type, int row, int col) {
			super();
			this.type = type;
			this.row = row;
			this.col = col;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N][M];
		cctvs = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(s[j]);
				map[i][j] = n;

				if (n != 0 && n != 6) {
					cctvs.add(new CCTV(n, j, i));
				}
			}
		}

		watchCCTV(0);
		System.out.println(min);
	}

	public static void watchCCTV(int cnt) {

		// 기저 부분
		if (cnt == cctvs.size()) {
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

		CCTV cctv = cctvs.get(cnt);

		if (cctv.type == 1) {
			watchDirection(cnt, cctv.col, cctv.row, up);
			watchDirection(cnt, cctv.col, cctv.row, down);
			watchDirection(cnt, cctv.col, cctv.row, left);
			watchDirection(cnt, cctv.col, cctv.row, right);

		} else if (cctv.type == 2) {
			watchDirection(cnt, cctv.col, cctv.row, up | down);
			watchDirection(cnt, cctv.col, cctv.row, left | right);

		} else if (cctv.type == 3) {
			watchDirection(cnt, cctv.col, cctv.row, up | right);
			watchDirection(cnt, cctv.col, cctv.row, right | down);
			watchDirection(cnt, cctv.col, cctv.row, down | left);
			watchDirection(cnt, cctv.col, cctv.row, left | up);

		} else if (cctv.type == 4) {
			watchDirection(cnt, cctv.col, cctv.row, up | right | down);
			watchDirection(cnt, cctv.col, cctv.row, right | down | left);
			watchDirection(cnt, cctv.col, cctv.row, down | left | up);
			watchDirection(cnt, cctv.col, cctv.row, left | up | right);

		} else if (cctv.type == 5) {
			watchDirection(cnt, cctv.col, cctv.row, up | right | down | left);

		}

	}

	public static void watchDirection(int cnt, int col, int row, int direction) {

		if ((direction & up) == up) {
			for (int k = col - 1; k >= 0; k--) {
				if (map[k][row] == 6) {
					break;
				}

				if (map[k][row] <= 0) {
					map[k][row] -= 1;
				}
			}
		}

		if ((direction & down) == down) {
			for (int k = col + 1; k < N; k++) {
				if (map[k][row] == 6) {
					break;
				}

				if (map[k][row] <= 0) {
					map[k][row] -= 1;
				}
			}
		}

		if ((direction & left) == left) {
			for (int k = row - 1; k >= 0; k--) {
				if (map[col][k] == 6) {
					break;
				}

				if (map[col][k] <= 0) {
					map[col][k] -= 1;
				}
			}
		}

		if ((direction & right) == right) {
			for (int k = row + 1; k < M; k++) {
				if (map[col][k] == 6) {
					break;
				}

				if (map[col][k] <= 0) {
					map[col][k] -= 1;
				}
			}
		}

		watchCCTV(cnt + 1);

		if ((direction & up) == up) {
			for (int k = col - 1; k >= 0; k--) {
				if (map[k][row] == 6) {
					break;
				}

				if (map[k][row] < 0) {
					map[k][row] += 1;
				}
			}
		}

		if ((direction & down) == down) {
			for (int k = col + 1; k < N; k++) {
				if (map[k][row] == 6) {
					break;
				}

				if (map[k][row] < 0) {
					map[k][row] += 1;
				}
			}
		}

		if ((direction & left) == left) {
			for (int k = row - 1; k >= 0; k--) {
				if (map[col][k] == 6) {
					break;
				}

				if (map[col][k] < 0) {
					map[col][k] += 1;
				}
			}
		}

		if ((direction & right) == right) {
			for (int k = row + 1; k < M; k++) {
				if (map[col][k] == 6) {
					break;
				}

				if (map[col][k] < 0) {
					map[col][k] += 1;
				}
			}
		}
	}

}