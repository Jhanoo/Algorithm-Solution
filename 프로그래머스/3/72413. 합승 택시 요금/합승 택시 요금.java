import java.util.*;

class Solution {
    
    static final int INF = 100_000_000;
    static int[][] dp;
    
    // (s->N 합승 요금) + (N->a + N->b 요금) 의 최솟값 구하기
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dp = new int[n + 1][n + 1]; // 한 지점 -> 다른 지점까지 최소 비용
        
        // dp를 INF로 초기화
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }
        
        // 양방향 edges 요금 초기화
        for (int i = 0; i < fares.length; i++) {
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];
            
            dp[c][d] = f;
            dp[d][c] = f;
        }
        
        // 플로이드-워셜 알고리즘 
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (k == i || k == j) continue; // 경유지가 시작지와 같으면 스킵
                    
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    dp[j][i] = dp[i][j]; // 양방향 동일 요금
                }
            }
        }
        
        
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            int fareShared = dp[s][i];  // s->i 지점 까지의 최소 합승 요금
            int fareA = dp[i][a];       // i->a 지점 까지 최소 요금
            int fareB = dp[i][b];       // i->b 지점 까지 최소 요금
            
            answer = Math.min(answer, fareShared + fareA + fareB);
        }
        
        return answer;
    }
}