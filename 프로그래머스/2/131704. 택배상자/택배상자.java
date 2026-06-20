import java.util.*;

class Solution {
    public int solution(int[] order) {
        ArrayDeque<Integer> belt = new ArrayDeque<>(order.length);
        
        for (int i = 1; i < order[0]; i++) {
            belt.offerFirst(i);
        }
        
        int next = order[0] + 1;
        int idx = 1;
        int cnt = 1;
        
        while (idx < order.length) {
            int cur = order[idx];
            
            if (!belt.isEmpty() && cur == belt.peek()) {
                belt.poll();

                cnt++;
            }
            else if (cur == next) {
                cnt++;
                next++;
            }
            else if (cur > next) {
                belt.offerFirst(next++);
                continue;
            }
            else {
                break;
            }
            
            idx++;
        }
        
        return cnt;
    }
}