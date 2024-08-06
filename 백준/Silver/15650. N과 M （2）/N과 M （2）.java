import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int N;
	public static int R;
	public static int[] nums;
	public static StringBuilder sb;

	public static void combination(int cnt, int start) {

		if (cnt == R) {
			for (int n : nums) {
				sb.append(n + " ");
			}
			sb.append("\n");

			return;
		}

		for (int i = start; i <= N; i++) {
			nums[cnt] = i;
			combination(cnt + 1, i + 1);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		R = Integer.parseInt(split[1]);
		nums = new int[R];
		sb = new StringBuilder();

		combination(0, 1);

		System.out.println(sb);
	}

}