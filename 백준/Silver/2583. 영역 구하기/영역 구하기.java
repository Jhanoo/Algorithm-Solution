import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int M, N, K;
	public static int[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new int[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int r = y1; r < y2; r++) {
				for (int c = x1; c < x2; c++) {
//					System.out.println(r + " " + c);
					visited[r][c] = -1;
				}
			}
		}

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };
		List<Integer> area = new ArrayList<Integer>();

		Queue<Integer> q;
		int idx = 1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {

				if (visited[i][j] != 0)
					continue;

				q = new ArrayDeque<Integer>();

				q.offer(j);
				q.offer(i);
				visited[i][j] = idx;

				int cnt = 0;
				while (!q.isEmpty()) {
					int x = q.poll();
					int y = q.poll();

					cnt++;

					for (int k = 0; k < 4; k++) {
						int rx = x + dx[k];
						int ry = y + dy[k];

						if (rx < 0 || rx >= N || ry < 0 || ry >= M || visited[ry][rx] != 0)
							continue;

						q.offer(rx);
						q.offer(ry);
						visited[ry][rx] = idx;
					}
				}
				area.add(cnt);
				idx++;
			}
		}

		area.sort((o1, o2) -> {
			return o1 - o2;
		});

		System.out.println(--idx);
		for (int i : area)
			System.out.print(i + " ");
	}

}