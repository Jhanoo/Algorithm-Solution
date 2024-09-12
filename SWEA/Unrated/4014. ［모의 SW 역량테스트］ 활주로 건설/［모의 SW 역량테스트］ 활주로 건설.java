import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static int N, X;
	public static int[][] map;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // 6<=N<=20
			X = Integer.parseInt(st.nextToken()); // 2<=X<=4

			map = new int[N][N]; // 1<=높이<=6

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt = buildRight();
			int cnt2 = buildDown();

			sb.append(cnt + cnt2);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int buildRight() {
		int cnt = 0;
		boolean[][] built = new boolean[N][N];

		// 각 행마다 반복
		for (int r = 0; r < N; r++) {
			boolean isFail = false;

			// left,right = { col, height }
			int[] left = { 0, map[r][0] };
			int[] right = { 0, map[r][0] };

			for (int c = 1; c < N; c++) {
				right[0] = c;
				right[1] = map[r][c];

				// 높이가 같을 때 스킵
				if (left[1] == right[1])
					continue;

				// 왼쪽 높이 < 오른쪽 높이
				if (left[1] < right[1]) {
					// 높이 차이가 1이 아닐 때, 간격이 경사로 길이보다 짧을 때 break
					if (right[1] - left[1] != 1 || right[0] - left[0] < X) {
						isFail = true;
						break;
					}
					for (int i = 1; i <= X; i++) {
						if (built[r][right[0] - i]) {
							isFail = true;
							break;
						}

						built[r][right[0] - i] = true;
					}
					// 경사로 건설, left를 right로 갱신
					left[0] = right[0];
					left[1] = right[1];
				}
				// 왼쪽 높이 > 오른쪽 높이
				else if (left[1] > right[1]) {

					if (left[1] - right[1] != 1 || built[r][c]) {
						isFail = true;
						break;
					}

					int[] tmp = { c - 1, map[r][c - 1] };

					built[r][c] = true;
					for (int i = 0; i < X - 1; i++) {

						if (c + 1 == N) {
							isFail = true;
							break;
						}
						// right의 오른쪽 칸 탐색
						tmp[0] = ++c;
						tmp[1] = map[r][c];

						if (built[r][c]) {
							isFail = true;
							break;
						}

						built[r][c] = true;

						// right의 오른쪽이 높이가 다를 때 break
						if (tmp[1] != right[1]) {
							isFail = true;
							break;
						}
					}

					// 실패했을 때 break
					if (isFail) {
						break;
					}

					// 경사로 건설, left를 tmp로 갱신
					left[0] = tmp[0];
					left[1] = tmp[1];

					if (left[1] != right[1]) {
						isFail = true;
						break;
					}
				}
			}

			if (isFail) {
				continue;
			}
			cnt++;
		}
		return cnt;
	}

	public static int buildDown() {
		int cnt = 0;
		boolean[][] built = new boolean[N][N];

		for (int c = 0; c < N; c++) {
			boolean isFail = false;

			// left,right = { col, height }
			int[] up = { 0, map[0][c] };
			int[] down = { 0, map[0][c] };

			for (int r = 1; r < N; r++) {
				down[0] = r;
				down[1] = map[r][c];

				if (up[1] == down[1])
					continue;

				if (up[1] < down[1]) {

					if (down[1] - up[1] != 1 || down[0] - up[0] < X) {
						isFail = true;
						break;
					}
					for (int i = 1; i <= X; i++) {
						if (built[down[0] - i][c]) {
							isFail = true;
							break;
						}

						built[down[0] - i][c] = true;
					}

					up[0] = down[0];
					up[1] = down[1];
				} else if (up[1] > down[1]) {

					if (up[1] - down[1] != 1 || built[r][c]) {
						isFail = true;

						break;
					}

					int[] tmp = { r - 1, map[r - 1][c] };

					built[r][c] = true;
					for (int i = 0; i < X - 1; i++) {

						if (r + 1 == N) {
							isFail = true;
							break;
						}
						tmp[0] = ++r;
						tmp[1] = map[r][c];

						if (built[r][c]) {
							isFail = true;
							break;
						}

						built[r][c] = true;

						if (tmp[1] != down[1]) {
							isFail = true;
							break;
						}
					}

					if (isFail) {
						break;
					}

					up[0] = tmp[0];
					up[1] = tmp[1];

					if (up[1] != down[1]) {
						isFail = true;
						break;
					}
				}
			}

			if (isFail) {
				continue;
			}
			cnt++;
		}
		return cnt;
	}

}