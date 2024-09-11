import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken()); // 재료 수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			int[] scores = new int[N];
			int[] calories = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}

			int[][] dp = new int[N + 1][L + 1];
			for (int i = 1; i <= N; i++) {
				int score = scores[i - 1];
				int calorie = calories[i - 1];

				for (int j = 1; j <= L; j++) {

					if (calorie <= j) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - calorie] + score);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

			sb.append(dp[N][L]);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}