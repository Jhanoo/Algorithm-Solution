import java.util.Scanner;

public class Main {

	public static class BackTracking {
		StringBuilder sb;
		int t[];
		int n, m, v, cnt = 0;

		public BackTracking(int n, int m, int t[], int v, int cnt, StringBuilder sb) {
			this.n = n;
			this.m = m;
			this.t = new int[n + 1];
			for (int i = 0; i < n; i++)
				this.t[i + 1] = t[i + 1];
			this.v = v;
			this.t[v] = 1;
			this.cnt = cnt;
			this.sb = new StringBuilder();
			this.sb.append(sb);
		}

		public void tracing() {
			int check = 0;
			if (cnt == 0) {
				sb.append(v);
			}
			int i = 0;
			for (i = 1; i <= n && cnt < m; i++) {

				if (t[i] == 1)
					continue;
				if (cnt > 0 && check == 0) {
					sb.append(" " + v);
					check = 1;
				}
				cnt++;

				if (cnt == m) {
					System.out.println(sb);
					break;
				}

				BackTracking child = new BackTracking(n, m, t, i, cnt, sb);
				child.tracing();
				if (cnt != m)
					cnt--;
			}
			if (i == n+1) {
				if (cnt > 0 && check == 0) {
					sb.append(" " + v);
					check = 1;
				}
				cnt++;

				if (cnt == m) {
					System.out.println(sb);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.close();

		int t[] = new int[n + 1];
		BackTracking bt;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			bt = new BackTracking(n, m, t, i, 0, sb);
			bt.tracing();
		}
	}

}
