import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static class Cell {
		List<int[]> pList;

		public Cell() {
			super();
			this.pList = new ArrayList<>();
			pList.add(new int[] { 0, 0 });
		}

		public void setBC(int v, int p) {
			pList.add(new int[] { v, p });
			pList.sort((o1, o2) -> o2[1] - o1[1]); // p 내림차순 정렬
		}

		@Override
		public String toString() {
			return "[pList=" + pList + "]";
		}

	}

	public static Cell[][] map;
	public static int[] moveA, moveB;
	public static int M, A;

	public static int[] dx = { 0, 0, 1, 0, -1 }; // 0: 정지, 1:상, 2:우, 3:하, 4: 좌
	public static int[] dy = { 0, -1, 0, 1, 0 }; // 0: 정지, 1:상, 2:우, 3:하, 4: 좌

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			// 1<=C(충전 범위)<=4, 10<=P(성능, 짝수)<=500
			M = Integer.parseInt(st.nextToken()); // 이동 시간 20<=M<=100
			A = Integer.parseInt(st.nextToken()); // BC의 개수 1<=A<=8

			moveA = new int[M];
			moveB = new int[M];
			map = new Cell[11][11]; // 지도 크기: 10x10, 인덱스 0은 안씀.
			for (int i = 1; i < 11; i++) {
				for (int j = 1; j < 11; j++) {
					map[i][j] = new Cell();
				}
			}

			String[] s1 = br.readLine().split(" ");
			String[] s2 = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(s1[i]);
				moveB[i] = Integer.parseInt(s2[i]);
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int v = 1 << i;
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				Queue<Integer> q = new ArrayDeque<Integer>();
				boolean[][] visited = new boolean[11][11];

				q.offer(x);
				q.offer(y);
				visited[y][x] = true;

				while (!q.isEmpty()) {
					int tmpX = q.poll();
					int tmpY = q.poll();

					map[tmpY][tmpX].setBC(v, p);

					for (int j = 1; j < 5; j++) {
						int rx = tmpX + dx[j];
						int ry = tmpY + dy[j];

						if (rx < 1 || rx > 10 || ry < 1 || ry > 10 || visited[ry][rx] || getDist(x, y, rx, ry) > c)
							continue;

						q.offer(rx);
						q.offer(ry);
						visited[ry][rx] = true;
					}
				}
			}

			sb.append(start());

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int getDist(int ax, int ay, int bx, int by) {
		return Math.abs(ax - bx) + Math.abs(ay - by);
	}

	public static int start() {
		int ax = 1;
		int ay = 1;
		int bx = 10;
		int by = 10;

		int sum = 0;

		for (int time = 0; time <= M; time++) {
			List<int[]> aBcList = map[ay][ax].pList;
			List<int[]> bBcList = map[by][bx].pList;

			int aBcCnt = aBcList.size();
			int bBcCnt = bBcList.size();

			int[] bestAbc = aBcList.get(0);
			int[] bestBbc = bBcList.get(0);

			// 서로 같은 bc에 있을 때
			if ((bestAbc[0] & bestBbc[0]) > 0) {
				int[] secondAbc = new int[] { 0, 0 };
				int[] secondBbc = new int[] { 0, 0 };

				if (aBcCnt > 1) {
					secondAbc = aBcList.get(1);
				}

				if (bBcCnt > 1) {
					secondBbc = bBcList.get(1);
				}
				int sumA0B1 = bestAbc[1] + secondBbc[1];
				int sumA1B0 = secondAbc[1] + bestBbc[1];
				int sumSameBc = bestAbc[1];

				int max = Math.max(sumA0B1, sumA1B0);
				max = Math.max(max, sumSameBc);
				sum += max;
			}
			// 서로 다른 bc에 있을 때
			else {
				sum += bestAbc[1] + bestBbc[1];
			}

			if (time < M) {
				ax += dx[moveA[time]];
				ay += dy[moveA[time]];

				bx += dx[moveB[time]];
				by += dy[moveB[time]];
			}
		}

		return sum;
	}

}