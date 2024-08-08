import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static final int A = 0;
	public static final int B = 99;
	public static int[][] a;
	public static int N;

	public static int canReach;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1219.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine().split(" ")[1]);

			a = new int[100][2];
			canReach = 0;

			String[] s = br.readLine().split(" ");

			for (int i = 0; i < s.length; i += 2) {
				int tmp = Integer.parseInt(s[i]);
				if (a[tmp][0] != 0) {
					a[tmp][1] = Integer.parseInt(s[i + 1]);

				} else {
					a[tmp][0] = Integer.parseInt(s[i + 1]);
				}
			}

			go(0);
			sb.append(canReach).append("\n");
		}

		System.out.println(sb);
	}

	public static void go(int cityNum) {

		if (cityNum == B) {
			canReach = 1;
			return;
		}

		if (a[cityNum][0] == 0) {
			return;
		}
		go(a[cityNum][0]);

		if (a[cityNum][1] == 0) {
			return;
		}
		go(a[cityNum][1]);

	}

}