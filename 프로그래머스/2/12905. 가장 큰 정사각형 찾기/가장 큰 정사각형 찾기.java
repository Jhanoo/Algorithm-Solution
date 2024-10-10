class Solution {
    public int solution(int[][] board) {
		int N = board.length;
		int M = board[0].length;
		int[][] dp = new int[N][M];
		int max = 0;

		for (int i = 0; i < N; i++) {
			dp[i][0] = board[i][0];
            max = Math.max(dp[i][0], max);
        }

		for (int i = 0; i < M; i++) {
			dp[0][i] = board[0][i];
            max = Math.max(dp[0][i], max);
        }

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {

				if (board[i][j] == 0)
					continue;

				int up = dp[i - 1][j];
				int left = dp[i][j - 1];
				int leftUp = dp[i - 1][j - 1];
				dp[i][j] = Math.min(up, Math.min(left, leftUp)) + 1;
				max = Math.max(dp[i][j], max);
			}
		}

		return max * max;
	}
}