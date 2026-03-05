import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] a = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        int[][] visited = new int[N][N];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] > 0) continue;

                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                visited[i][j] = ++cnt;
                char color = a[i][j];

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int y = cur[0];
                    int x = cur[1];

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] != 0 || a[ny][nx] != color)
                            continue;

                        visited[ny][nx] = cnt;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
        }
        int RGB = cnt;

        cnt = 0;
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] > 0) continue;

                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                visited[i][j] = ++cnt;
                char color = a[i][j];

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int y = cur[0];
                    int x = cur[1];

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] != 0) continue;
                        if ((color == 'B' || a[ny][nx] == 'B') && a[ny][nx] != color) continue;

                        visited[ny][nx] = cnt;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
        }

        int RG_B = cnt;
        System.out.println(RGB + " " + RG_B);
    }
}