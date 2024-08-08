import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Main {

	public static int N;
	public static int M;
	public static int D;
	public static int max;
	public static int[][] map;
	public static boolean[][] shoot;
	public static int[] archer;

	public static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			Position other = (Position) obj;

			return x == other.x && y == other.y;
		}

		@Override
		public String toString() {
			return "(" + y + ", " + x + ")";
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		D = Integer.parseInt(s[2]);

		map = new int[N][M];
		archer = new int[3];
		shoot = new boolean[N][M];
		max = -1;

		for (int i = 0; i < N; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		setArcher(0, 0);
		System.out.println(max);
	}

	private static void setArcher(int cnt, int idx) {

		if (cnt == 3) {
			play(0);

			return;
		}
		for (int i = idx; i < M; i++) {
			archer[cnt] = i;
			setArcher(cnt + 1, i + 1);
		}
	}

	private static void play(int cnt) {

		if (cnt == N) {
			int sum = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (shoot[i][j]) {
						sum++;
						shoot[i][j] = false;
					}
				}
			}

			shoot = new boolean[N][M];
			max = sum > max ? sum : max;

			return;
		}

		attack(N - 1 - cnt);

		play(cnt + 1);

	}

	private static void attack(int y) {

		LinkedList<Integer[]> q = new LinkedList<>();
		List<Position> deathList = new ArrayList<>();

		int dist = 1;

		q.add(new Integer[] { y, archer[0], dist });
		bfs(q, dist, deathList);
		q.clear();

		q.add(new Integer[] { y, archer[1], dist });
		bfs(q, dist, deathList);
		q.clear();

		q.add(new Integer[] { y, archer[2], dist });
		bfs(q, dist, deathList);

		for (Position p : deathList) {
			shoot[p.y][p.x] = true;
		}

	}

	private static void bfs(LinkedList<Integer[]> q, int dist, List<Position> deathList) {
		while (!q.isEmpty()) {
			Integer[] tmp = q.removeFirst();

			int tmpY = tmp[0];
			int tmpX = tmp[1];
			dist = tmp[2];

			if (map[tmpY][tmpX] == 1) {
				if (!shoot[tmpY][tmpX]) {
					deathList.add(new Position(tmpX, tmpY));

					return;
				}
			}

			if (dist++ == D) {
				continue;
			}

			if (tmpX - 1 >= 0) {
				q.add(new Integer[] { tmpY, tmpX - 1, dist });
			}

			if (tmpY - 1 >= 0) {
				q.add(new Integer[] { tmpY - 1, tmpX, dist });
			}

			if (tmpX + 1 < M) {
				q.add(new Integer[] { tmpY, tmpX + 1, dist });
			}
		}

	}

}