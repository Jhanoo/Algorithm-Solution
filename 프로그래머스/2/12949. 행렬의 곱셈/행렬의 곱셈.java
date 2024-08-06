class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr2[0].length];

		for (int col = 0; col < arr1.length; col++) {
			for (int i = 0; i < arr2[0].length; i++) {
				int sum = 0;

				for (int row = 0; row < arr1[col].length; row++) {
					sum += arr1[col][row] * arr2[row][i];
				}
				answer[col][i] = sum;
			}
		}

		return answer;
	}
}