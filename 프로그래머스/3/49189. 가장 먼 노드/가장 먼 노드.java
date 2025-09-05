import java.util.*;

class Solution {
    // dp
    public int solution(int n, int[][] edge) {
        
        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        // 간선 초기화
        for (int i = 0; i < edge.length; i++) {
            int v1 = edge[i][0];
            int v2 = edge[i][1];
            
            edges[v1].add(v2);
            edges[v2].add(v1);
        }
        
        int[] vertices = new int[n + 1];
        Arrays.fill(vertices, -1); // 거리를 -1로 초기화
        
        // BFS
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1); // 1번 노드 삽입
        vertices[1] = 0; // 1번 노드 까지의 거리
        
        int maxDist = 0; // 가장 멀리 떨어진 노드까지의 거리
        
        while(!q.isEmpty()) {
            int v = q.poll(); // v번 노드
            int dist = vertices[v]; // 1번 노드 ~ v번 노드까지의 최단 거리
            
            maxDist = Math.max(maxDist, dist); // 거리가 멀면 갱신
            
            for (int next : edges[v]) {
                if (vertices[next] != -1) continue; // 최단거리로 방문한 적이 있는 노드는 스킵
                
                q.offer(next);
                vertices[next] = dist + 1;
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (vertices[i] == maxDist)
                answer++;
        }
        
        return answer;
    }
}