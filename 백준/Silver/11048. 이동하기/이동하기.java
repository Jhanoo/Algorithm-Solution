import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int[][] dp, a;
	public static int N, M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		a = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][M];
		dp[0][0] = a[0][0];

		int[] dx = { -1, 0, -1 };
		int[] dy = { 0, -1, -1 };

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {

				dp[y][x] = a[y][x];

				for (int k = 0; k < 3; k++) {
					int rx = x + dx[k];
					int ry = y + dy[k];

					if (rx < 0 || rx >= M || ry < 0 || ry >= N)
						continue;

					dp[y][x] = Math.max(dp[y][x], dp[ry][rx] + a[y][x]);
				}

			}
		}

		System.out.println(dp[N - 1][M - 1]);

	}
}