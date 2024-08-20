import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {

		Queue<Integer> q = new ArrayDeque<Integer>();

		int size = progresses.length;
		for (int i = 0; i < size; i++) {
			int rest = 100 - progresses[i];
			int days = rest / speeds[i] + (rest % speeds[i] == 0 ? 0 : 1);

			q.add(days);
		}

		if (size == 0)
			return new int[] { 0 };

		List<Integer> a = new ArrayList<Integer>();

		int tmp = q.poll();
		int cnt = 1;
		while (!q.isEmpty()) {
            
			int next = q.poll();

			if (tmp < next) {
				a.add(cnt);
				tmp = next;
				cnt = 1;
			} else {
				cnt++;
			}

			if (q.isEmpty()) {
				a.add(cnt);
			}
		}

		int[] answer = new int[a.size()];
		Arrays.setAll(answer, a::get);

		return answer;
	}
}