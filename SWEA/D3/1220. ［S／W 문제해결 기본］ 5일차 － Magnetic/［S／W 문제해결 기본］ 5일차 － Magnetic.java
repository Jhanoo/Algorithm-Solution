import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input_1220.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int n = Integer.parseInt(br.readLine());

			int[][] table = new int[n][n];

			// 1=N, 2=S
			for (int i = 0; i < n; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					table[i][j] = Integer.parseInt(s[j]);
				}
			}

			List<Integer> tmp;
			int cnt = 0;

			for (int row = 0; row < n; row++) {
				tmp = new ArrayList<>();

				for (int col = 0; col < n; col++) {
					int t = table[col][row];
					if (t != 0) {
						tmp.add(t);
					}
				}

				while (tmp.size() != 0) {
					if (tmp.get(0) == 2) {
						tmp.remove(0);
						continue;
					}
					break;
				}
				while (tmp.size() != 0) {
					if (tmp.get(tmp.size() - 1) == 1) {
						tmp.remove(tmp.size() - 1);
						continue;
					}
					break;
				}

				boolean prev = true;
				for (int i = 1; i < tmp.size(); i++) {
					if (tmp.get(i) == 2 && prev == true) {
						cnt++;
						prev = false;
					} else if (tmp.get(i) == 1) {
						prev = true;
					}
				}
			}

			sb.append(cnt + "\n");

		}

		System.out.println(sb);
	}

}