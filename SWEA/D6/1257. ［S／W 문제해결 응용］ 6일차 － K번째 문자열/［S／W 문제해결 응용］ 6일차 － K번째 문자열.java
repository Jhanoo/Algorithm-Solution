import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1257.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int K = Integer.parseInt(br.readLine());

			String str = br.readLine();
			Set<String> s = new HashSet<String>();

			int len = str.length();

			for (int cnt = 1; cnt <= len; cnt++) {
				for (int i = 0; i <= len - cnt; i++) {
					s.add(str.substring(i, i + cnt));
				}
			}
			List<String> a = new ArrayList<>(s);
			a.sort((o1, o2) -> {
				return o1.compareTo(o2);
			});

			if (a.size() < K)
				sb.append("none");
			else
				sb.append(a.get(K-1));

			sb.append("\n");
		}

		System.out.println(sb);
	}

}