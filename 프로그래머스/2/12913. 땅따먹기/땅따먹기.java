class Solution {
    int solution(int[][] land) {
		int n = land.length; // 크기

		int[][] dp = new int[n][4];

		for (int[] i : dp)
			i = new int[4];

		dp[0] = land[0]; // 0번째 행 초기화

		int result = 0;
		for (int i = 1; i < n; i++) { // 1번째 행부터
			for (int j = 0; j < 4; j++) { // a[i][j]
				for (int k = 0; k < 4; k++) { // a[i-1][k]

					if (j != k) // i-1번째가 다른 열에서 내려올 때
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i][j]);

				}

				result = Math.max(result, dp[i][j]);
			}
		}

		return result;
	}
}