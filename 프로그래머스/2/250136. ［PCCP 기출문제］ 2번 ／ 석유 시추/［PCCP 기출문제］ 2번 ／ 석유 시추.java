import java.util.*;

class Solution {
    
    private static int N, M;
    private static boolean[][] visited;
    private static Map<Integer, Integer> oils;
    
    private static int[] dr = { 1, -1, 0, 0 };
    private static int[] dc = { 0, 0, 1, -1 };
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        
        visited = new boolean[N][M];
        oils = new HashMap<>();
            
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (land[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }
                // if (land[i][j] == 1)
                bfs(land, i, j);
            }
        }
        
        int answer = Collections.max(oils.values());
        
        return answer;
    }
    
    private static void bfs(int[][] land, int row, int col) {
        Queue<int[]> q = new ArrayDeque<>();
        
        visited[row][col] = true;
        q.offer(new int[] {row, col});
        
        int cnt = 0;
        Set<Integer> cols = new HashSet<>();
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            
            cnt++;
            cols.add(c);
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc] || land[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] { nr, nc });
            }
        }
        
        for (Integer key : cols) {
            oils.put(key, oils.getOrDefault(key, 0) + cnt);
        }
        
        return;
    }
}