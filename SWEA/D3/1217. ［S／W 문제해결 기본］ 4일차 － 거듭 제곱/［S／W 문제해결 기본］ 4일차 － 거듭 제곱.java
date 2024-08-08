import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sc.nextInt();

			int N = sc.nextInt();
			int M = sc.nextInt();

			System.out.println("#" + test_case + " " + pow(N, M));
		}

		sc.close();
	}

	public static int pow(int n, int multiplier) {
		if (multiplier == 0)
			return 1;

		return n * pow(n, multiplier - 1);
	}

}