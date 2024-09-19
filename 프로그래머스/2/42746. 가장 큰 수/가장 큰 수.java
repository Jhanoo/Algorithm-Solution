import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
		int n = numbers.length;
		String[] a = new String[n];

		for (int i = 0; i < n; i++) {
			a[i] = "" + numbers[i];
		}

		Arrays.sort(a, (o1, o2) -> {
			return (o2 + o1).compareTo(o1 + o2);
		});

		String answer = "";
		for (int i = 0; i < n; i++) {
			answer += a[i];
		}

		if (answer.charAt(0) == '0')
			return "0";

		return answer;
	}
}