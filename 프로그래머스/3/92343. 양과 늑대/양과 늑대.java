import java.util.*;

class Solution {
    List<Integer>[] child;
    int[] info, dp;
    int n, ans;
    
    public int solution(int[] _info, int[][] edges) {
        info = _info;
        n = _info.length;

        child = new ArrayList[n];
        for (int i = 0; i < n; i++) child[i] = new ArrayList<>();
        for (int[] e : edges) child[e[0]].add(e[1]);

        dp = new int[1 << n];
        Arrays.fill(dp, -1);

        int visited = 1 << 0;   // 루트 방문
        int candi = 0;          // 루트 방문 후 자식들을 후보에 추가
        for (int c : child[0]) 
            candi |= (1 << c);

        ans = 1; // 루트 노드는 항상 양
        dfs(1, 0, candi, visited);
        return ans;
    }
    
    void dfs(int sheep, int wolf, int candiMask, int visitedMask) {
        ans = Math.max(ans, sheep);

        // 양의 수 갱신
        if (dp[visitedMask] >= sheep) return;
        dp[visitedMask] = sheep;

        // 후보들 순회
        for (int v = 0; v < n; v++) {
            if ((candiMask & (1 << v)) == 0) continue;

            int ns = sheep + (info[v] ^ 1);
            int nw = wolf + info[v];
            
            if (nw >= ns) continue; // 늑대가 양보다 많거나 같은 경우
            
            int nextVisited = visitedMask | (1 << v);
            int nextCandi = candiMask & ~(1 << v); // v를 후보에서 제거

            // v를 방문했으니 v의 자식들을 후보에 추가
            for (int c : child[v]) {
                // v의 자식을 아직 방문하지 않은 경우
                if ((nextVisited & (1 << c)) == 0)
                    nextCandi |= (1 << c);
            }

            dfs(ns, nw, nextCandi, nextVisited);
        }
    }
}