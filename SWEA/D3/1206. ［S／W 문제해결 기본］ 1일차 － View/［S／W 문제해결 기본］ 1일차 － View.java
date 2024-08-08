import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		// System.setIn(new FileInputStream("sample_input.txt")); // 키보드 입력을 파일 입력으로 변경
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = 10;
//		T = Integer.parseInt(in.readLine()); // 첫째줄 읽기
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(in.readLine());

			String line = in.readLine();
			String[] arr = line.split(" ");

			int[] h = new int[N];

			for (int i = 0; i < N; i++) {
				h[i] = Integer.parseInt(arr[i]);
			}

			int cnt = 0;

			for (int i = 2; i < N - 2; i++) {
				int left = Math.max(h[i - 2], h[i - 1]);
				int right = Math.max(h[i + 1], h[i + 2]);
				int max = Math.max(left, right);

				cnt += h[i] - max > 0 ? h[i] - max : 0;
			}

			sb.append(cnt + "\n");
		}

		System.out.println(sb);
	}
}