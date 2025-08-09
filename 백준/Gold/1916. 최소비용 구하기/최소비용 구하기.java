import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Edge>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new Edge(v, w));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        final int INF = 1_000_000_000;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dist[start] = 0;
        pq.offer(new int[]{0, start}); // {cost, vertex}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int v = cur[1];

            if (dist[v] < cost) continue;
            if (v == dest) break;

            for (Edge e : g[v]) {
                int nc = cost + e.w;
                if (nc < dist[e.to]) {
                    dist[e.to] = nc;
                    pq.offer(new int[]{nc, e.to});
                }
            }
        }

        System.out.println(dist[dest]);
    }

}
