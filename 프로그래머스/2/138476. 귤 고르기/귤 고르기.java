import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> m = new HashMap<>();
        
        for (int tan : tangerine) {
            m.put(tan, m.getOrDefault(tan, 0) + 1);
        }
        
        List<Integer> keys = new ArrayList<>(m.keySet());
        keys.sort((o1, o2) -> {
            return m.get(o2) - m.get(o1);
        });
        
        int answer = 0;
        for (int key : keys) {
            
            if (k <= 0) 
                break;
            
            k -= m.get(key);
            answer++;
        }
        
        return answer;
    }
}