import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public String solution(String[] participant, String[] completion) {
		
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		
		for(String s : participant){
			Integer oldVal = m.putIfAbsent(s, 1);
			if(oldVal != null) {
				m.put(s, oldVal+1);
			}
		}

		for(String s : completion) {
			int t = m.get(s);
			if(t == 1) {
				m.remove(s);
			}
			else {
				m.replace(s, t-1);
			}
		}
		
		Iterator<String> it = m.keySet().iterator();
		
		String answer = it.next();
		return answer;
	}
}