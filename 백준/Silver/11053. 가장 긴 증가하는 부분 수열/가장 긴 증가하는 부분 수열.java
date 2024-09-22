import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		int max = 0;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
					max = Math.max(max, dp[i]);
				}
			}
		}

		System.out.println(max + 1);

	}

}