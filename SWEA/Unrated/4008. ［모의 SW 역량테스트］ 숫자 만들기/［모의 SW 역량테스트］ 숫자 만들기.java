import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static int N;
	public static int[] ops, nums, visited;
	public static StringBuilder sb;
	public static int min, max;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("input 4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());

			// [ +, -, *, / ]
			ops = new int[4];
			visited = new int[4];
			nums = new int[N];

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			String[] s = br.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(s[i]);
			}

			s = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(s[i]);
			}

			perm(0, "");
			sb.append(max - min);

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void perm(int k, String str) {

		if (k == N - 1) {
			int result = calc(str);
			min = min < result ? min : result;
			max = max > result ? max : result;
			return;
		}

		for (int i = 0; i < 4; i++) {

			if (visited[i] < ops[i]) {
				visited[i]++;
				perm(k + 1, new String(str + operator(i)));
				visited[i]--;
			}
		}

	}

	public static int calc(String str) {
		int tmp = nums[0];
		for (int i = 1; i < N; i++) {
			tmp = calcOp(tmp, str.charAt(i - 1), nums[i]);
		}

		return tmp;
	}

	private static int calcOp(int i, char op, int j) {
		if (op == '+')
			return i + j;
		else if (op == '-')
			return i - j;
		else if (op == '*')
			return i * j;
		else
			return i / j;
	}

	public static String operator(int i) {
		if (i == 0)
			return "+";
		else if (i == 1)
			return "-";
		else if (i == 2)
			return "*";
		else
			return "/";
	}

}