import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution
{
    public int solution(String s) {

		int answer = 0;

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char tmp = s.charAt(i);

			if (!stack.isEmpty()) {
				char top = stack.peek();
				if (top == tmp) {
					stack.pop();
					continue;
				}
			}

			stack.push(tmp);
		}

		if (stack.isEmpty()) {
			answer = 1;
		}

		return answer;
	}
}