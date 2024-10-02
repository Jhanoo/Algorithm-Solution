class Solution {
    public int[] solution(String s) {
		int[] answer = { 0, 0 };

		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1')
				num++;
		}

		answer[0]++; // 횟수
		answer[1] += s.length() - num; // 0의 개수

		// 150,000 부터는 int 범위
		while (num > 1) {
			int[] changed = change(num);
			answer[0]++;
			answer[1] += changed[0];
			num = changed[1];
		}

		return answer;
	}

	public static int[] change(int num) {
		int one = 0;
		int zero = 0;

		while (num > 0) {

			// 마지막 비트가
			if ((num & 1) == 1) // 1이면
				one++;
			else // 0이면
				zero++;

			num >>= 1; // >> 비트시프트 연산 모든 비트를 한 칸씩 오른쪽으로 이동
		}

		return new int[] { zero, one };
	}
}