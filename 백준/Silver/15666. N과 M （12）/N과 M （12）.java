import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static List<Integer> a;
	public static Set<Integer> set;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		set = new HashSet<Integer>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}

		a = new ArrayList<>(set);
		a.sort((o1, o2) -> {
			return o1 - o2;
		});

		for (int i = 0; i < a.size(); i++) {
			dfs(1, a.get(i), a.get(i) + " ");
		}

	}

	public static void dfs(int depth, int prev, String ans) {

		if (depth == M) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < a.size(); i++) {

			if (a.get(i) < prev)
				continue;

			dfs(depth + 1, a.get(i), ans + a.get(i) + " ");
		}

	}

}