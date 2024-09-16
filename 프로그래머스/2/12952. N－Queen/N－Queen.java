class Solution {
    
    public static int N, cnt;
	public static boolean[][] board;

	public int solution(int n) {

		N = n;
		cnt = 0;
		board = new boolean[N][N];
		dfs(0);

		return cnt;
	}

	public static void dfs(int row) {

		// 마지막 줄까지 N개의 퀸을 놓았으면
		if (row == N) {
			cnt++;
			return;
		}

		for (int col = 0; col < N; col++) {

			// 퀸을 놓을 수 있으면
			if (canPut(row, col)) {
				board[row][col] = true;
				dfs(row + 1);
				board[row][col] = false;
			}
		}

	}

	public static boolean canPut(int r, int c) {

		// 우상, 우, 우하, 하, 좌하, 좌, 좌상, 상 (8방향 탐색)
		int[] dx = { 1, 1, 1, 0, -1, -1, -1, 0 };
		int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

		for (int i = 0; i < 8; i++) {
			int rx = c;
			int ry = r;

			// 해당 방향으로 이
			for (int j = 0; j < N; j++) {
				rx += dx[i];
				ry += dy[i];

				// 보드 밖으로 나가면 continue
				if (rx < 0 || rx >= N || ry < 0 || ry >= N)
					continue;

				// 해당 칸에 퀸이 이미 존재하면 false 반환
				if (board[ry][rx])
					return false;
			}

		}

		// 문제없으면 true 반환
		return true;
	}
}