import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    public int solution(int n, int[][] computers) {
		int answer = 0;

		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				q.offer(i);
				visited[i] = true;
				answer++;
			}

			while (!q.isEmpty()) {

				int tmp = q.poll();

				for (int j = 0; j < n; j++) {

					if (computers[tmp][j] == 1 && !visited[j]) {
						q.offer(j);
						visited[j] = true;
					}
				}
			}

		}

		return answer;
	}
}