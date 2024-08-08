import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] s = br.readLine().split(" ");

			int N = Integer.parseInt(s[0]); // 10 <= N <= 100
			Stack<Character> stack = new Stack<Character>();

			stack.push(s[1].charAt(0));
			for (int i = 1; i < N; i++) {
				if (!stack.empty()) {
					if (stack.peek() == s[1].charAt(i)) {
						stack.pop();
						continue;
					}
				}
				stack.push(s[1].charAt(i));
			}

			String password = "";
			while (!stack.isEmpty()) {
				password = stack.pop() + password;
			}
			sb.append(password).append("\n");
		}

		System.out.println(sb);
	}
}