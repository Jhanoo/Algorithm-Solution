import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];

		int[] cnt = new int[N + 2];
		int n = stages.length; // 사람 수

		for (int stage : stages) {
			cnt[stage]++;
		}

		double[][] failure = new double[N][2];

		for (int i = 1; i < cnt.length - 1; i++) {

			failure[i - 1][0] = i;
			if (n <= 0)
				continue;
			failure[i - 1][1] = (double) cnt[i] / n;
			n -= cnt[i];
		}

		for (double[] d : failure) {
			System.out.println(Arrays.toString(d));
		}

		Arrays.sort(failure, new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				if (o1[1] == o2[1]) {
					return (int) (o1[0] - o2[0]);
				}

				return Double.compare(o2[1], o1[1]);
			}
		});


		for (int i = 0; i < N; i++) {
			answer[i] = (int) failure[i][0];
		}

		return answer;
	}
}