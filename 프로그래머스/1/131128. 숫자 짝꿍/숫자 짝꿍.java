class Solution {
    public String solution(String X, String Y) {
        int a[] = new int[10];
        int b[] = new int[10];
        
        for(int i = 0; i < X.length(); i++)
            a[X.charAt(i) - '0']++;
        
        for(int i = 0; i < Y.length(); i++)
            b[Y.charAt(i) - '0']++;
        
        String answer = "";
        for(int i = 9; i > -1; i--) {
            if(a[i] > 0 && b[i] > 0) {
                int num = Math.min(a[i], b[i]);
                if(i == 0 && answer.equals(""))
                    answer += 0;
                else 
                    answer += ("" + i).repeat(num);
            }
        }
        if(answer.equals(""))
            answer += "-1";
        
        return answer;
        
        
    }
}