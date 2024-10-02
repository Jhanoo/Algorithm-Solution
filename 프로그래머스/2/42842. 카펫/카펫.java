class Solution {
    public int[] solution(int brown, int yellow) {
		// x: 가로, y: 세로
		// brown: B = 2x+2(y-2) = 2x+2y-4
		// 2(x+y) = B+4

		// yellow: Y = (x-2)*(y-2) = xy-2(x+y)+4
		// xy = Y+2(x+y)-4 = Y+B
		// 즉, xy = B+Y
		// 단, x >= y >= 3

		// 카펫 모양이므로 길쭉한 모양보다는 정사각형 모양으로 나와야 하므로
		// 중간에 있는 약수의 곱 구하기

		int xy = brown + yellow;
		int y = (int) Math.sqrt(xy); // 중간 값 찾기
		int x = 0;

		while (true) {
			// 약수인 경우 && brown 개수가 맞으면
			if (xy % y == 0 && brown == 2 * (xy / y + y - 2)) {
				x = xy / y;
				break;
			}
			y--;
		}

		int[] answer = { x, y };
		return answer;
	}
}