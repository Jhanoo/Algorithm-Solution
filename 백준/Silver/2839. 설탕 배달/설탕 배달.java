import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		boolean isDivided = false;
		int i, min = 2000;

		for (i = n / 5; i >= 0; i--) {
			if ((n - i * 5) % 3 == 0) {
				int x = i;
				int y = (n - i * 5) / 3;
				if (min > x + y)
					min = x + y;
				isDivided = true;
			}
		}

		if (isDivided) {
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	}

}