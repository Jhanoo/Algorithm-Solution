import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static int[] fees, plan;
	public static final int DAY = 0;
	public static final int MONTH = 1;
	public static final int MONTH3 = 2;
	public static int min;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			fees = new int[4];
			plan = new int[12];

			String[] s = br.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				fees[i] = Integer.parseInt(s[i]);
			}

			s = br.readLine().split(" ");
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(s[i]);
			}

			min = fees[3];

			calc(0, 0);
			sb.append(min);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void calc(int month, int sum) {
		if (sum >= min)
			return;

		if (month >= 12) {
			min = min < sum ? min : sum;
			return;
		}

		calc(month + 1, sum + plan[month] * fees[DAY]);
		calc(month + 1, sum + fees[MONTH]);
		calc(month + 3, sum + fees[MONTH3]);

	}

}