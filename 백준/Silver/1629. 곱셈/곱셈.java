import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int A, B, C;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		System.out.println(calc(B));
	}

	public static long calc(int div) {

		if (div == 1)
			return A % C;

		long k = calc(div / 2) % C;

		if (div % 2 == 0)
			return k * k % C;

		else
			return k * k % C * A % C;
	}

}