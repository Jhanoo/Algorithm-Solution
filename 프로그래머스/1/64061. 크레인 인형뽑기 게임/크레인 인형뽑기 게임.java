import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {

		int N = board.length;
		Stack<Integer>[] stack = new Stack[N + 1];

		for (int i = 0; i < N; i++) {
			stack[i + 1] = new Stack<Integer>(); // 각 열에 해당하는 스택

			for (int j = N - 1; j >= 0; j--) {

				if (board[j][i] != 0) {
					stack[i + 1].push(board[j][i]);
				}
			}
		}

		stack[0] = new Stack<Integer>(); // 뽑은 인형이 들어갈 스택

		System.out.println(Arrays.toString(stack));
		int answer = 0;

		for (int move : moves) {
			if (!stack[move].isEmpty()) {
				int tmp = stack[move].pop();

				if (!stack[0].isEmpty()) {

					if (stack[0].peek() == tmp) {
						stack[0].pop();
						answer += 2;
						System.out.println();
						continue;
					}
				}
				stack[0].push(tmp);

			}
		}

		return answer;
	}
}