import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 14510.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(br.readLine());
			int a[] = new int[N];
			int max = 1;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				max = max > a[i] ? max : a[i];
			}

			int cntOne = 0;
			int cntTwo = 0;

			for (int i = 0; i < N; i++) {
				a[i] = max - a[i];
				cntOne += a[i] % 2;
				cntTwo += a[i] / 2;
			}

			if (cntOne + 2 <= cntTwo) {
				int mok = (cntTwo - cntOne) / 3;
				int nameoge = (cntTwo - cntOne) % 3 > 1 ? 1 : 0;

				cntOne += (mok + nameoge) * 2;
				cntTwo -= mok + nameoge;
			}

			int result = 0;
			if (cntOne > cntTwo) {
				result = cntOne * 2 - 1;
			} else if (cntOne == cntTwo) {
				result = cntOne * 2;
			} else {
				result = cntTwo * 2;
			}

			sb.append(result);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}