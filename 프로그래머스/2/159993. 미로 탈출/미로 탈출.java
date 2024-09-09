import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    public static int N, M;
	public static boolean[][][] visited;
	public static Pos S, E, L;
	public static int pushLever;

	public static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

	public int solution(String[] maps) {
		// 1x1 칸들로 이루어진 직사각형 미로
		N = maps.length;
		M = maps[0].length();

		visited = new boolean[2][N][M]; // 0: 레버 누르기 전, 1: 레버 누른 후, N x M 사이즈 맵

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < maps[i].length(); j++) {
				if (maps[i].charAt(j) == 'S')
					S = new Pos(j, i, 0); // 출발 지점
				else if (maps[i].charAt(j) == 'L')
					L = new Pos(j, i, 0); // 레버
				else if (maps[i].charAt(j) == 'E')
					E = new Pos(j, i, 0); // 출구
				else if (maps[i].charAt(j) == 'X') {
					visited[0][i][j] = true;
					visited[1][i][j] = true;
				}
			}
		}

		// 레버 찾고 최단거리 카운트
		int findLeverCnt = bfs(S);

		// 레버 못 찾은 경우
		if (pushLever == 0)
			return -1;

		// 레버 찾은 후 출구 찾고 최단거리 카운트
		int findExitCnt = bfs(L);

		// 출구 못 찾은 경우
		if (findExitCnt == -1)
			return -1;

		return findLeverCnt + findExitCnt;
	}

	public static int bfs(Pos start) {
		// 4방향 탐색
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		// BFS
		Queue<Pos> q = new ArrayDeque<Pos>();

		q.offer(start);
		visited[pushLever][start.y][start.x] = true;

		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int cnt = pos.cnt;

			// 레버 찾기 전, 레버 찾았을 때
			if (x == L.x && y == L.y && pushLever == 0) {
				pushLever = 1;
				return cnt;
			}
			// 레버 찾은 후, 도착 지점 찾았을 때
			if (x == E.x && y == E.y && pushLever == 1) {
				return cnt;
			}

			// 4방향 탐색(상하좌우)
			for (int i = 0; i < 4; i++) {
				// 이동하고자 하는 x,y 좌표
				int rx = x + dx[i];
				int ry = y + dy[i];

				// 맵 밖으로 나가거나, 이미 방문한 경우 스킵
				if (rx < 0 || rx >= M || ry < 0 || ry >= N || visited[pushLever][ry][rx]) {
					continue;
				}

				// Queue에 삽입 후 방문처리
				q.offer(new Pos(rx, ry, cnt + 1));
				visited[pushLever][ry][rx] = true;
			}
		}

		return -1;
	}
}