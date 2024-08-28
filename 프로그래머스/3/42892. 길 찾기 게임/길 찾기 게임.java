import java.util.Arrays;

class Solution {
    
    public static class Node {
		int v;
		int x, y;
		Node parent, leftChild, rightChild;

		public Node(int v, int x, int y) {
			super();
			this.v = v;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", x=" + x + ", y=" + y + "]";
		}

	}

	public static int max, idx;
	public static int[] pre, post;
	public static Node[] nodes;

	public int[][] solution(int[][] nodeinfo) {
		pre = new int[nodeinfo.length];
		post = new int[nodeinfo.length];
		nodes = new Node[nodeinfo.length];

		for (int i = 0; i < nodeinfo.length; i++) {
			nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
		}

		Arrays.sort(nodes, (o1, o2) -> {
			if (o1.y == o2.y)
				return o1.x - o2.x;

			return o2.y - o1.y;
		});

		Node root = nodes[0];
		

		for (int i = 1; i < nodes.length; i++) {
			insertNode(root, nodes[i]);
		}

		preOrder(root);
		idx = 0;
		postOrder(root);

		int[][] answer = { pre, post };
		return answer;
	}

	private void insertNode(Node parent, Node child) {
		if (child.x < parent.x) {
			if (parent.leftChild == null) {
				parent.leftChild = child;
			} else {
				insertNode(parent.leftChild, child);
			}
		} else {
			if (parent.rightChild == null) {
				parent.rightChild = child;
			} else {
				insertNode(parent.rightChild, child);
			}
		}
	}

	public static void preOrder(Node root) {
		if (root == null) {
			return;
		}
		pre[idx++] = root.v;
		preOrder(root.leftChild);
		preOrder(root.rightChild);
	}

	public static void postOrder(Node root) {
		if (root == null) {
			return;
		}
		postOrder(root.leftChild);
		postOrder(root.rightChild);
		post[idx++] = root.v;
	}
}