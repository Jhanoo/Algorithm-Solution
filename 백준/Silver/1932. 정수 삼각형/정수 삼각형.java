import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, cnt;
	public static int[] a, dp, num;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		cnt = N * (N + 1) / 2;
		a = new int[cnt];
		dp = new int[cnt];
		num = new int[N];

		cnt = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < i + 1; j++)
				a[cnt++] = Integer.parseInt(st.nextToken());
		}

		if (N == 1) {
			System.out.println(a[0]);
			return;
		}

		dp[0] = a[0];
		for (int level = 1; level < N; level++) {
			num[level] = num[level - 1] + level;

			for (int i = 0; i < level + 1; i++) {
				int leftParent = i == 0 ? 0 : dp[num[level - 1] + i - 1] + a[num[level] + i];
				int rightParent = i == level ? 0 : dp[num[level - 1] + i] + a[num[level] + i];

				dp[num[level] + i] = Math.max(leftParent, rightParent);
			}
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[num[N - 1] + i]);
		}

		System.out.println(max);
	}

}