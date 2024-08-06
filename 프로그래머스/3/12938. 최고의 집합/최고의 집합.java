class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        if(n > s)
            return new int[] { -1 };
        
        // 3, 7 (1,1,5) (1,2,4) (1,3,3) (2,2,3)
        int mok = s / n;
        int nameoge = s % n;
        
        answer = new int[n];
        for(int i = 0; i < n; i++){
            answer[i] = mok;
        }
        
        for(int i = n-1; i > n - 1 - nameoge; i--){
            answer[i]++;
        }
        
        return answer;
    }
}