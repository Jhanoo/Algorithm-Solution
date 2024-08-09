import java.util.Stack;

class Solution {
    boolean solution(String s) {
		boolean answer = true;

		if (s.charAt(0) == ')')
			return false;

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char t = s.charAt(i);

			if (t == '(') {
				stack.push(t);
			}
			if (t == ')') {
				if (!stack.isEmpty()) {
					if (stack.pop() == t) {
						answer = false;
					}
				} else {
					answer = false;
				}
			}
		}

		if (!stack.isEmpty())
			answer = false;

		return answer;
	}
}