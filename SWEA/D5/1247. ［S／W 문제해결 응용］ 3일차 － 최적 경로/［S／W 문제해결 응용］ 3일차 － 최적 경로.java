import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}

	}

	public static final int INF = Integer.MAX_VALUE;
	public static int N;
	public static int[][] dp;
	public static Pos[] a;
	public static Pos home;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			a = new Pos[N + 1];
			dp = new int[N + 1][1 << (N + 1)];

			for (int i = 0; i < N + 1; i++) {
				Arrays.fill(dp[i], INF);
			}

			st = new StringTokenizer(br.readLine(), " ");

			Pos company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			a[0] = company;
			for (int i = 1; i < N + 1; i++) {
				a[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			int result = dfs(0, 1);

			sb.append(result);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int getDist(Pos a, Pos b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	public static int dfs(int start, int visited) {

		if (visited == (1 << N + 1) - 1) {
			dp[start][visited] = getDist(a[start], home);
			return getDist(a[start], home);
		}

		if (dp[start][visited] != INF) {
			return dp[start][visited];
		}

		for (int i = 1; i < N + 1; i++) {
			if ((visited & (1 << i)) != 0) {
				continue;
			}

			dp[start][visited] = Math.min(dp[start][visited], dfs(i, visited | (1 << i)) + getDist(a[start], a[i]));
		}

		return dp[start][visited];
	}
}