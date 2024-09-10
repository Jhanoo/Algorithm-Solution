import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());

		int[] dp = new int[1000001];
		Arrays.fill(dp, 1000001);
		dp[1] = 0;

		for (int i = 1; i <= X; i++) {

			int min = dp[i];

			if (i % 3 == 0) {
				min = Math.min(min, dp[i / 3] + 1);
			}
			if (i % 2 == 0) {
				min = Math.min(min, dp[i / 2] + 1);
			}
			min = Math.min(min, dp[i - 1] + 1);

			dp[i] = min;
		}

		System.out.println(dp[X]);
	}

}