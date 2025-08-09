import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        int[][] home = new int[N][N];

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (home[i][j] > 0 || map[i][j] == 0) continue;

                q.offer(new int[]{i, j});
                int cnt = 0;
                home[i][j] = ++cnt;
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if (home[nx][ny] > 0 || map[nx][ny] == 0) continue;

                        q.offer(new int[]{nx, ny});
                        home[nx][ny] = ++cnt;
                    }
                }
                answer.add(cnt);
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (Integer i : answer) {
            System.out.println(i);
        }
    }

}
