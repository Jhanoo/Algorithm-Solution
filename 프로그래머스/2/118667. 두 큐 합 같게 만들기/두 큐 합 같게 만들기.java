import java.util.*;

class Solution {
    
	public int solution(int[] queue1, int[] queue2) {

		long max = 0;
		long sum1 = 0;
		long sum2 = 0;
        
        int size1 = queue1.length;
        int size2 = queue2.length;

		Queue<Integer> q1 = new ArrayDeque<>();
		Queue<Integer> q2 = new ArrayDeque<>();

		for (int num : queue1) {
			max = Math.max(num, max);
			sum1 += num;
			q1.add(num);
		}

		for (int num : queue2) {
			max = Math.max(num, max);
			sum2 += num;
			q2.add(num);
		}

		// 합이 홀수 or 가장 큰 수가 합의 절반보다 큰 경우
		if ((sum1 + sum2) % 2 == 1 || max > (sum1 + sum2) / 2) {
			return -1;
		}

		int cnt = 0;
		while (sum1 != sum2) {
            if (cnt > (size1 + size2) * 4) {
                cnt = -1;
                break;
            }
			if (sum1 > sum2) {
				int a = q1.poll();
				q2.offer(a);
				sum1 -= a;
				sum2 += a;
			} else {
				int a = q2.poll();
				q1.offer(a);
				sum1 += a;
				sum2 -= a;
			}
			cnt++;
		}

		return cnt;

	}
}