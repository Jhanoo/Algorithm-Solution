import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int[][] flavor;
	public static int N;
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		flavor = new int[N][2];
		min = Integer.MAX_VALUE;

		int S = 1; // 신맛의 곱
		int B = 0; // 쓴맛은 합

		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			flavor[i][0] = Integer.parseInt(s[0]);
			flavor[i][1] = Integer.parseInt(s[1]);
		}

		cook(0, S, B, true);
		System.out.println(min);
	}

	public static void cook(int cnt, int S, int B, boolean isEmpty) {
		if (cnt == N) {
			if (!isEmpty) {
				min = min < Math.abs(S - B) ? min : Math.abs(S - B);
			}

			return;
		}

		cook(cnt + 1, S, B, isEmpty);
		cook(cnt + 1, S * flavor[cnt][0], B + flavor[cnt][1], false);
	}

}