import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    public static class Pos {
		int x, y;
		Pos prev;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}

	}

	public int solution(int[][] maps) {

		int n = maps.length;
		int m = maps[0].length;

		boolean visited[][] = new boolean[n][m];

		int xPos = 0;
		int yPos = 0;

		int xGoal = m - 1;
		int yGoal = n - 1;

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		// bfs
		Queue<Pos> q = new ArrayDeque<Pos>();

		Pos start = new Pos(yPos, xPos);
		q.offer(start);
		visited[yPos][xPos] = true;

		int answer = -1;
		while (!q.isEmpty()) {

			Pos tmp = q.poll();

			if (tmp.x == xGoal && tmp.y == yGoal) {
				tmp = tmp.prev;
				int cnt = 1;

				while (tmp != null) {
					tmp = tmp.prev;
					cnt++;
				}
				answer = cnt;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int x = tmp.x + dx[i];
				int y = tmp.y + dy[i];

				if (0 <= x && x < m && 0 <= y && y < n && maps[y][x] == 1 && !visited[y][x]) {
					Pos newPos = new Pos(x, y);
					q.offer(newPos);
					visited[y][x] = true;
					newPos.prev = tmp;
				}
			}
		}

		return answer;
	}
}