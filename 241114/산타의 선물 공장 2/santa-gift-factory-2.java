import java.io.*;
import java.util.*;

public class Main {

	static HashMap<Integer, Integer> map;
	static Deque<Integer>[] belts;
	static int N, M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

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
				sb.append(result).append("\n");

				break;

			case "300":
				src = Integer.parseInt(cmd[1]);
				dst = Integer.parseInt(cmd[2]);

				result = changeForward(src, dst);
				sb.append(result).append("\n");

				break;

			case "400":
				src = Integer.parseInt(cmd[1]);
				dst = Integer.parseInt(cmd[2]);

				result = divide(src, dst);
				sb.append(result).append("\n");
				break;

			case "500":
				num = Integer.parseInt(cmd[1]);

				result = getPresentInfo(num);
				sb.append(result).append("\n");

				break;

			case "600":
				num = Integer.parseInt(cmd[1]);

				result = getBeltInfo(num);
				sb.append(result).append("\n");

				break;

			default:
				break;
			}
		}

		System.out.println(sb);
	}

	private static void init(String[] cmd) {
		belts = new ArrayDeque[N + 1];
		map = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			belts[i] = new ArrayDeque<>();
		}

		for (int i = 1; i <= M; i++) {
			int belt = Integer.parseInt(cmd[2 + i]);
			belts[belt].offerLast(i);
			map.put(i, belt);
		}
	}

	private static int moveAll(int src, int dst) {

		while (!belts[src].isEmpty()) {
			int tmp = belts[src].pollLast();

			belts[dst].offerFirst(tmp); // 선물 옮기기
			map.put(tmp, dst); // 선물이 있는 벨트 인덱스 갱신
		}

		return belts[dst].size();
	}

	private static int changeForward(int src, int dst) {

		Integer a = belts[src].pollFirst();
		Integer b = belts[dst].pollFirst();

		// 선물이 있으면 교체 후 벨트 갱신
		if (a != null) {
			belts[dst].offerFirst(a);
			map.put(a, dst);
		}

		if (b != null) {
			belts[src].offerFirst(b);
			map.put(b, src);
		}

		return belts[dst].size();
	}

	private static int divide(int src, int dst) {

		int cnt = belts[src].size() / 2;

		for (int i = 0; i < cnt; i++) {

			int tmp = belts[src].pollFirst();

			belts[dst].offerFirst(tmp); // 선물 옮기기
			map.put(tmp, dst); // 선물이 있는 벨트 인덱스 갱신

		}

		return belts[dst].size();
	}

	private static int getPresentInfo(int num) {

		int belt = map.get(num);

		int a = -1;
		int b = -1;
		Iterator<Integer> it = belts[belt].iterator();

		while (it.hasNext()) {
			int tmp = it.next();

			if (tmp == num) {
				break;
			}
			a = tmp;
		}

		if (it.hasNext())
			b = it.next();

		return a + b * 2;
	}

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
