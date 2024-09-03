import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[][] item = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				item[i][0] = Integer.parseInt(st.nextToken());
				item[i][1] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(item, (o1, o2) -> {
				return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
			});

			int[] dp = new int[K + 1];
			for (int i = 0; i < N; i++) {
				int v = item[i][0];

				if (v > K)
					break;

				int c = item[i][1];
				for (int j = K - v; j >= 0; j--) {
					dp[v + j] = Math.max(dp[v + j], dp[j] + c);
				}
			}

			int maxC = 0;
			for (int i = 1; i <= K; i++) {
				maxC = maxC > dp[i] ? maxC : dp[i];
			}
			sb.append(maxC);

			sb.append("\n");
		}

		System.out.println(sb);
	}

}