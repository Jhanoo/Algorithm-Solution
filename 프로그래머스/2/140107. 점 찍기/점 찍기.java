class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for (int i = 0; i * k <= d; i++) {
            long x = (long) i * k;
            
            // y^2 = d^2 - x^2
            long maxY = (long) Math.sqrt((long) d * d - x * x);
            
            answer += maxY / (long) k + 1;
        }
        
        return answer;
    }
}