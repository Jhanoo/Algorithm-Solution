import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
		List<Integer> result = new ArrayList<>();
        
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				result.remove(new Integer(numbers[i] + numbers[j]));
				result.add(numbers[i] + numbers[j]);
			}
		}
		result.sort((o1, o2) -> o1 - o2);

		int[] answer = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}

		return answer;
	}
}