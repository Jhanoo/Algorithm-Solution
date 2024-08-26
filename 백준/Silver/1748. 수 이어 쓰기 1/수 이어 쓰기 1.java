import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static long nums;
	public static int[] a = { 0, 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();

		// 1~9 = 9 / 10~99 = 90*2 / 100~999 = 900*3 / 1000~9999 = 9000*4
		calc(N, N.length());

		System.out.println(nums);

	}

	public static void calc(String s, int digit) {
		nums += (Integer.parseInt(s) - a[digit - 1]) * digit;

		while (digit > 1) {
			digit--;
			nums += (a[digit] - a[digit - 1]) * digit;
		}
	}

}