import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int N;
	public static int R;
	public static boolean[] isSelected;
	public static int[] nums;
	public static StringBuilder sb;

	public static void permutation(int cnt) {

		if (cnt == R) {
			for (int n : nums)
				sb.append(n + " ");
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {

			if (isSelected[i] == true) {
				continue;
			}

			nums[cnt] = i;
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;

		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		R = Integer.parseInt(split[1]);
		isSelected = new boolean[N + 1];
		nums = new int[R];
		sb = new StringBuilder();

		permutation(0);
		System.out.println(sb);
	}

}