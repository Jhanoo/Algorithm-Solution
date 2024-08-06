import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int now = 0;
        int idx = 0;
        int cnt = 0;
        int answer = 0;
        
        while(cnt < jobs.length) {
            
            while(idx < jobs.length && jobs[idx][0] <= now){
                pq.add(jobs[idx++]);
            }
            
            if(pq.isEmpty()) {
                now = jobs[idx][0];
            }
            else {
                int tmp[] = pq.poll();
                answer += now + tmp[1] - tmp[0];
                now += tmp[1];
                cnt++;
            }
            
        }
        
        answer /= jobs.length;
        
        
        return answer;
    }
}