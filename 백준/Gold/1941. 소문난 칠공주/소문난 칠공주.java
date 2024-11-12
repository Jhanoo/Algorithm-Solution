import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

	static boolean[] a, visited;
	static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		a = new boolean[25]; // S: true, Y: false
		visited = new boolean[25];

		for (int i = 0; i < 5; i++) {
			String line = br.readLine();

			for (int j = 0; j < 5; j++) {
				if (line.charAt(j) == 'S') {
					a[i * 5 + j] = true;
				}
			}
		}

		comb(new int[7], 0, 0);

		System.out.println(result);
	}

	static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void comb(int[] selected, int start, int cnt) {
		if (cnt == 7) {
			int sNum = 0;

			Queue<Integer> q = new ArrayDeque<>();

			Set<Integer> set = new HashSet<>();
			q.offer(selected[0]);

			while (!q.isEmpty()) {
				int tmp = q.poll();
				set.add(tmp);

				for (int j = 0; j < 4; j++) {
					int x = tmp % 5 + d[j][1];
					int y = tmp / 5 + d[j][0];
					int next = 5 * y + x;

					if (y < 0 || y > 4 || x < 0 || x > 4 || !contains(selected, next) || set.contains(next))
						continue;

					q.offer(next);
				}
			}

			if (set.size() != 7)
				return;

			for (int i = 0; i < 7; i++) {
				if (a[selected[i]]) {
					sNum++;
				}
			}

			if (sNum >= 4) {
				result++;
			}
			return;
		}
		for (int i = start; i < 25; i++) {
			selected[cnt] = i;
			comb(selected, i + 1, cnt + 1);
		}
	}

	static boolean contains(int[] selected, int num) {

		for (int tmp : selected) {
			if (num == tmp)
				return true;
		}

		return false;
	}

}