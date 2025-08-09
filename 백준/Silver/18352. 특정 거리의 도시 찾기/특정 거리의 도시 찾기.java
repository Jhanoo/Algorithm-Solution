import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to;

        Edge(int to) {
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to));
        }

        ArrayList<Integer> ans = new ArrayList<>();

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{X, 0});
        visited[X] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int from = curr[0];
            int cost = curr[1];

            if (cost == K) {
                ans.add(from);
                continue;
            }

            for (Edge e : edges[from]) {
                if (visited[e.to]) continue;

                pq.offer(new int[]{e.to, cost + 1});
                visited[e.to] = true;
            }
        }

        Collections.sort(ans);
        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int i : ans) {
                System.out.println(i);
            }
        }
    }

}
