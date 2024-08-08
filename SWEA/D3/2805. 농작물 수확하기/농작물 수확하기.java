import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(br.readLine());

			int[][] farm = new int[N][N];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = s.charAt(j) - '0';
				}
			}

			int sum = 0;
			int midIdx = N / 2;
			for (int i = 0; i < midIdx; i++) {
				for (int j = 0; j < N; j++) {
					if (j >= (midIdx - i) && j <= (midIdx + i)) {
						sum += farm[i][j];
						sum += farm[N - i - 1][j];
					}
				}
			}
			for (int i = 0; i < N; i++) {
				sum += farm[midIdx][i];
			}

			sb.append(sum + "\n");

		}

		System.out.println(sb);
	}

}