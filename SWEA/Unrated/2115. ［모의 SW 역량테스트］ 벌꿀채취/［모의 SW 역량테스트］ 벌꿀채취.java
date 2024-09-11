import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static int N, M, C;
	public static boolean[][] visited;
	public static int[][] honey;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // NxN 벌통 사이즈 3<=N<=10
			M = Integer.parseInt(st.nextToken()); // M: 꿀을 채취할 수 있는 벌통의 수 1<=M<=5
			C = Integer.parseInt(st.nextToken()); // C: 채취할 수 있는 꿀의 최대 양 10<=C<=30

			honey = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			List<Selection> possibleSelections = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c <= N - M; c++) {
					int[] selection = new int[M];
					for (int k = 0; k < M; k++) {
						selection[k] = honey[r][c + k];
					}
					possibleSelections.add(new Selection(r, c, r, c + M - 1, selection));
				}
			}

			int maxProfit = 0;

			for (int i = 0; i < possibleSelections.size(); i++) {
				Selection sel1 = possibleSelections.get(i);
				int honey1 = computeMaxHoney(sel1.honey, C);

				for (int j = i + 1; j < possibleSelections.size(); j++) {
					Selection sel2 = possibleSelections.get(j);

					if (!isOverlap(sel1, sel2)) {
						int honey2 = computeMaxHoney(sel2.honey, C);

						int profit = honey1 + honey2;
						maxProfit = Math.max(maxProfit, profit);
					}
				}
			}

			sb.append(maxProfit);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static int computeMaxHoney(int[] selection, int C) {
		int maxHoney = 0;
		int len = selection.length;

		for (int mask = 0; mask < (1 << len); mask++) {
			int sum = 0;
			int squaredSum = 0;

			for (int i = 0; i < len; i++) {
				if ((mask & (1 << i)) != 0) {
					sum += selection[i];
					squaredSum += selection[i] * selection[i];
				}
			}

			if (sum <= C) {
				maxHoney = Math.max(maxHoney, squaredSum);
			}
		}

		return maxHoney;
	}

	private static boolean isOverlap(Selection sel1, Selection sel2) {
		return !(sel1.endRow < sel2.startRow || sel2.endRow < sel1.startRow || sel1.endCol < sel2.startCol
				|| sel2.endCol < sel1.startCol);
	}

	private static class Selection {
		int startRow;
		int startCol;
		int endRow;
		int endCol;
		int[] honey;

		Selection(int startRow, int startCol, int endRow, int endCol, int[] honey) {
			this.startRow = startRow;
			this.startCol = startCol;
			this.endRow = endRow;
			this.endCol = endCol;
			this.honey = honey;
		}
	}
}