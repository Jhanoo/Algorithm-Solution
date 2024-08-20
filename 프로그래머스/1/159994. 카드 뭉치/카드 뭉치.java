import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
		Queue<String> q1 = new ArrayDeque<String>();
		Queue<String> q2 = new ArrayDeque<String>();

		for (String s : cards1)
			q1.offer(s);

		for (String s : cards2)
			q2.offer(s);

		String tmp1 = q1.poll();
		String tmp2 = q2.poll();

		int i = 0;
		for (i = 0; i < goal.length; i++) {

			if (goal[i].equals(tmp1)) {
				tmp1 = q1.poll();
				continue;
			}

			if (goal[i].equals(tmp2)) {
				tmp2 = q2.poll();
				continue;
			}

			return "No";
		}

		return "Yes";
	}
}