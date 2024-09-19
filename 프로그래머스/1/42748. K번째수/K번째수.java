import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
		int n = commands.length;
		int[] answer = new int[n];

		for (int cnt = 0; cnt < n; cnt++) {

			// 인덱스는 0부터 시작하므로 1씩 뺀다.
			int i = commands[cnt][0] - 1;
			int j = commands[cnt][1] - 1;
			int k = commands[cnt][2] - 1;

			int[] tmp = new int[j - i + 1];

			System.arraycopy(array, i, tmp, 0, j - i + 1); // i번째 인덱스부터 (j-i+1)개 복사
			Arrays.sort(tmp); // 정렬
			answer[cnt] = tmp[k]; // k번째 수 추가
		}

		return answer;
	}
}