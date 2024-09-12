import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int[][] dp;
	public static final int INF = 987654321;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			dp = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (i == j) {
						st.nextToken();
						continue;
					}

					dp[i][j] = Integer.parseInt(st.nextToken()) == 1 ? 1 : INF;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {

					if (k == i)
						continue;

					for (int j = 0; j < N; j++) {

						if (j == i || j == k)
							continue;

						dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
					}
				}
			}

			int min = INF;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += dp[i][j];
				}
				min = Math.min(min, sum);
			}

			sb.append(min);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}