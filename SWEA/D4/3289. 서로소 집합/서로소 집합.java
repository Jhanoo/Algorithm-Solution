import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static int[] parents;
	public static int n;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 3289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] s = br.readLine().split(" ");
			n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);

			makeSet();

			for (int i = 0; i < m; i++) {
				s = br.readLine().split(" ");
				int a = Integer.parseInt(s[1]);
				int b = Integer.parseInt(s[2]);

				if (s[0].equals("0")) {
					union(a, b);
				} else if (s[0].equals("1")) {
					if (a == b) {
						sb.append("1");
					} else if (findSet(a) == findSet(b)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}

			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void makeSet() {
		parents = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	public static int findSet(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = findSet(parents[a]);
	}

	public static boolean union(int a, int b) {

		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;

		return true;
	}

}