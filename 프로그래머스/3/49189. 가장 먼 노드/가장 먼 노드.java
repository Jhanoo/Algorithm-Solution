import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        Arrays.setAll(graph, g -> new ArrayList<>());
        
        // 인접 노드 연결
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int[] dist = new int[n + 1]; // 거리 배열
        Arrays.fill(dist, -1); // -1: 미방문 으로 초기화
        
        // BFS
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1); // 1번 노드 삽입
        dist[1] = 0; // 1번 노드 까지의 거리
        
        int maxDist = 0; // 가장 멀리 떨어진 노드까지의 거리
        
        while(!q.isEmpty()) {
            int v = q.poll();
            int d = dist[v];
            if (d > maxDist) maxDist = d;
            
            for (int next : graph[v]) {
                if (dist[next] != -1) continue; // 방문한 노드는 스킵
                
                dist[next] = d + 1;
                q.offer(next);
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist)
                answer++;
        }
        
        return answer;
    }
}