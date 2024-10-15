import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution {
    
    public int solution(String[] strs, String t) {
		int n = t.length();
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 987654321);
		dp[0] = 0;

		// HashSet에 단어 조각 삽입
		Set<String> set = new HashSet<>(Arrays.asList(strs));

		// dp[i]는 t의 0부터 i까지의 최소 조각 수
		for (int i = 1; i <= n; i++) {
			// 최대 5글자까지 조각을 확인 (조각 길이는 최대 5)
			for (int j = Math.max(0, i - 5); j < i; j++) {
				// j에서 i까지의 부분 문자열이 유효한지 확인
				if (set.contains(t.substring(j, i))) {
					dp[i] = Math.min(dp[i], dp[j] + 1);
				}
			}
		}

		// t의 끝까지 완성할 수 없다면 -1을 반환
		return dp[n] > 20000 ? -1 : dp[n];
	}
}