import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] W = new int[N];
		int[] V = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		int w;
		int v;
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			w = W[i - 1];
			v = V[i - 1];
			for (int j = 1; j <= K; j++) {
				if (j >= w)
					dp[i][j] = Math.max(dp[i - 1][j - w] + v, dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}

		System.out.println(dp[N][K]);
	}

}