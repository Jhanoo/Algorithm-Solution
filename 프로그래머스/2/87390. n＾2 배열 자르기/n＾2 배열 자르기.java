class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int) (right - left) + 1;
        int[] answer = new int[size];
        int idx = 0;
        
        for (long i = left; i <= right; i++) {
            long r = i / n;
            long c = i % n;
            int num;
            
            if (c > r) 
                num = (int) c + 1;
            else
                num = (int) r + 1;
            
            answer[idx++] = num;
        }
        
        return answer;
    }
}