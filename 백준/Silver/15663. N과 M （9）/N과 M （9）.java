import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[] a;
	public static boolean[] visited;
	public static Set<String> set;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		a = new int[N];
		visited = new boolean[N];
		set = new HashSet<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(a);
		dfs(0, "");

	}

	public static void dfs(int depth, String ans) {

		if (depth == M && !set.contains(ans)) {
			System.out.println(ans);
			set.add(ans);
			return;
		}

		for (int i = 0; i < N; i++) {

			if (visited[i])
				continue;

			visited[i] = true;
			dfs(depth + 1, ans + a[i] + " ");
			visited[i] = false;
		}

	}

}