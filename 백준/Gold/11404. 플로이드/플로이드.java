import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 987654321);
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			dp[from][to] = Math.min(dp[from][to], cost);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (i == j)
					continue;

				for (int k = 0; k < N; k++) {

					if (i == k)
						continue;

					dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dp[i][j] == 987654321 || i == j)
					System.out.print("0 ");
				else
					System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

	}

}