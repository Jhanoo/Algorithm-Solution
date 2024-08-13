import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static class Node {
		String v;
		int leftChild;
		int rightChild;

		public Node(String v) {
			super();
			this.v = v;
		}

	}

	public static int N;
	public static StringBuilder sb;
	public static Node[] nodes;
	public static Stack<Integer> stack;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("input 1232.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			nodes = new Node[N + 1];
			stack = new Stack<Integer>();

			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");

				Node tmpNode = new Node(s[1]);

				if (s.length >= 3)
					tmpNode.leftChild = Integer.parseInt(s[2]);
				if (s.length == 4)
					tmpNode.rightChild = Integer.parseInt(s[3]);

				nodes[Integer.parseInt(s[0])] = tmpNode;

			}

			printPostOrder(1);

			sb.append(stack.pop() + "\n");
		}

		System.out.println(sb);
	}

	private static void printPostOrder(int current) {
		if (current == 0)
			return;

		printPostOrder(nodes[current].leftChild);

		printPostOrder(nodes[current].rightChild);

		String tmp = nodes[current].v;

		if (tmp.equals("+")) {
			int n2 = stack.pop();
			int n1 = stack.pop();
			int n = n1 + n2;

			stack.push(n);

		} else if (tmp.equals("-")) {
			int n2 = stack.pop();
			int n1 = stack.pop();
			int n = n1 - n2;

			stack.push(n);

		} else if (tmp.equals("*")) {
			int n2 = stack.pop();
			int n1 = stack.pop();
			int n = n1 * n2;

			stack.push(n);

		} else if (tmp.equals("/")) {
			int n2 = stack.pop();
			int n1 = stack.pop();
			int n = n1 / n2;

			stack.push(n);

		} else {
			int n = Integer.parseInt(tmp);
			stack.push(n);
		}

	}
}