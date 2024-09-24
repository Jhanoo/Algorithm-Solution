import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static class Node {
		int leftChild, rightChild;

		public Node(int leftChild, int rightChild) {
			super();
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	public static int N;
	public static Node[] nodes;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		nodes = new Node[26];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			int node = s.charAt(0) - 'A';
			int leftChild = s.charAt(2) - 'A';
			int rightChild = s.charAt(4) - 'A';
			nodes[node] = new Node(leftChild, rightChild);
		}

		preOrderTraversal(0);
		System.out.println();
		inOrderTraversal(0);
		System.out.println();
		postOrderTraversal(0);
	}

	public static void preOrderTraversal(int root) {

		if (root < 0)
			return;

		System.out.print((char) ('A' + root));
		preOrderTraversal(nodes[root].leftChild);
		preOrderTraversal(nodes[root].rightChild);
	}

	public static void inOrderTraversal(int root) {

		if (root < 0)
			return;

		inOrderTraversal(nodes[root].leftChild);
		System.out.print((char) ('A' + root));
		inOrderTraversal(nodes[root].rightChild);
	}

	public static void postOrderTraversal(int root) {

		if (root < 0)
			return;

		postOrderTraversal(nodes[root].leftChild);
		postOrderTraversal(nodes[root].rightChild);
		System.out.print((char) ('A' + root));
	}

}