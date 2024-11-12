import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        
        Set<Integer> win = new HashSet<>();
        
        for (int num : win_nums) {
            win.add(num);
        }
        
        int cnt = 0;
        int zeroCnt = 0;
        for (int num : lottos) {
            if (num == 0) {
                zeroCnt++;
            }
            else if (win.contains(num)) {
                cnt++;
            }
        }
        
        int max = cnt + zeroCnt == 0 ? 6 : 7 - cnt - zeroCnt;
        int min = cnt == 0 ? 6 : 7 - cnt;
        
        return new int[] { max, min };
    }
}