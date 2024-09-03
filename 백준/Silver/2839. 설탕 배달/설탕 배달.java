import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Integer> three = new ArrayList<>();
		List<Integer> five = new ArrayList<>();

		for (int i = 0; i <= N; i += 3) {
			three.add(i);
		}
		for (int i = 0; i <= N; i += 5) {
			five.add(i);
		}

		int len3 = three.size();
		int len5 = five.size();
		int i = 0;
		int j = len5 - 1;
		int min = 5000;
		while (i < len3 && j >= 0) {
			int sum = three.get(i) + five.get(j);

			if (sum == N) {
				min = min < i + j ? min : i + j;
				i++;
			} else if (sum < N) {
				i++;
			} else {
				j--;
			}
		}

		if (min == 5000)
			min = -1;

		System.out.println(min);
	}

}