class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        
        int[][] dp = new int[n][n];
        for(int[] i : dp) {
            i = new int[n];
        }
        
        dp[0][0] = triangle[0][0];
        
        int result = 0;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if(j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    int max = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                    dp[i][j] = max;
                }
                
                if(i == n - 1) {
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        
        return result;
    }
}