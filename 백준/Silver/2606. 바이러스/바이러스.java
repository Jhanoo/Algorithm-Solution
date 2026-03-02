import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            edges[u].add(v);
            edges[v].add(u);
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;

        int ans = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u != 1) {
                ans++;
            }

            for (int v : edges[u]) {
                if (visited[v]) continue;

                q.offer(v);
                visited[v] = true;
            }
        }

        System.out.println(ans);
    }
}