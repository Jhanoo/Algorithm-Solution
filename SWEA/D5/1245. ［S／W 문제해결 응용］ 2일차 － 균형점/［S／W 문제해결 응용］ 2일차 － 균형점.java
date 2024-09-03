import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static double xpos[];
	public static double m[];
	public static int N;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("input 1245.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			xpos = new double[N];
			m = new double[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				xpos[i] = Double.parseDouble(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				m[i] = Double.parseDouble(st.nextToken());
			}

			for (int cnt = 0; cnt < N - 1; cnt++) {
				double left = xpos[cnt];
				double right = xpos[cnt + 1];

				double tmpCenter = (left + right) / 2;
				double leftForce = getLeftForce(tmpCenter);
				double rightForce = getRightForce(tmpCenter);

				while (right - left >= 1e-12) {
					if (leftForce == rightForce) {
						break;
					}

					if (leftForce > rightForce) {
						left = tmpCenter;
						tmpCenter = (tmpCenter + right) / 2;
					} else {
						right = tmpCenter;
						tmpCenter = (left + tmpCenter) / 2;
					}

					leftForce = getLeftForce(tmpCenter);
					rightForce = getRightForce(tmpCenter);
				}

				sb.append(String.format("%.10f", tmpCenter) + " ");
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static double getLeftForce(double x) {
		double force = 0;
		for (int i = 0; xpos[i] < x; i++) {
			double dSquare = (xpos[i] - x) * (xpos[i] - x);
			double tmpM = m[i];
			force += tmpM / dSquare;
		}

		return force;
	}

	public static double getRightForce(double x) {
		double force = 0;
		for (int i = N - 1; xpos[i] > x; i--) {
			double dSquare = (xpos[i] - x) * (xpos[i] - x);
			double tmpM = m[i];
			force += tmpM / dSquare;
		}

		return force;
	}
}