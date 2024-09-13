import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static class Core {
		int idx;
		int x, y;

		public Core(int idx, int x, int y) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

	}

	public static int N, coreNum, minCost, maxCoreNum;
	public static int[][] a;
	public static Core[] cores;
	public static boolean[] visited;

	public static final int INF = 987654321;

	public static int[] dx = { 0, 1, 0, -1, 0 };
	public static int[] dy = { 0, 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());

			a = new int[N][N];
			cores = new Core[12];
			coreNum = 0;
			maxCoreNum = 0;
			minCost = INF;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					a[i][j] = st.nextToken().equals("1") ? 1 : 0;

					if (a[i][j] == 1 && i != 0 && j != 0 && i != N - 1 && j != N - 1)
						cores[coreNum] = new Core(coreNum++, j, i);
				}
			}

			if (coreNum == 0) {
				sb.append(0);
				continue;
			}

			dfs(0, 0, 0);

			sb.append(minCost);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int depth, int cost, int tmpCoreNum) {

		// 전부 탐색한 경우
		if (depth == coreNum) {

			if (maxCoreNum < tmpCoreNum) {
				maxCoreNum = tmpCoreNum;
				// minCost 갱신
				minCost = cost;
			} else if (maxCoreNum == tmpCoreNum) {
				minCost = Math.min(cost, minCost);
			}

			return;
		}

		for (int i = 0; i < 5; i++) {

			if (i == 0) {
				dfs(depth + 1, cost, tmpCoreNum);
				continue;
			}

			int rx = cores[depth].x + dx[i];
			int ry = cores[depth].y + dy[i];
			int tmpCost = 0;
			boolean conflict = false;

			while (true) {

				if (rx < 0 || rx >= N || ry < 0 || ry >= N)
					break;

				if (a[ry][rx] != 0) {
					conflict = true;
					break;
				}

				a[ry][rx] = 2;
				rx += dx[i];
				ry += dy[i];

				tmpCost++;
			}

			// 충돌난 경우 복구
			if (conflict) {
				while (true) {
					rx -= dx[i];
					ry -= dy[i];

					if (rx == cores[depth].x && ry == cores[depth].y)
						break;

					a[ry][rx] = 0;
				}
			} else {
				dfs(depth + 1, cost + tmpCost, tmpCoreNum + 1);
			}
			// 복구
			while (!conflict) {
				rx -= dx[i];
				ry -= dy[i];

				if (rx == cores[depth].x && ry == cores[depth].y)
					break;

				a[ry][rx] = 0;
			}
		}
	}

}