import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static class Node {
		int v;
		int cnt;
		Node prev;

		public Node(int v, int cnt, Node prev) {
			super();
			this.v = v;
			this.cnt = cnt;
			this.prev = prev;
		}
	}

	public static int[][] synergy;
	public static int N;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());

			synergy = new int[N][N];

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int min = Integer.MAX_VALUE;

			Queue<Node> q = new ArrayDeque<>();
			q.offer(new Node(0, 1, null));

			while (!q.isEmpty()) {
				Node tmp = q.poll();

				if (tmp.cnt == N / 2) {
					int[] a = new int[N / 2];
					int[] b = new int[N / 2];

					for (int i = 0; i < N / 2; i++) {
						a[(N / 2) - i - 1] = tmp.v;
						tmp = tmp.prev;
					}

					int aIdx = 0;
					int bIdx = 0;
					for (int i = 0; i < N; i++) {
						if (aIdx < N / 2 && i == a[aIdx]) {
							aIdx++;
							continue;
						}
						b[bIdx++] = i;
					}

					int diff = getDiff(a, b);
					min = Math.min(min, diff);
					continue;
				}

				for (int i = tmp.v + 1; i < N; i++) {
					q.offer(new Node(i, tmp.cnt + 1, tmp));
				}
			}

			sb.append(min);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int getDiff(int[] a, int[] b) {
		int diff = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {

				if (i == j)
					continue;

				diff += synergy[a[i]][a[j]];
			}
		}

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {

				if (i == j)
					continue;

				diff -= synergy[b[i]][b[j]];
			}
		}

		return Math.abs(diff);
	}

}