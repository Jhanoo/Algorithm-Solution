import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static int[][] a;
	public static int xpos;
	public static int ypos;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(br.readLine());

			a = new int[100][100];
			for (int i = 0; i < 100; i++) {
				String[] s = br.readLine().split(" ");

				for (int j = 0; j < 100; j++) {
					a[i][j] = Integer.parseInt(s[j]);
					if (a[i][j] == 2) {
						xpos = j;
						ypos = i;
					}
				}
			}

			goUp();
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void goUp() {
		ypos--;
		if (ypos == 0) {
			sb.append(xpos);
			return;
		}

		if (xpos == 0) {
			if (a[ypos][xpos + 1] == 1) {
				goRight();
			} else {
				goUp();
			}
		} else if (xpos == 99) {
			if (a[ypos][xpos - 1] == 1) {
				goLeft();
			} else {
				goUp();
			}
		} else {
			if (a[ypos][xpos + 1] == 1) {
				goRight();
			} else if (a[ypos][xpos - 1] == 1) {
				goLeft();
			} else {
				goUp();
			}

		}

	}

	public static void goLeft() {
		xpos--;
		if (xpos == 0) {
			goUp();
			return;
		}

		if (a[ypos][xpos - 1] == 1) {
			goLeft();
		} else {
			goUp();
		}
	}

	public static void goRight() {
		xpos++;
		if (xpos == 99) {
			goUp();
			return;
		}

		if (a[ypos][xpos + 1] == 1) {
			goRight();
		} else {
			goUp();
		}
	}

}