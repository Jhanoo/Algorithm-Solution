import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    public String[] solution(String[] record) {
		Queue<List<String>> q = new ArrayDeque<List<String>>();
		Map<String, String> user = new HashMap<>();
		StringTokenizer st;

		for (String str : record) {
			st = new StringTokenizer(str);
			String cmd = st.nextToken();
			String uid = st.nextToken();

			if (cmd.equals("Enter")) {
				String name = st.nextToken();
				user.put(uid, name);

				List<String> tmp = new ArrayList<>();
				tmp.add(uid);
				tmp.add("님이 들어왔습니다.");

				q.offer(tmp);

			} else if (cmd.equals("Leave")) {
				List<String> tmp = new ArrayList<>();
				tmp.add(uid);
				tmp.add("님이 나갔습니다.");

				q.offer(tmp);

			} else if (cmd.equals("Change")) {
				String name = st.nextToken();
				user.put(uid, name);
			}
		}

		String[] answer = new String[q.size()];

		int i = 0;
		while (!q.isEmpty()) {
			List<String> tmp = q.poll();
			String name = user.get(tmp.get(0));
			answer[i++] = name + tmp.get(1);
		}

		return answer;
	}
}