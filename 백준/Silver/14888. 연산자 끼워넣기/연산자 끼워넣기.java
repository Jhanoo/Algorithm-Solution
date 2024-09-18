import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, max, min;
	public static int[] a;
	public static int[] opNum; // + - * /

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		a = new int[N];
		opNum = new int[4];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			opNum[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, a[0]);
		System.out.println(max);
		System.out.println(min);
	}

	public static void dfs(int cnt, int result) {

		if (cnt == N - 1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}

		for (int j = 0; j < 4; j++) {

			if (opNum[j] > 0) {
				opNum[j]--;
				dfs(cnt + 1, calc(j, result, a[cnt + 1]));
				opNum[j]++;
			}
		}

	}

	public static int calc(int op, int a, int b) {
		if (op == 0)
			return a + b;
		if (op == 1)
			return a - b;
		if (op == 2)
			return a * b;
		if (op == 3)
			return a / b;

		return -1;
	}

}