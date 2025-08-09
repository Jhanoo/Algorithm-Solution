import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) map[i][j] = s.charAt(j) - '0';
        }

        int[][] dist = new int[N][M];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == N - 1 && y == M - 1) break;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 1 && dist[nx][ny] == 0) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        System.out.println(dist[N - 1][M - 1]);
    }

}
