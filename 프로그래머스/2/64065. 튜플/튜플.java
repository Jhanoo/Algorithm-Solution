import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String s) {
		StringTokenizer st = new StringTokenizer(s, "{}");

		List<String> a = new ArrayList<String>();

		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (!tmp.equals(",")) {
				a.add(tmp);
			}
		}

		a.sort((o1, o2) -> {
			return o1.length() - o2.length();
		});

		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < a.size(); i++) {
			String[] tmp = a.get(i).split(",");

			for (int j = 0; j < tmp.length; j++) {
				int t = Integer.parseInt(tmp[j]);
				if (!result.contains(t))
					result.add(t);
			}
		}

		int[] answer = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}

		return answer;
	}
}