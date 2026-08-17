import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return b[0] - a[0];
            }
            return a[col - 1] - b[col - 1];
        });
        
        int answer = 0;
        int[] S = new int[data.length + 1];
        for (int i = row_begin; i <= row_end; i++) {
            for (int j : data[i - 1]) {
                S[i] += j % i;
            }
            answer = answer ^ S[i];
        }
        
        return answer;
    }
}