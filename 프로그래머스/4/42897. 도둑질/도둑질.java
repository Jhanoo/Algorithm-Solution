class Solution {
    public int solution(int[] money) {
		int n = money.length;

		int[] dp1 = new int[n];
		int[] dp2 = new int[n];

		dp1[0] = money[0];
		dp1[1] = dp1[0];
		dp1[2] = dp1[0] + money[2];

		dp2[0] = 0;
		dp2[1] = money[1];
		dp2[2] = Math.max(money[1], money[2]);

		steal(dp1, money);
		steal(dp2, money);

		int result = Math.max(dp1[n - 2], dp2[n - 1]);

		return result;
	}

	public static void steal(int[] dp, int[] money) {
		int n = money.length;

		for (int i = 3; i < n; i++) {
			int a = dp[i - 1];
			int b = dp[i - 2] + money[i];
			int c = dp[i - 3] + money[i];

			if (a > b) {
				if (a > c) {
					dp[i] = a; // a > b, a > c
				} else {
					dp[i] = c; // c > a > b
				}
			} else {
				if (b > c) {
					dp[i] = b; // b > a, b > c
				} else {
					dp[i] = c; // c > b > a
				}
			}
		}
	}
}