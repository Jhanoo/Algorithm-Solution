import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    private static class ExitMap {
		int row;
		int col;
		int count;

		public ExitMap(int row, int col, int count) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}

	public int solution(String[] maps) {
		int answer = 0;
		int flag = 0;
		int count = 0;
		int[][] realMap = new int[maps.length][maps[0].length()];
		boolean[][][] visited = new boolean[maps.length][maps[0].length()][2];
		Queue<ExitMap> queue = new ArrayDeque<>();
		int[][] direction = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		// -1은 벽 0은 통로 1은 출발점, 2는 레버, 3은 목적지, realMap 작성
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				if (maps[i].charAt(j) == 'O') {
					realMap[i][j] = 0;
				} else if (maps[i].charAt(j) == 'X') {
					realMap[i][j] = -1;
				} else {
					if (maps[i].charAt(j) == 'S') {
						realMap[i][j] = 1;
						queue.add(new ExitMap(i, j, count));
						visited[i][j][flag] = true;
					} else if (maps[i].charAt(j) == 'L') {
						realMap[i][j] = 2;
					} else if (maps[i].charAt(j) == 'E') {
						realMap[i][j] = 3;
					}
				}
			}
		}

		while (!queue.isEmpty()) {
			ExitMap sdir = queue.poll();
			count = sdir.count + 1;
			if (flag == 1 &&realMap[sdir.row][sdir.col] == 3) {
				return sdir.count;
			}
			for (int i = 0; i < 4; i++) {
				int nRow = sdir.row + direction[i][0];
				int nCol = sdir.col + direction[i][1];
				if (nRow >= 0 && nRow < realMap.length && nCol >= 0 && nCol < realMap[0].length
						&& !visited[nRow][nCol][flag] && realMap[nRow][nCol] != -1) {

					if (flag == 0) {
						if (realMap[nRow][nCol] == 2) {
							flag = 1;
							visited[nRow][nCol][flag] = true;
							queue.clear();
							queue.add(new ExitMap(nRow, nCol, count));
							break;
						} else {
							visited[nRow][nCol][flag] = true;
							queue.add(new ExitMap(nRow, nCol, count));
						}
					} else {
						visited[nRow][nCol][flag] = true;
						queue.add(new ExitMap(nRow, nCol, count));
					}
				}
			}

		}

		return -1;
	}
}