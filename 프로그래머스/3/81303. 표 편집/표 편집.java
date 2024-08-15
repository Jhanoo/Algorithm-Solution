import java.util.Stack;

class Solution {
    public static class Node {
		int v;
		Node prev;
		Node next;

		public Node(int v, Node prev, Node next) {
			super();
			this.v = v;
			this.prev = prev;
			this.next = next;
		}

	}

	// D x: x행 아래 선택, U x: x행 위 선택,
	// C: 행 삭제(마지막 행이면 위 행 선택), Z: 최근 삭제된 행 복구(선택 행 변화x)
	// n: 처음 표의 행 개수, k: 처음 선택된 행 위치
	public String solution(int n, int k, String[] cmd) {
		Node[] table = new Node[n];

		table[0] = new Node(0, null, null);
		for (int i = 1; i < n; i++) {
			table[i] = new Node(i, table[i - 1], null);
			table[i - 1].next = table[i];
		}

		Stack<Node> stack = new Stack<Node>();

		Node tmpNode = table[k];

		for (int i = 0; i < cmd.length; i++) {
			String[] command = cmd[i].split(" ");

			if (command[0].equals("D")) {
				int x = Integer.parseInt(command[1]);

				for (int j = 0; j < x; j++) {
					tmpNode = tmpNode.next;
				}

			} else if (command[0].equals("U")) {
				int x = Integer.parseInt(command[1]);

				for (int j = 0; j < x; j++) {
					tmpNode = tmpNode.prev;
				}

			} else if (command[0].equals("C")) {
				if (tmpNode.prev != null)
					tmpNode.prev.next = tmpNode.next;

				if (tmpNode.next != null)
					tmpNode.next.prev = tmpNode.prev;

				stack.push(tmpNode);

				if (tmpNode.next == null)
					tmpNode = tmpNode.prev;
				else
					tmpNode = tmpNode.next;

			} else if (command[0].equals("Z")) {
				Node tmp = stack.pop();

				if (tmp.prev != null)
					tmp.prev.next = tmp;

				if (tmp.next != null)
					tmp.next.prev = tmp;

			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append("O");
		}

		while (!stack.isEmpty()) {
			sb.setCharAt(stack.pop().v, 'X');
		}

		return sb.toString();
	}
}