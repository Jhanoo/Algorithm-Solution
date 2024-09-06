import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static String[] nums = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1221.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + "\n");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			st.nextToken(); // #n
			int N = Integer.parseInt(st.nextToken());

			int[] cnt = new int[10];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				String str = st.nextToken();

				for (int j = 0; j < 10; j++) {
					if (nums[j].equals(str)) {
						cnt[j]++;
						break;
					}
				}
			}

			for (int i = 0; i < 10; i++) {
				String ans = "";
				for (int j = 0; j < cnt[i]; j++) {
					ans += nums[i] + " ";
				}
				sb.append(ans);
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

}