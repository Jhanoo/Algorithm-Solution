import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	private static class Node implements Comparable<Node> {
		String name;
		int value;

		Node prevName;
		Node nextName;

		public Node(String name, int value) {
			super();
			this.name = name;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if (this.equals(o))
				return 0;
			return this.value - o.value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return name.equals(other.name) || value == other.value;
		}

		@Override
		public String toString() {
			return "Node [name=" + name + ", value=" + value + "]";
		}

	}

	private static class Table {
		Set<Node> nodes;

		public Table() {
			super();
			this.nodes = new TreeSet<>();
		}

		// 시간복잡도 O(N)
		public Node[] searchName(String name) {
			Iterator<Node> it = nodes.iterator();

			Node tmpNode = it.next();
			Node nextNode = tmpNode.nextName;
			Node prevNode = null;

			boolean flag = false;

			// tmpNode가 사전 순으로 name 보다 뒤에 있는 경우
			if (tmpNode.name.compareTo(name) < 0) {
				while (tmpNode.name.compareTo(name) < 0) {
					prevNode = tmpNode.prevName;

					// 앞으로 이동
					if (prevNode != null)
						tmpNode = prevNode;
					else { // prev가 없으면 (맨 앞인 경우)
						flag = true;
						break;
					}
				}

				if (tmpNode.name.equals(name)) {
					return new Node[] { tmpNode };
				}

				// 맨 앞인 경우 prev = null, next = tmp
				if (flag) {
					nextNode = tmpNode;
					prevNode = null;
				} else {
					nextNode = prevNode.nextName;
				}
			}
			// tmpNode가 사전 순으로 name 보다 뒤에 있는 경우
			else if (tmpNode.name.compareTo(name) > 0) {
				while (tmpNode.name.compareTo(name) > 0) {
					nextNode = tmpNode.nextName;

					// 뒤로 이동
					if (nextNode != null)
						tmpNode = nextNode;
					else { // next가 없으면 (맨 뒤인 경우)
						flag = true;
						break;
					}
				}

				if (tmpNode.name.equals(name)) {
					return new Node[] { tmpNode };
				}

				// 맨 뒤인 경우 next = null, prev = tmp
				if (flag) {
					prevNode = tmpNode;
					nextNode = null;
				} else {
					prevNode = nextNode.prevName;
				}
			} else {
				return new Node[] { tmpNode };
			}

			return new Node[] { prevNode, nextNode };
		}

		// 시간복잡도 O(N)
		public int insert(String name, int value) {
			Node newNode = new Node(name, value);

			if (nodes.size() == 0) {
				nodes.add(newNode);
				return 1;
			}

			if (nodes.contains(newNode))
				return 0;

			Node[] a = searchName(name);

			// prev와 next 둘 다 있으면
			if (a[0] != null && a[1] != null) {
				a[0].nextName = newNode;
				newNode.prevName = a[0];

				a[1].prevName = newNode;
				newNode.nextName = a[1];

			}
			// prev가 없고 next만 있으면
			else if (a[0] == null) {
				a[1].prevName = newNode;
				newNode.nextName = a[1];

			}
			// prev만 있고 next가 없으면
			else {
				a[0].nextName = newNode;
				newNode.prevName = a[0];
			}

			nodes.add(newNode);

			return 1;
		}

		// 시간복잡도: O(N)
		public int delete(String name) {

			if (nodes.size() == 0)
				return 0;

			Node[] a = searchName(name);

			if (a.length == 2)
				return 0;

			nodes.remove(a[0]);

			return a[0].value;
		}

		// 시간복잡도: O(N)
		public String rank(int k) {

			if (nodes.size() < k)
				return "None";

			List<Node> a = new ArrayList<>(nodes);
			return a.get(k - 1).name;
		}

		public int sum(int k) {

			if (nodes.size() == 0)
				return 0;

			List<Node> a = new ArrayList<>(nodes);

			int result = 0;
			for (Node tmp : a) {
				if (tmp.value <= k) {
					result += tmp.value;
				} else {
					break;
				}
			}

			return result;
		}
	}

	private static Table t;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Q = Integer.parseInt(br.readLine());

		String[] cmd;
		String query, name;
		int value, k, result;
		for (int i = 0; i < Q; i++) {
			cmd = br.readLine().split(" ");
			query = cmd[0];

			if (query.equals("init")) {
				init();
			} else if (query.equals("insert")) {
				name = cmd[1];
				value = Integer.parseInt(cmd[2]);

				result = insert(name, value);
				sb.append(result + "\n");

			} else if (query.equals("delete")) {
				name = cmd[1];

				sb.append(delete(name) + "\n");

			} else if (query.equals("rank")) {
				k = Integer.parseInt(cmd[1]);

				sb.append(rank(k) + "\n");

			} else if (query.equals("sum")) {
				k = Integer.parseInt(cmd[1]);

				sb.append(sum(k) + "\n");
			}
		}

		System.out.println(sb);
	}

	private static void init() {
		t = new Table();
	}

	private static int insert(String name, int value) {
		return t.insert(name, value);
	}

	private static int delete(String name) {
		return t.delete(name);
	}

	private static String rank(int k) {
		return t.rank(k);
	}

	private static int sum(int k) {
		return t.sum(k);
	}

}