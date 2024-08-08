import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1209.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(br.readLine());

			int[][] a = new int[100][100];

			for (int i = 0; i < 100; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < 100; j++) {
					a[i][j] = Integer.parseInt(s[j]);
				}
			}

			int sum = 0;
			int max = 0;
			// 각 행의 합
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					sum += a[i][j];
				}
				max = max > sum ? max : sum;
				sum = 0;
			}
			// 각 열의 합
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					sum += a[j][i];
				}
				max = max > sum ? max : sum;
				sum = 0;
			}
			// 대각선의 합
			for (int i = 0; i < 100; i++) {
				sum += a[i][i];
			}
			max = max > sum ? max : sum;
			sum = 0;

			for (int i = 0; i < 100; i++) {
				sum += a[99 - i][i];
			}
			max = max > sum ? max : sum;

			sb.append(max + "\n");

		}

		System.out.println(sb);
	}
}