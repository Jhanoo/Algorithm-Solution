import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    public static class Genre {
		public int totalPlays;
		public int bestId;
		public int secondId;

		public Genre(int id, int plays) {
			totalPlays = plays;
			bestId = id;
			secondId = -1;
		}

		public void set(int[] plays, int id) {
			totalPlays += plays[id];

			if (plays[bestId] < plays[id]) {
				secondId = bestId;
				bestId = id;
			} else if (secondId == -1) {
				secondId = id;
			} else if (secondId != -1 && plays[secondId] <= plays[id]) {
				secondId = id;
			}
		}

	}

	public int[] solution(String[] genres, int[] plays) {

		HashMap<String, Genre> m = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {

			Genre tmp = m.putIfAbsent(genres[i], new Genre(i, plays[i]));

			if (tmp != null) {
				tmp.set(plays, i);
				m.replace(genres[i], tmp);
			}
		}

		List<String> keySet = new ArrayList<String>(m.keySet());

		keySet.sort((o1, o2) -> m.get(o2).totalPlays - m.get(o1).totalPlays);

		List<Integer> result = new ArrayList<Integer>();
		for (String key : keySet) {
			result.add(m.get(key).bestId);
			if (m.get(key).secondId != -1)
				result.add(m.get(key).secondId);
		}
		int[] answer = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}

		return answer;
	}
}