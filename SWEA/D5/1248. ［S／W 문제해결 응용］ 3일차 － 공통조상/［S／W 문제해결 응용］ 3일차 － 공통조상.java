import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static class Node {
		int v;
		Node parent, leftChild, rightChild;

		public Node(int v) {
			super();
			this.v = v;
		}

		public void addChild(Node c) {
			if (leftChild == null) {
				leftChild = c;
			} else {
				rightChild = c;
			}
		}

		@Override
		public String toString() {
			return "Node [v=" + v + "]";
		}

	}

	public static int V, E;
	public static Node[] nodes;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1248.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			nodes = new Node[V + 1];
			for (int i = 1; i < V + 1; i++) {
				nodes[i] = new Node(i);
			}

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				nodes[p].addChild(nodes[c]);
				nodes[c].parent = nodes[p];
			}

			Node p = getCommonParent(nodes[a], nodes[b]);
			Queue<Node> q = new ArrayDeque<Node>();
			q.offer(p);

			int cnt = 0;
			while (!q.isEmpty()) {
				Node tmp = q.poll();
				cnt++;

				if (tmp.leftChild != null) {
					q.offer(tmp.leftChild);

					if (tmp.rightChild != null) {
						q.offer(tmp.rightChild);
					}
				}
			}

			sb.append(p.v + " ");
			sb.append(cnt);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static Node getCommonParent(Node a, Node b) {
		List<Integer> parents = new ArrayList<Integer>();

		Node parent = a.parent;
		while (parent != null) {
			parents.add(parent.v);
			parent = parent.parent;
		}

		parent = b.parent;
		while (parent != null) {
			if (parents.contains(parent.v)) {
				return parent;
			}
			parent = parent.parent;
		}

		return null;
	}

}