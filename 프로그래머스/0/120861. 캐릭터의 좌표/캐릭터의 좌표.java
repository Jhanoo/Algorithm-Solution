class Solution {
    public int[] solution(String[] keyinput, int[] board) {

		// up(0,1) down(0,-1) left(-1,0) right(1,0)
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { 1, -1, 0, 0 };

		int M = board[0];
		int N = board[1];

		int[][] map = new int[N][M];

		// 0,0 -> 맵 중앙으로 설정
		int x = M / 2;
		int y = N / 2;

		for (String cmd : keyinput) {
			int k = -1;
			switch (cmd) {
			case "up":
				k = 0;
				break;

			case "down":
				k = 1;
				break;

			case "left":
				k = 2;
				break;

			case "right":
				k = 3;
				break;
			}

			int rx = x + dx[k];
			int ry = y + dy[k];

			if (rx < 0 || rx >= M || ry < 0 || ry >= N)
				continue;

			x = rx;
			y = ry;
		}

		int[] answer = { x - M / 2, y - N / 2 };
		return answer;
	}
}