import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
		Map<Integer, Integer> a = new HashMap<>();
		Map<Integer, Integer> b = new HashMap<>();

		int cnt = 0;

		a.put(topping[0], 1);
		for (int i = 1; i < topping.length; i++) {
			b.put(topping[i], b.getOrDefault(topping[i], 0) + 1);
		}

		if (a.size() == b.size())
			cnt++;

		for (int i = 1; i < topping.length; i++) {
			// a에 없으면 추가, 있으면 +1
			a.put(topping[i], a.getOrDefault(topping[i], 0) + 1);

			// b에서 1개면 제거, 2개 이상이면 -1
			if (!b.remove(topping[i], 1)) {
				b.replace(topping[i], b.get(topping[i]) - 1);
			}

			if (a.size() == b.size())
				cnt++;
		}

		return cnt;
	}
}