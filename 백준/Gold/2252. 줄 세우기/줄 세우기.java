import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Node {
		int v;
		Node link;

		public Node(int v, Node link) {
			super();
			this.v = v;
			this.link = link;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] inDegree = new int[N + 1];
		Node[] adjList = new Node[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			inDegree[b]++;
			adjList[a] = new Node(b, adjList[a]);
		}

		// Topological Sort
		List<Integer> ans = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i < N + 1; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {

			int v = q.poll();
			ans.add(v);

			for (Node tmp = adjList[v]; tmp != null; tmp = tmp.link) {

				if (--inDegree[tmp.v] == 0) {
					q.offer(tmp.v);
				}
			}
		}

		for (int v : ans)
			sb.append(v + " ");
		
		System.out.println(sb);
	}

}