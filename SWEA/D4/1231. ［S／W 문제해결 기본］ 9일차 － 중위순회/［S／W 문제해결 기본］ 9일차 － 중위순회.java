import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static int N;
	public static StringBuilder sb;
	public static char[] c;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1231.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());

			c = new char[N];
			int[][] child = new int[N + 1][2];

			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");

				int v = Integer.parseInt(s[0]);
				c[i] = s[1].charAt(0);

				for (int j = 2; j < s.length; j++) {
					child[i][j - 2] = Integer.parseInt(s[j]);
				}
			}
			printInOrder(1);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void printInOrder(int current) {

		if (current * 2 <= N) {
			printInOrder(current * 2);
		}

		sb.append(c[current - 1]);

		if (current * 2 + 1 <= N) {
			printInOrder(current * 2 + 1);
		}
	}

}