import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static int N;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1224.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			String s = br.readLine();

			String postFix = changePostFix(s);
			int result = calc(postFix);

			sb.append(result + "\n");
		}

		System.out.println(sb);
	}

	public static String changePostFix(String s) {

		Stack<Character> stack = new Stack<Character>();
		String postFix = "";

		for (int i = 0; i < N; i++) {
			char tmp = s.charAt(i);
			if ('0' <= tmp && tmp <= '9') {
				postFix += tmp;

			} else if (tmp == '*' || tmp == '(') {
				stack.push(tmp);

			} else if (tmp == '+') {
				while (!stack.isEmpty()) {
					char t = stack.peek();

					if (t == '+' || t == '(') {
						stack.push(tmp);
						break;
					}

					postFix += stack.pop();
				}

			} else if (tmp == ')') {
				while (!stack.isEmpty()) {
					char t = stack.pop();

					if (t == '(') {
						break;
					}

					postFix += t;
				}

			}
		}
		while (!stack.isEmpty()) {
			postFix += stack.pop();
		}

		return postFix;
	}

	public static int calc(String s) {

		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			char tmp = s.charAt(i);

			if ('0' <= tmp && tmp <= '9') {
				stack.push(tmp - '0');
			} else if (tmp == '*') {
				int second = stack.pop();
				int first = stack.pop();

				stack.push(first * second);
			} else if (tmp == '+') {
				int second = stack.pop();
				int first = stack.pop();

				stack.push(first + second);
			}
		}

		return stack.pop();
	}

}