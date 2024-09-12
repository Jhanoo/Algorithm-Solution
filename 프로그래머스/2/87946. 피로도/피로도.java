class Solution {
    
    public static boolean[] visited;
	public static int[][] a;
	public static int n, max;

	// 1<=k(현재피로도)<=5000, 1<=row<=8, col=2 {최소필요피로도(leastFatigue),
	// 소모피로도(costFatigue)}
	// 1 <= costFatigue <= leastFatigue <= 1000
	public int solution(int k, int[][] dungeons) {

		n = dungeons.length;
		a = dungeons;

		// 각 던전마다 DFS 탐색
		for (int i = 0; i < n; i++) {
			// 현재 피로도보다 최소 필요 피로도가 큰 경우 스킵
			if (k < a[i][0])
				continue;

			visited = new boolean[n];
			visited[i] = true; // i번째 던전 방문처리
			dfs(i, k - a[i][1], 1); // 던전 DFS 탐색
		}

		return max;
	}

	public static void dfs(int idx, int currentFatigue, int cnt) {

		max = Math.max(max, cnt); // max 카운트값 갱신

		for (int i = 0; i < n; i++) {

			// 이미 탐색한 던전이거나 현재 피로도가 최소 필요 피로도보다 작은 경우 스킵
			if (visited[i] || currentFatigue < a[i][0])
				continue;

			visited[i] = true;
			dfs(i, currentFatigue - a[i][1], cnt + 1);
			visited[i] = false;
		}
	}
}