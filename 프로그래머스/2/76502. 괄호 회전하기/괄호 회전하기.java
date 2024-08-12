import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public int solution(String s) {

		int answer = 0;
		String newStr = s;

		for (int i = 0; i < s.length(); i++) {
			answer += isRight(newStr);
			newStr = newStr.substring(1) + newStr.charAt(0);
		}

		return answer;
	}

	public int isRight(String s) {
		Stack<Character> stack = new Stack<>();

		if (s.length() == 0) {
			return 0;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == ')') {

				if (stack.isEmpty())
					return 0;

				if (stack.pop() != '(')
					return 0;

			} else if (c == '}') {

				if (stack.isEmpty())
					return 0;

				if (stack.pop() != '{')
					return 0;

			} else if (c == ']') {

				if (stack.isEmpty())
					return 0;

				if (stack.pop() != '[')
					return 0;
			} else {
				stack.push(c);
			}
		}

		if (!stack.isEmpty())
			return 0;

		return 1;
	}
}