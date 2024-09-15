import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int D, W, K, min;
	public static int[][] map;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("input 2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // 두께 3<=D<=13
			W = Integer.parseInt(st.nextToken()); // 가로크기 1<=W<=20
			K = Integer.parseInt(st.nextToken()); // 합격기준 1<=K<=D

			map = new int[D][W];
			int[][] tmp = new int[D][W];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = st.nextToken().equals("1") ? 1 : 0;
					tmp[i][j] = map[i][j];
				}
			}

			check(0, 0);
			sb.append(min);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void check(int depth, int usedCnt) {

		if (depth == D) {

			int passCnt = 0;

			for (int c = 0; c < W; c++) {
				int prev = -1;
				int cnt = 0;
				boolean pass = false;

				for (int r = 0; r < D; r++) {
					int tmp = map[r][c];

					if (r == 0) {
						prev = map[r][c];
						cnt++;
					} else if (tmp == prev) {
						cnt++;
					} else if (tmp != prev) {
						prev = tmp;
						cnt = 1;
					}

					if (cnt >= K) {
						pass = true;
						break;
					}
				}

				if (!pass)
					break;
				else {
					passCnt++;
				}
			}

			if (passCnt == W) {
				min = Math.min(min, usedCnt);
			}
			return;
		}

		int[] tmp = new int[W];
		for (int i = 0; i < W; i++) {
			tmp[i] = map[depth][i];
		}

		check(depth + 1, usedCnt);

		for (int i = 0; i < W; i++) {
			map[depth][i] = 0;
		}
		check(depth + 1, usedCnt + 1);

		for (int i = 0; i < W; i++) {
			map[depth][i] = 1;
		}
		check(depth + 1, usedCnt + 1);

		map[depth] = tmp;

	}

}