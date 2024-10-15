class Solution {
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int start = 1; // 전파가 닿지 않는 가장 왼쪽 아파트
        int range = 2 * w + 1; // 전파 전달 범위
        
        for (int station : stations) {
            int end = station - w - 1; // 전파가 닿지 않는 가장 오른쪽 아파트
            
            if (start > end) {
                start = station + w + 1;
                continue;
            }
            
            int size = end - start + 1; // 전파가 닿지 않는 아파트 수
            answer += size / range; // 설치해야 할 기지국 수
            
            // 나머지가 있으면 기지국 겹쳐서 세우기
            if (size % range > 0) {
                answer += 1;
            }
            
            start = station + w + 1;
        }
        
        if (start <= n) {
            answer += (n - start + 1) / range;
            
            if((n - start + 1) % range > 0)
                answer += 1;
        }
        

        return answer;
    }
    
}