import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		String a[] = new String[N];

		for (int i = 0; i < N; i++) {
			a[i] = br.readLine();
		}

		sb.append(watch(true, a, N) + " " + watch(false, a, N));

		System.out.println(sb);
	}

	public static int watch(boolean watchGreen, String[] a, int N) {

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		Queue<Integer> q = new ArrayDeque<>(); // x,y 각각 enqueue

		q.offer(0); // 초기 x좌표
		q.offer(0); // 초기 y좌표

		int cnt = 0;

		int[][] visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				char color = 0;
				if (visited[i][j] == 0) {
					q.offer(j); // x
					q.offer(i); // y
					color = a[i].charAt(j);

					if (!watchGreen && color == 'G')
						color = 'R';

					visited[i][j] = ++cnt;
				}

				while (!q.isEmpty()) {
					int x = q.poll();
					int y = q.poll();

					for (int k = 0; k < 4; k++) {
						int moveX = x + dx[k];
						int moveY = y + dy[k];

						if (0 <= moveX && moveX < N && 0 <= moveY && moveY < N && visited[moveY][moveX] == 0) {
							char tmpColor = a[moveY].charAt(moveX);

							if (!watchGreen && tmpColor == 'G')
								tmpColor = 'R';

							if (tmpColor == color) {
								q.offer(moveX);
								q.offer(moveY);
								visited[moveY][moveX] = cnt;
							}
						}
					}
				}

			}
		}

		return cnt;
	}

}