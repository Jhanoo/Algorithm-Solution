import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] a = new int[N][2];

		String[] s1 = br.readLine().split(" ");
		String[] s2 = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			a[i][0] = Integer.parseInt(s1[i]);
			a[i][1] = Integer.parseInt(s2[i]);
		}

		int[][] dp = new int[N + 1][10001];
		for (int i = 1; i <= N; i++) {

			int m = a[i - 1][0];
			int c = a[i - 1][1];

			if (c == 0) {
				dp[i][0] = dp[i - 1][0] + m;
			}

			for (int j = 1; j <= 10000; j++) {
				if (j >= c) {
					dp[i][j] = Math.max(dp[i - 1][j - c] + m, dp[i - 1][j]);
				} else
					dp[i][j] = dp[i - 1][j];
			}
		}

		for (int i = 0; i <= 10000; i++) {
			if (dp[N][i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
}