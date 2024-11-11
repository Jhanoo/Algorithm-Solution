import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int i = 0;
        int j = 0;
        while (j < B.length) {
            if (A[i] < B[j]) {
                answer++;
                i++;
            }
            j++;
        }
        
        return answer;
    }
}