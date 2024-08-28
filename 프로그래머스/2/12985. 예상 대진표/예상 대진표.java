class Solution {
    
    public int solution(int n, int a, int b) {
		int answer = n;

		n >>= 1;
		int tmpN = n;
		int small, big;
		if (a < b) {
			small = a;
			big = b;
		} else {
			small = b;
			big = a;
		}
		while (tmpN > 0) {
			if (big <= tmpN) {
				answer >>= 1;
				n >>= 1;
				tmpN -= n;
			} else if (small > tmpN) {
				answer >>= 1;
				n >>= 1;
				tmpN += n;
			} else {
				break;
			}
		}

		int cnt = 0;
		while (answer > 1) {
			cnt++;
			answer >>= 1;
		}

		return cnt;
	}
}