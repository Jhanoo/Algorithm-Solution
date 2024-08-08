import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String args[]) throws Exception {

		// System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]);
			int K = Integer.parseInt(s[1]);

			int a[] = new int[N];
			s = br.readLine().split(" ");

			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(s[i]);
			}
			Arrays.sort(a);
			int min = a[K - 1] - a[0];

			for (int i = 1; i < N - K + 1; i++) {
				min = Math.min(min, a[i + K - 1] - a[i]);
			}
			System.out.println("#" + test_case + " " + min);
		}
	}

}