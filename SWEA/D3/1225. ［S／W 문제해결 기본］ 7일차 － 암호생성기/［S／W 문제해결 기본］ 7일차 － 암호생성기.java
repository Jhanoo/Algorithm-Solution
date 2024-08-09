import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1225.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			br.readLine();
			String[] s = br.readLine().split(" ");

			Queue<Integer> a = new LinkedList<>();

			for (int i = 0; i < 8; i++) {
				a.add(Integer.parseInt(s[i]));
			}

			int idx = 0;
			int k = 1;

			while (true) {
				if (idx++ == 5) {
					k = 1;
					idx = 1;
				}

				int tmp = a.poll() - k++;

				if (tmp <= 0) {
					tmp = 0;
					a.add(tmp);
					break;
				} else {
					a.add(tmp);
				}

			}

			for (Integer n : a) {
				sb.append(n + " ");
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}
}