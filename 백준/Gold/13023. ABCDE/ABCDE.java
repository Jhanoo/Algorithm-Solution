import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static int N, M;
	public static List<Integer>[] a;
	public static boolean visited[];
	public static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		result = 0;

		a = new ArrayList[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			a[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			s = br.readLine().split(" ");
			a[Integer.parseInt(s[0])].add(Integer.parseInt(s[1]));
			a[Integer.parseInt(s[1])].add(Integer.parseInt(s[0]));
		}

		for (int i = 0; i < N; i++) {
			dfs(i, 1);
			if (result == 1)
				break;
		}
		System.out.println(result);

	}

	public static void dfs(int n, int cnt) {
		visited[n] = true;

		if (result == 1) {
			return;
		}

		if (cnt == 5) {
			result = 1;
			return;
		}

		for (int i = 0; i < a[n].size(); i++) {
			int tmp = a[n].get(i);

			if (!visited[tmp])
				dfs(tmp, cnt + 1);

		}

		visited[n] = false;
	}

}