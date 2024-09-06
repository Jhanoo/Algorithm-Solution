import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] T = br.readLine().toCharArray(); // 텍스트(본문)
		char[] P = br.readLine().toCharArray(); // 패턴

		int tLength = T.length;
		int pLength = P.length;

		// 1단계: 부분 일치 테이블 만들기
		int[] pi = new int[pLength];

		// i: 접미사 포인터(i는 1부터 시작)
		// j: 접두사 포인터
		for (int i = 1, j = 0; i < pLength; i++) {
			while (j > 0 && P[i] != P[j]) {
				// j의 이전위치 j - 1 까지는 일치했으므로 일치했던 곳으로 이동
				j = pi[j - 1];
			}

			// 접미사와 접두사가 같은 경우, 그 길이를 부분일치 테이블에 기록
			if (P[i] == P[j]) {
				pi[i] = ++j;
			} else {
				pi[i] = 0;
			}
		}

		// 2단계: 부분일치 테이블을 활용해서 텍스트(본문)과 패턴 비교
		int cnt = 0; // 패턴과 일치하는 부분의 개수
		List<Integer> list = new ArrayList<>(); // 찾은 부분의 첫 인덱스 번호 저장

		// i: 접미사 포인터(i는 1부터 시작)
		// j: 접두사 포인터
		for (int i = 0, j = 0; i < tLength; i++) {

			// 본문과 패턴이 불일치하면
			while (j > 0 && T[i] != P[j]) {
				// j의 이전위치 j - 1 까지는 일치했으므로 일치했던 곳으로 이동
				j = pi[j - 1];
			}

			// 두 글자가 일치
			if (T[i] == P[j]) {

				// j가 패턴의 마지막 인덱스라면 패턴과 일치하는 단어 찾기 완료
				if (j == pLength - 1) {
					cnt++;
					list.add(i - (pLength - 1)); // 찾은 단어의 첫 인덱스 번호 저장

					j = pi[j];
				} else {
					j++; // i와 함께 j도 증가
				}
			}
		}

		System.out.println(cnt);

		for (int i = 0; i < cnt; i++) {
			System.out.print((list.get(i) + 1) + " ");
		}

	}
}