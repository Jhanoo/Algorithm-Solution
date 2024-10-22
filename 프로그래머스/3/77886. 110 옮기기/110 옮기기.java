import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String[] solution(String[] s) {
		List<String> movedList = new ArrayList<>();

		for (String x : s) {
			char[] charStack = new char[x.length()];
			int cnt = 0; // 110의 개수
			int idx = 0; // 문자열의 인덱스
			int size = 0; // stack의 크기

			while (true) {
				// 110인지 확인하기 위해 스택 크기가 2개 이하면 추가하고 스킵
				if (size < 3) {
					// 더 이상 넣을 수 있는 문자가 없을 때 break
					if (idx >= x.length())
						break;

					charStack[size++] = x.charAt(idx++);
					continue;
				}

				// 110이면
				if (charStack[size - 3] == '1' && charStack[size - 2] == '1' && charStack[size - 1] == '0') {
					size -= 3; // 110 스택에서 빼기
					cnt++;
				}

				// 더 이상 넣을 수 있는 문자가 없을 때 break
				if (idx >= x.length())
					break;

				charStack[size++] = x.charAt(idx++);
			}

			String tmp = new String(Arrays.copyOf(charStack, size));
			
			idx = tmp.lastIndexOf("0"); // x에서 가장 뒤에 있는 0의 인덱스

			StringBuilder left = new StringBuilder();
			StringBuilder right = new StringBuilder();

			if (idx == -1) { // 0이 없는 경우
				right.append(tmp);
			} else if (idx == tmp.length() - 1) { // 0이 맨 끝에 있는 경우
				left.append(tmp);
			} else { // 0이 중간에 있는 경우
				left.append(tmp.substring(0, idx + 1));
				right.append(tmp.substring(idx + 1));
			}

			for (int i = 0; i < cnt; i++) {
				left.append("110"); // left와 right 사이에 끼워 넣기
			}

			movedList.add(left.toString() + right.toString());
		}

		return movedList.toArray(new String[] {});
	}
}