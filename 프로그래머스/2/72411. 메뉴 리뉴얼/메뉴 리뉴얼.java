import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    public static Map<String, Integer> m;
	public static boolean[] visited;
	public static int max;
	public static List<String> result;

	public String[] solution(String[] orders, int[] course) {

		result = new ArrayList<String>();

		for (int i : course) {
			m = new HashMap<>();
			max = 2;

			for (String s : orders) {
				visited = new boolean[s.length()];
				char[] cArr = s.toCharArray();
				Arrays.sort(cArr);
				combination(cArr, 0, i);
			}

			for (String key : m.keySet()) {
				if (m.get(key) == max) {
					result.add(key);
				}
			}
		}

		result.sort((o1, o2) -> o1.compareTo(o2));

		String[] answer = result.toArray(new String[0]);
		return answer;
	}

	public static void combination(char[] s, int start, int r) {

		if (r == 0) {
			String tmp = "";
			for (int i = 0; i < s.length; i++) {
				if (visited[i]) {
					tmp += s[i];
				}
			}

			Integer exist = m.putIfAbsent(tmp, 1);
			if (exist != null) {
				m.put(tmp, exist + 1);
				max = max > exist + 1 ? max : exist + 1;
			} 

			return;

		} else {
			for (int i = start; i < s.length; i++) {
				visited[i] = true;
				combination(s, i + 1, r - 1);
				visited[i] = false;
			}
		}

	}
}