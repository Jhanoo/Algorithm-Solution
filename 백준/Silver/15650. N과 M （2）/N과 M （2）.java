import java.util.Scanner;

public class Main {

	public static StringBuilder sb = new StringBuilder();

	public static void backTracking(int n, int m, boolean check[], int cnt, String s) {
		if (n == 0)
			return;

		if (cnt == m) {
			sb.append(s + "\n");
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (!check[i] && cnt < m) {
				if (cnt == 0) {
					String s2 = s;
					s += i;
					check[i] = true;
					backTracking(n, m, check, cnt + 1, s);
					check[i] = false;
					s = s2;	
				}
				else {
					if (Integer.parseInt("" + s.charAt(s.length() - 1)) < i) {
						String s2 = s;
						
						s += " " + i;
						check[i] = true;
						backTracking(n, m, check, cnt + 1, s);
						check[i] = false;
						s = s2;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		boolean check[] = new boolean[n + 1];
		String s = "";

		backTracking(n, m, check, 0, s);
		System.out.println(sb);
	}

}