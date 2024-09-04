import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static int R, C;
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				visited[i][j] = line.charAt(j) == 'x' ? true : false;
			}
		}

		System.out.println(dfs());

	}

	public static int dfs() {

		int[] dx = { 1, 1, 1 };
		int[] dy = { 1, 0, -1 };

		int cnt = 0;
		Stack<int[]> stack = new Stack<>();

		for (int i = 0; i < R; i++) {
			stack.push(new int[] { 0, i });

			while (!stack.isEmpty()) {
				int[] pos = stack.pop();
				int x = pos[0];
				int y = pos[1];
				visited[y][x] = true;

				if (x == C - 1) {
					cnt++;
					break;
				}

				for (int j = 0; j < 3; j++) {
					int moveX = x + dx[j];
					int moveY = y + dy[j];

					if (0 <= moveY && moveY < R && 0 <= moveX && moveX < C && !visited[moveY][moveX]) {
						stack.push(new int[] { moveX, moveY });
					}

				}
			}
			stack.clear();
		}

		return cnt;
	}

}