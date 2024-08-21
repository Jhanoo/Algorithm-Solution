import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		Queue<String> q = new ArrayDeque<String>();

		int answer = 0;

		int size = 0;
		for (int i = 0; i < number.length; i++) {
			m.put(want[i], number[i]);
			size += number[i];
		}

		for (int i = 0; i < discount.length; i++) {
			String item;
			Integer cnt;

			if (i >= 10) {
				if (size == 0)
					answer++;

				item = q.poll();

				cnt = m.get(item);
				if (cnt != null) {

					if (cnt >= 0)
						size++;

					m.replace(item, cnt + 1);
				}
			}

			item = discount[i];
			q.offer(item);

			cnt = m.get(item);
			if (cnt != null) {
				if (cnt > 0)
					size--;

				m.replace(item, cnt - 1);
			}
		}

		if (size == 0)
			answer++;

		return answer;
	}
}