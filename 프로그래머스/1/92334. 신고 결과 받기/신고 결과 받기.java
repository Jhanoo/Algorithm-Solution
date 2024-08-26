import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    
	public static Map<String, Report> m;
	public static Map<String, Set<String>> sendList;

	public static class Report {
		Set<String> froms;
		int cnt;

		public Report() {
			froms = new HashSet<String>();
		}

		public void set(String from) {
			if (froms.contains(from))
				return;

			froms.add(from);
			cnt++;
		}
	}

    
    public int[] solution(String[] id_list, String[] report, int k) {
		m = new HashMap<String, Report>();
		sendList = new HashMap<String, Set<String>>();

		for (String id : id_list) {
			m.put(id, new Report());
			sendList.put(id, new HashSet<String>());
		}

		for (String s : report) {
			String[] split = s.split(" ");

			Report tmp = m.get(split[1]);
			if (!tmp.froms.contains(split[0])) {
				tmp.set(split[0]);
			}
		}

		for (String id : id_list) {
			Report tmp = m.get(id);
			if (tmp.cnt >= k) {
				for (String s : tmp.froms) {
					sendList.get(s).add(id);
				}
			}
		}

		int[] answer = new int[id_list.length];
		for (int i = 0; i < id_list.length; i++) {
			answer[i] = sendList.get(id_list[i]).size();
		}

		return answer;
	}
    
}