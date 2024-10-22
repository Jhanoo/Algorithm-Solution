import java.util.Stack;

class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

		Stack<Integer> dStack = new Stack<>(); // deliveries 스택
		Stack<Integer> pStack = new Stack<>(); // pickups 스택

		// Stack의 Top: 가장 먼 집
		for (int i = 0; i < n; i++) {
			dStack.push(deliveries[i]);
			pStack.push(pickups[i]);
		}

		// 만약 Top의 배달 및 수거가 끝났으면(0 이면) 스택에서 제거(pop)한 뒤 스택 크기
		int dSize = removeZeroOnTop(dStack);
		int pSize = removeZeroOnTop(pStack);

		long answer = 0;

		// 배달 및 수거가 필요한 집이 있으면
		while (dSize > 0 || pSize > 0) {
			answer += 2 * Math.max(dSize, pSize); // 가장 먼 집의 거리 * 2 (왕복)
			
			int tmp = 0; // 현재 배달해야 할 택배 상자 수
			if (dSize > 0) { // 배달해야 할 집이 있으면
				while (!dStack.isEmpty() && tmp <= cap) { // 배달해야 할 집이 있고, 현재 배달해야 할 상자 수가 cap보다 작으면
					tmp += dStack.pop(); // Top(가장 먼 집)부터 배달해야 할 상자 개수 더하기
				}
				if (tmp > cap) { // 배달해야 할 상자가 실을 수 있는 상자 개수보다 많으면
					dStack.push(tmp - cap); // 싣지 못하므로 남은 개수 다시 넣기
				}
			}

			tmp = 0; // 현재 수거한 택배 상자 수
			if (pSize > 0) { // 수거해야 할 집이 있으면
				while (!pStack.isEmpty() && tmp <= cap) { // 수거해야 할 집이 있고, 현재 수거한 상자 수가 cap보다 작으면
					tmp += pStack.pop(); // Top(가장 먼 집)부터 택배 수거하기
				}
				if (tmp > cap) { // 수거해야 할 상자가 실을 수 있는 상자 수보다 많으면
					pStack.push(tmp - cap); // 싣지 못하는 상자 다시 넣기
				}
			}

			dSize = dStack.size(); // 가장 먼 집 갱신
			pSize = pStack.size(); // 가장 먼 집 갱신
		}

		return answer;
	}

	// 가장 먼 집에 택배 배달 및 수거해야 할 상자 개수가 0일 경우 스택에서 빼고 사이즈 리턴(가장 먼 집)
	public static int removeZeroOnTop(Stack<Integer> stack) {
		while (!stack.isEmpty() && (stack.peek() == 0)) {
			stack.pop();
		}

		return stack.size();
	}
}