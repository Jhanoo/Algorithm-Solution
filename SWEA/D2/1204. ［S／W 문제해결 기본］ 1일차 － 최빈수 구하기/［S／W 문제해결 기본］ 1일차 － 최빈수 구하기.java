import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		// System.setIn(new FileInputStream("input.txt")); // 키보드 입력을 파일 입력으로 변경
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T;
		T = Integer.parseInt(in.readLine()); // 첫째줄 읽기
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			// 여러분의 알고리즘 코드 작성하기
			// 둘째줄 읽기
			int testCase = Integer.parseInt(in.readLine());

			// 셋째줄 읽기
			String line = in.readLine();
			String[] arr = line.split(" ");

//			int[] intArr = new int[arr.length];
//			for (int i = 0; i < arr.length; i++) {
//				intArr[i] = Integer.parseInt(arr[i]);
//			}

			// 읽은 내용 출력해보기
//			System.out.println(Arrays.toString(intArr));

			int[] cntArr = new int[101];

			for (int i = 0; i < arr.length; i++) {
				cntArr[Integer.parseInt(arr[i])]++;
			}

			int max = 0;
			int num = -1;
			for (int i = 0; i < 101; i++) {
				if (cntArr[i] >= max) {
					max = cntArr[i];
					num = i;
				}
			}
			sb.append(num + "\n");

		}

		System.out.println(sb);
	}
}