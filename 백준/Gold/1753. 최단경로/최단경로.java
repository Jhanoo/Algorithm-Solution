import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static class Node {
		public int vertex;
		public Node link;
		public int weight;

		public Node(int vertex, Node link, int weight) {
			super();
			this.vertex = vertex;
			this.link = link;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + ", weight=" + weight + "]";
		}

	}

	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());

		Node[] nodeList = new Node[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			nodeList[u - 1] = new Node(v - 1, nodeList[u - 1], w);
		}

		int distance[] = new int[V];
		boolean visited[] = new boolean[V];

		Arrays.fill(distance, INF);

		distance[K - 1] = 0;

		int min = 0;
		int current = 0;

		for (int cnt = 0; cnt < V; cnt++) {

			current = -1;
			min = INF;

			for (int i = 0; i < V; i++) {
				if (!visited[i] && distance[i] < min) {
					min = distance[i];
					current = i;
				}
			}

			if (current == -1) {
				break;
			}

			visited[current] = true;

			for (Node tmp = nodeList[current]; tmp != null; tmp = tmp.link) {
				if (!visited[tmp.vertex] && distance[tmp.vertex] > min + tmp.weight) {
					distance[tmp.vertex] = min + tmp.weight;
				}
			}
		}

		for (int i = 0; i < V; i++) {
			if (distance[i] == INF)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
	}

}