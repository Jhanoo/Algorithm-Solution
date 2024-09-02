import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static int N, M;
	public static Map<String, Integer> codes;
	public static final String[] CODE = { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111",
			"0111011", "0110111", "0001011" };

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("input 1240.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		codes = new HashMap<String, Integer>();
		for (int i = 0; i < 10; i++) {
			codes.put(CODE[i], i);
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] s = br.readLine().split(" ");
			N = Integer.parseInt(s[0]);
			M = Integer.parseInt(s[1]);

			boolean find = false;
			for (int i = 0; i < N; i++) {
				String l = br.readLine();
				if (!find && l.contains("1")) {
					int lastIdx = l.lastIndexOf("1");
					int firstIdx = lastIdx - 55;

					String code = l.substring(firstIdx, lastIdx + 1);
					sb.append(verify(code));
					find = true;
				}
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int verify(String code) {

		int a[] = new int[8];
		for (int i = 0; i < 8; i++) {
			int startIdx = 7 * i;
			int endIdx = 7 * (i + 1);

			a[i] = codes.get(code.substring(startIdx, endIdx));
		}

		int validateCode = 0;
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			if (i % 2 == 0) {
				validateCode += 3 * a[i];
			} else {
				validateCode += a[i];
			}
			sum += a[i];
		}

		if (validateCode % 10 != 0) {
			sum = 0;
		}

		return sum;
	}

}