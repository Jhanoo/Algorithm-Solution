import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int[][] a;
	public static boolean[][] visited;
	public static int M, N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			String[] s = br.readLine().split(" ");

			M = Integer.parseInt(s[0]);
			N = Integer.parseInt(s[1]);
			int K = Integer.parseInt(s[2]);

			a = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < K; i++) {
				s = br.readLine().split(" ");
				int x = Integer.parseInt(s[0]);
				int y = Integer.parseInt(s[1]);

				a[y][x] = 1;
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check(i, j)) {
						cnt++;
					}
				}
			}

			sb.append(cnt + "\n");

		}
		System.out.println(sb);

	}

	public static boolean check(int y, int x) {

		if (visited[y][x] || a[y][x] == 0) {
			return false;
		}

		visited[y][x] = true;

		if (y + 1 < N)
			check(y + 1, x);

		if (y - 1 >= 0)
			check(y - 1, x);

		if (x + 1 < M)
			check(y, x + 1);

		if (x - 1 >= 0)
			check(y, x - 1);

		return true;
	}
}