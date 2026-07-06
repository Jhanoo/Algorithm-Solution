import java.util.*;

class Solution {
    
    static Set<Integer> set = new HashSet<>();
    static int N;
    
    public int solution(String numbers) {
        N = numbers.length();
        
        makeNumber(numbers, "", 0);
        
        int answer = 0;
        for (Integer i : set) {
            if (isPrime(i))
                answer++;
        }
        
        return answer;
    }
    
    void makeNumber(String numbers, String num, int bitmask) {
        if (((bitmask + 1) & (1 << N)) != 0) 
            return;
        
        for (int i = 0; i < N; i++) {
            if ((bitmask & (1 << i)) != 0)
                continue;
            
            String newNum = num + numbers.charAt(i);
            set.add(Integer.parseInt(newNum));
            
            makeNumber(numbers, newNum, bitmask + (1 << i));
        }
    }
    
    boolean isPrime(int n) {
        if (n <= 1) 
            return false;
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) 
                return false;
        }
        
        return true;
    }
}