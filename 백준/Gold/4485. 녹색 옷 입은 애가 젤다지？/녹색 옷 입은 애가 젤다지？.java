import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = 1;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());

			if (N == 0)
				break;

			int a[][] = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				for (int j = 0; j < N; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] distance = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++)
				Arrays.fill(distance[i], INF);

			distance[0][0] = a[0][0];

			int curX = 0;
			int curY = 0;
			int min = 0;

			for (int cnt = 0; cnt < N * N; cnt++) {

				curX = curY = -1;
				min = INF;

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited[i][j] && min > distance[i][j]) {
							min = distance[i][j];
							curY = i;
							curX = j;
						}
					}
				}

				if (curX == N - 1 && curY == N - 1)
					break;

				visited[curY][curX] = true;

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited[i][j] && Math.abs(i - curY) <= 1 && Math.abs(j - curX) <= 1
								&& (Math.abs(i - curY) & Math.abs(j - curX)) == 0 && distance[i][j] > min + a[i][j]) {

							distance[i][j] = min + a[i][j];
						}
					}
				}

			}

			sb.append("Problem " + tc + ": " + distance[N - 1][N - 1] + "\n");
			tc++;
		}
		System.out.println(sb);

	}
}