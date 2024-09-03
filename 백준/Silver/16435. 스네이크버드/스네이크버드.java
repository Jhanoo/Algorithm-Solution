import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int L = Integer.parseInt(s[1]);

		int a[] = new int[N];

		s = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(s[i]);
		}

		Arrays.sort(a);
		for (int i = 0; i < N; i++) {
			if (L >= a[i]) {
				L++;
			} else {
				break;
			}
		}

		System.out.println(L);
	}

}