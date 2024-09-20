import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean solution(String[] phone_book) {

		// 만약 전화번호가 1개라면 false 반환
		if (phone_book.length == 1)
			return false;

		Set<String> set = new HashSet<String>();

		// 길이가 짧은 순서로 정렬
		Arrays.sort(phone_book, (o1, o2) -> {
			return o1.length() - o2.length();
		});

		boolean[] len = new boolean[21]; // 1~20 의 전화번호 길이

		for (int i = 0; i < phone_book.length; i++) {
			String tmp = phone_book[i];

			// 전화번호를 HashSet에 추가, 만약 이미 있으면 false 반환
			if (!set.add(tmp)) {
				return false;
			}

			// 정상적으로 추가됐으면 해당 길이의 문자열이 있으므로 true 처리
			len[tmp.length()] = true;

			// 1 ~ 전화번호의 길이 까지 len배열에 있는지 체크
			for (int j = 1; j < tmp.length(); j++) {

				// HashSet에 해당 길이의 전화번호가 있으면
				if (len[j]) {
					String prefix = tmp.substring(0, j); // 같은 길이 만큼 자르고

					// 만약 접두어에 해당하는 전화번호가 있으면 false 반환
					if (set.contains(prefix)) {
						return false;
					}
				}
			}
		}

		return true;
	}
}