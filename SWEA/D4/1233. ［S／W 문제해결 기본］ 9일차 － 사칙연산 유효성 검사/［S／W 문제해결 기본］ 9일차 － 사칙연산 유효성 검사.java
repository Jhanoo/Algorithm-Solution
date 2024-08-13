import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("input 1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(br.readLine());

			Node[] nodes = new Node[N + 1];

			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");

				Node tmpNode = new Node(s[1]);

				if (s.length >= 3)
					tmpNode.leftChild = Integer.parseInt(s[2]);
				if (s.length == 4)
					tmpNode.rightChild = Integer.parseInt(s[3]);

				nodes[Integer.parseInt(s[0])] = tmpNode;
			}

			int result = 1;
			for (int i = 1; i <= N; i++) {
				Node tmp = nodes[i];

				if (tmp.leftChild == 0 && tmp.rightChild == 0) {
					if (tmp.v.equals("+") || tmp.v.equals("-") || tmp.v.equals("*") || tmp.v.equals("/")) {
						result = 0;
						break;
					}
				} else {
					if (tmp.v.equals("+") || tmp.v.equals("-") || tmp.v.equals("*") || tmp.v.equals("/")) {
						continue;
					} else {
						result = 0;
						break;
					}
				}
			}

			sb.append(result + "\n");
		}

		System.out.println(sb);
	}

}