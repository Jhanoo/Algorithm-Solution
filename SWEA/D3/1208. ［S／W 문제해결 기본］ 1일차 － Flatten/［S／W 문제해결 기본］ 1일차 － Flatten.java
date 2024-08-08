import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input (1).txt")); // 키보드 입력을 파일 입력으로 변경
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = 10;
//		T = Integer.parseInt(in.readLine()); // 첫째줄 읽기
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int n = Integer.parseInt(in.readLine());

			String line = in.readLine();
			String[] arr = line.split(" ");

			int[] h = new int[100];

			for (int i = 0; i < 100; i++) {
				h[i] = Integer.parseInt(arr[i]);
			}

			Arrays.sort(h);

			for (int i = 0; i < n; i++) {
				int r = 99;
				int max = h[r];
				while (h[r - 1] == max) {
					r--;
					if (r == 0) {
						break;
					}
				}
				h[r]--;

				int l = 0;
				int min = h[l];
				while (h[l + 1] == min) {
					l++;
					if (l == 99) {
						break;
					}
				}
				h[l]++;
			}

			int diff = h[99] - h[0];

			sb.append(diff + "\n");
		}

		System.out.println(sb);
	}
}