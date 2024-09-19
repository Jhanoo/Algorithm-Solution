class Solution {
    public long solution(long n) {

		int num[] = new int[10];
		for (long i = n; i > 0; i /= 10) {
			num[(int) (i % 10)]++;
		}

		String s = "";
		for (int i = 9; i >= 0; i--) {
			while (num[i] > 0) {
				s += i;
				num[i]--;
			}
		}

		return Long.parseLong(s);
	}
}