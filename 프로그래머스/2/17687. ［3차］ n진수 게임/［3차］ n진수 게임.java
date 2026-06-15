import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        while (sb.length() < t * m) {
            sb.append(Integer.toString(num, n).toUpperCase());
            num++;
        }
        
        String answer = "";
        for (int i = p - 1; answer.length() < t; i += m) {
            answer += sb.charAt(i);
        }
        
        return answer;
    }
    
}