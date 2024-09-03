import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {

	public static class Node {
		int[] a;
		int cnt;

		public Node(int[] a, int cnt) {
			super();
			this.a = a;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [a=" + Arrays.toString(a) + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input 1244.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] s = br.readLine().split(" ");
			int[] board = new int[s[0].length()];
			int maxCnt = Integer.parseInt(s[1]);
			Set<Integer> set = new HashSet<Integer>();
			Node node = null;

			int n = board.length;

			for (int i = 0; i < s[0].length(); i++) {
				int tmp = s[0].charAt(i) - '0';

				board[i] = tmp;
				set.add(tmp);
				node = new Node(board, 0);
			}

			Queue<Node> q = new ArrayDeque<>();
			q.offer(node);

			int[] sorted = Arrays.copyOf(board, n);
			Arrays.sort(sorted);

			int[] result = new int[n];
			// bfs
			while (!q.isEmpty()) {

				Node tmp = q.peek();

				if (tmp.cnt == maxCnt) {
					break;
				}

				tmp = q.poll();

				boolean isSorted = true;
				for (int i = 0; i < n; i++) {
					if (sorted[n - i - 1] == tmp.a[i]) {
						continue;
					}
					isSorted = false;
					break;
				}
				if (isSorted) {
					if (set.size() == n && (maxCnt - tmp.cnt) % 2 == 1) {
						int swap = tmp.a[n - 1];
						tmp.a[n - 1] = tmp.a[n - 2];
						tmp.a[n - 2] = swap;
					}
					result = tmp.a;
					break;
				}

				for (int i = 0; i < n; i++) {
					int maxIdx = i;
					int max = tmp.a[maxIdx];

					for (int j = i + 1; j < n; j++) {
						if (max < tmp.a[j]) {
							maxIdx = j;
							max = tmp.a[maxIdx];
						}
					}

					if (i == maxIdx) {
						continue;
					}

					// bfs 삽입
					for (int j = i + 1; j < n; j++) {

						if (tmp.a[j] != tmp.a[maxIdx]) {
							continue;
						}

						int[] newTmp = Arrays.copyOf(tmp.a, n);
						max = newTmp[i];
						newTmp[i] = newTmp[j];
						newTmp[j] = max;
						Node newNode = new Node(newTmp, tmp.cnt + 1);
						q.offer(newNode);
					}
					break;
				}
			}

			Node tmp = q.poll();
			int[] max = null;
			if (tmp != null)
				max = tmp.a;

			if (tmp != null && q.isEmpty()) {
				result = max;
			}
			while (!q.isEmpty()) {
				tmp = q.poll();

				if (tmp.cnt == maxCnt) {

					for (int i = 0; i < n; i++) {
						if (max[i] == tmp.a[i])
							continue;

						if (max[i] < tmp.a[i]) {
							max = tmp.a;
						}

						break;
					}
					result = max;
				}
			}

			String ans = "";
			for (int i : result) {
				ans += i;
			}
			sb.append(ans);

			sb.append("\n");
		}

		System.out.println(sb);
	}

}