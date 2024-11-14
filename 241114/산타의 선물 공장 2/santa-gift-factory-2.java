
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class Main {

	static class Present {
		int prev;
		int next;
		int belt;

		public Present(int belt, int prev, int next) {
			super();
			this.belt = belt;
			this.prev = prev;
			this.next = next;
		}
	}

	static Present[] presents;
	static Deque<Integer>[] belts;
	static int N, M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringBuilder sb = new StringBuilder();

		int q = Integer.parseInt(br.readLine());

		int src, dst, num, result;
		for (int t = 0; t < q; t++) {
			String[] cmd = br.readLine().split(" ");

			switch (cmd[0]) {

			case "100":
				N = Integer.parseInt(cmd[1]); // 벨트 수
				M = Integer.parseInt(cmd[2]); // 선물 수

				init(cmd);

				break;

			case "200":
				src = Integer.parseInt(cmd[1]);
				dst = Integer.parseInt(cmd[2]);

				result = moveAll(src, dst);
				// sb.append(result).append("\n");
				System.out.println(result);

				break;

			case "300":
				src = Integer.parseInt(cmd[1]);
				dst = Integer.parseInt(cmd[2]);

				result = changeForward(src, dst);
				// sb.append(result).append("\n");
				System.out.println(result);

				break;

			case "400":
				src = Integer.parseInt(cmd[1]);
				dst = Integer.parseInt(cmd[2]);

				result = divide(src, dst);
				// sb.append(result).append("\n");
				System.out.println(result);

				break;

			case "500":
				num = Integer.parseInt(cmd[1]);

				result = getPresentInfo(num);
				// sb.append(result).append("\n");
				System.out.println(result);

				break;

			case "600":
				num = Integer.parseInt(cmd[1]);

				result = getBeltInfo(num);
				// sb.append(result).append("\n");
				System.out.println(result);
				
				break;

			default:
				break;
			}
		}

		// System.out.println(sb);
	}

	// 100
	private static void init(String[] cmd) {
		belts = new ArrayDeque[N + 1];
		presents = new Present[M + 1];

		for (int i = 1; i <= N; i++) {
			belts[i] = new ArrayDeque<>();
		}

		for (int i = 1; i <= M; i++) {
			int belt = Integer.parseInt(cmd[2 + i]);

			if (belts[belt].isEmpty())
				presents[i] = new Present(belt, -1, -1);
			else {
				int prev = belts[belt].peekLast();
				presents[i] = new Present(belt, prev, -1);
				presents[prev].next = i;
			}
			belts[belt].offerLast(i);
		}
	}

	// 200
	private static int moveAll(int src, int dst) {

		while (!belts[src].isEmpty()) {
			int tmp = belts[src].pollLast();
			int prev = presents[tmp].prev;
			Integer next = belts[dst].peekFirst();
			next = next == null ? -1 : next;

			belts[dst].offerFirst(tmp); // 선물 옮기기

			if (prev != -1)
				presents[prev].next = -1;

			if (next != -1)
				presents[next].prev = tmp;

			presents[tmp].prev = -1;
			presents[tmp].next = next;
			presents[tmp].belt = dst;
		}

		return belts[dst].size();
	}

	// 300
	private static int changeForward(int src, int dst) {

		Integer a = belts[src].pollFirst();
		a = a == null ? -1 : a;
		Integer b = belts[dst].pollFirst();
		b = b == null ? -1 : b;

		Integer next = belts[dst].peekFirst();
		if (next != null)
			presents[next].prev = a;

		// 선물이 있으면 교체 후 벨트 갱신
		if (a != -1) {
			belts[dst].offerFirst(a);

			if (next != null) {
				presents[a].next = next;
			} else {
				presents[a].next = -1;
			}
			presents[a].belt = dst;
		}

		next = belts[src].peekFirst();
		if (next != null)
			presents[next].prev = b;

		if (b != -1) {
			belts[src].offerFirst(b);

			if (next != null) {
				presents[b].next = next;
			} else {
				presents[b].next = -1;
			}
			presents[b].belt = src;
		}

		return belts[dst].size();
	}

	// 400
	private static int divide(int src, int dst) {
		Stack<Integer> stack = new Stack<>();

		int cnt = belts[src].size() / 2;

		for (int i = 0; i < cnt; i++) {

			int tmp = belts[src].pollFirst();
			stack.add(tmp);
		}

		Integer first = belts[src].peekFirst();
		if (first != null)
			presents[first].prev = -1;

		if (!stack.isEmpty()) {
			int tmp = stack.pop();
			Integer next = belts[dst].peekFirst();

			if (next != null) {
				presents[tmp].next = next;
				presents[next].prev = tmp;
			} else {
				presents[tmp].next = -1;
			}

			belts[dst].offerFirst(tmp);
			presents[tmp].belt = dst;
		}

		while (!stack.isEmpty())

		{
			int tmp = stack.pop();

			belts[dst].offerFirst(tmp);
			presents[tmp].belt = dst;
		}

		return belts[dst].size();
	}

	// 500
	private static int getPresentInfo(int num) {

		int a = presents[num].prev;
		int b = presents[num].next;

		return a + b * 2;
	}

	// 600
	private static int getBeltInfo(int num) {

		Integer a = belts[num].peekFirst();
		Integer b = belts[num].peekLast();
		int c = belts[num].size();

		if (a == null)
			a = -1;

		if (b == null)
			b = -1;

		return a + 2 * b + 3 * c;
	}
}
