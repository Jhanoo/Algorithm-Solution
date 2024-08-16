import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static int N, K;
    public static int[][] a;
    public static boolean[][] visited;
 
    public static int maxHeight, maxCnt;
    public static List<Integer[]> top;
 
    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { 1, 0, -1, 0 };
 
    public static boolean isDig;
 
    public static void main(String[] args) throws Exception {
 
        // System.setIn(new FileInputStream("input 1949.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
 
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            a = new int[N][N];
            visited = new boolean[N][N];
            top = new ArrayList<Integer[]>();
            maxHeight = 0;
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
 
                for (int j = 0; j < N; j++) {
                    a[i][j] = Integer.parseInt(st.nextToken());
 
                    maxHeight = maxHeight > a[i][j] ? maxHeight : a[i][j];
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (a[i][j] == maxHeight)
                        top.add(new Integer[] { i, j });
                }
            }
 
            int max = 0;
            for (int i = 0; i < top.size(); i++) {
                maxCnt = 0;
                Integer[] tmp = top.get(i);
                dfs(0, tmp[0], tmp[1], 1);
 
                max = max > maxCnt ? max : maxCnt;
            }
            sb.append(max);
            sb.append("\n");
        }
 
        System.out.println(sb);
    }
 
    public static void dfs(int k, int y, int x, int cnt) {
 
        if (visited[y][x])
            return;
 
        visited[y][x] = true;
        maxCnt = maxCnt > cnt ? maxCnt : cnt;
 
        for (int i = 0; i < 4; i++) {
 
            if (0 <= y + dy[i] && y + dy[i] < N && 0 <= x + dx[i] && x + dx[i] < N) {
                if (a[y + dy[i]][x + dx[i]] < a[y][x] - k) {
                    dfs(0, y + dy[i], x + dx[i], cnt + 1);
                }
 
                if (!isDig) {
                    for (int l = 1; l <= K; l++) {
 
                        if (a[y + dy[i]][x + dx[i]] - l < a[y][x]) {
                            isDig = true;
                            dfs(l, y + dy[i], x + dx[i], cnt + 1);
                            isDig = false;
                        }
                    }
                }
            }
        }
 
        visited[y][x] = false;
    }
}