import java.util.Stack;

class Solution {
    
    public int[] solution(int[] prices) {

		int[] answer = new int[prices.length];
		boolean[] fixed = new boolean[prices.length];

		Stack<Integer[]> stack = new Stack<Integer[]>();

		for (int i = 0; i < prices.length; i++) {

			while (!stack.isEmpty()) {
				if (stack.peek()[1] > prices[i]) {
					Integer[] t = stack.pop();
					answer[t[0]] = i - t[0];
				} else {
					break;
				}
			}
			stack.push(new Integer[] { i, prices[i] });
		}

		while (!stack.isEmpty()) {
			Integer[] t = stack.pop();
			answer[t[0]] = prices.length - t[0] - 1;
		}

		return answer;
	}
    
}