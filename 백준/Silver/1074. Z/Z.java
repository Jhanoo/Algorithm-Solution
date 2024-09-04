import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		int N = Integer.parseInt(s[0]);
		int r = Integer.parseInt(s[1]);
		int c = Integer.parseInt(s[2]);

		int size = powBase2(N);

		int half = size / 2;
		int ans = 0;

		while (half > 1) {
			if (r < half) {
				if (c < half) {
					// 왼쪽 위 0 <= tmp < 2^2(N-1)
					ans += 0;
				} else {
					// 오른쪽 위 2^2(N-1) <= tmp < 2^2(N-1) * 2 - 1
					ans += powBase2(2 * N - 2);
					c -= half;
				}
			} else {
				if (c < half) {
					// 왼쪽 아래 2^2(N-1) * 2 <= tmp < 2^2(N-1) * 3 - 1
					ans += powBase2(2 * N - 2) * 2;
					r -= half;
				} else {
					// 오른쪽 아래 2^2(N-1) * 3 <= tmp < 2^2(N-1) * 4 - 1
					ans += powBase2(2 * N - 2) * 3;
					r -= half;
					c -= half;
				}
			}

			half /= 2;
			N -= 1;
		}
		int[][] z = { { 0, 1 }, { 2, 3 } };
		System.out.println(ans + z[r][c]);

	}

	public static int powBase2(int cnt) {
		int n = 1;
		for (int i = 0; i < cnt; i++) {
			n *= 2;
		}
		return n;
	}

}