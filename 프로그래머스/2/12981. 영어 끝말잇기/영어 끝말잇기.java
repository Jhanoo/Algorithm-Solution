import java.util.HashSet;
import java.util.Set;

class Solution {
    
    public int[] solution(int n, String[] words) {

		Set<String> s = new HashSet<>();

		int dupIdx = -1;
		int turn = -1;
		s.add(words[0]);
		for (int i = 1; i < words.length; i++) {

			if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0) || !s.add(words[i])) {
				dupIdx = i % n;
				turn = i / n;
				break;
			}
		}

		return new int[] { dupIdx + 1, turn + 1 };
	}
    
}