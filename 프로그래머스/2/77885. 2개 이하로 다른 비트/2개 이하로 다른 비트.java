import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        int n = numbers.length;
        long[] answer = new long[n];
        
        for (int i = 0; i < n; i++) {
            String bin = Long.toBinaryString(numbers[i]);
            int size = bin.length();
            int lastZeroIdx = bin.lastIndexOf("0");
            
            if (lastZeroIdx == size - 1) {
                bin = bin.substring(0, size - 1) + "1";
            }
            else if (lastZeroIdx == -1) {
                bin = "10" + bin.substring(1, size);
            }
            else if (lastZeroIdx == size - 2) {
                bin = bin.substring(0, lastZeroIdx) + "10";
            }
            else {
                bin = bin.substring(0, lastZeroIdx) + "10" + bin.substring(lastZeroIdx + 2, size);
            }
            
            answer[i] = Long.parseLong(bin, 2);
        }
        
        return answer;
    }
}