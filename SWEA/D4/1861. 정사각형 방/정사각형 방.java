import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static int N;
	public static int[][] a;
	public static boolean[][] visited;
	public static int cntArr[], minArr[];

	public static int[] dy = { 1, 0, -1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };

	public static int min, cnt;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());

			a = new int[N][N];
			visited = new boolean[N][N];

			cntArr = new int[N * N];
			minArr = new int[N * N];
			Arrays.fill(minArr, Integer.MAX_VALUE);
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");

				for (int j = 0; j < N; j++) {
					a[i][j] = Integer.parseInt(s[j]);
				}
			}

			int idx = 0;
			int maxCnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 0;
					move(idx, i, j);

					maxCnt = maxCnt > cnt ? maxCnt : cnt;
					cntArr[idx++] = cnt;
				}
			}

			for (int i = 0; i < N * N; i++) {
				if (cntArr[i] == maxCnt) {
					min = min <= minArr[i] ? min : minArr[i];
				}
			}

			sb.append(min + " " + maxCnt);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void move(int idx, int i, int j) {

		if (visited[i][j])
			return;

		visited[i][j] = true;
		cnt++;

		if (a[i][j] <= minArr[idx]) {
			minArr[idx] = a[i][j];
		}

		for (int k = 0; k < 4; k++) {
			if (0 <= i + dy[k] && i + dy[k] < N && 0 <= j + dx[k] && j + dx[k] < N)
				if (a[i + dy[k]][j + dx[k]] == a[i][j] + 1 || a[i + dy[k]][j + dx[k]] == a[i][j] - 1) {
					move(idx, i + dy[k], j + dx[k]);
				}
		}

	}

}