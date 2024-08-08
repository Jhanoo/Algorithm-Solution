import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static class Node {
		int vertex;
		boolean visited;
		List<Integer> prev;
		List<Integer> next;

		public Node(int vertex) {
			this.vertex = vertex;
			this.visited = false;
			this.prev = new ArrayList<Integer>();
			this.next = new ArrayList<Integer>();
		}

	}

	public static Node[] a;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1267.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] s = br.readLine().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);

			s = br.readLine().split(" ");

			a = new Node[V];
			for (int i = 0; i < V; i++) {
				a[i] = new Node(i + 1);
			}

			for (int i = 0; i < E * 2; i += 2) {
				int from = Integer.parseInt(s[i]);
				int to = Integer.parseInt(s[i + 1]);

				a[from - 1].next.add(to);
				a[to - 1].prev.add(from);
			}

			for (int i = 0; i < V; i++) {
				dfs(i);
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void dfs(int vertexIdx) {

		if (a[vertexIdx].visited)
			return;

		for (int i = 0; i < a[vertexIdx].prev.size(); i++) {
			int prevVertex = a[vertexIdx].prev.get(i);

			if (a[prevVertex - 1].visited == false) {
				dfs(prevVertex - 1);
			}
		}

		a[vertexIdx].visited = true;
		sb.append((vertexIdx + 1) + " ");

	}
}