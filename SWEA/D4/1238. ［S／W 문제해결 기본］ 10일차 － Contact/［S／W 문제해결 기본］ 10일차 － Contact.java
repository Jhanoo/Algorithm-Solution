import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {

	public static class Node {
		public int v;
		public List<Integer> next;
		public boolean visited;
		public int degree;

		public Node(int v) {
			super();
			this.v = v;
			this.next = new ArrayList<Integer>();
			this.visited = false;
			this.degree = 0;
		}

	}

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String s[] = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]);
			int V = Integer.parseInt(s[1]);

			Node[] a = new Node[100];
			for (int i = 0; i < 100; i++) {
				a[i] = new Node(i + 1);
			}

			s = br.readLine().split(" ");
			for (int i = 0; i < N; i += 2) {
				int from = Integer.parseInt(s[i]);
				int to = Integer.parseInt(s[i + 1]);

				a[from - 1].next.add(to);
			}

			Queue<Integer> q = new ArrayDeque<>();
			q.offer(V);
			a[V - 1].visited = true;

			int maxDegree = 0;

			while (!q.isEmpty()) {
				int tmp = q.poll();

				Node tmpNode = a[tmp - 1];

				for (int i = 0; i < tmpNode.next.size(); i++) {
					int next = tmpNode.next.get(i);

					if (!a[next - 1].visited) {
						q.offer(next);
						a[next - 1].visited = true;
						a[next - 1].degree = tmpNode.degree + 1;
						maxDegree = tmpNode.degree + 1 > maxDegree ? tmpNode.degree + 1 : maxDegree;
					}
				}

			}

			for (int i = 99; i >= 0; i--) {
				if (a[i].degree == maxDegree) {
					sb.append(i + 1);
					break;
				}
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}
}