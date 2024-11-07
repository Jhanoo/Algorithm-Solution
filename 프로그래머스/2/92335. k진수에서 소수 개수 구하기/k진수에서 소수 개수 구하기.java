import java.util.*;

class Solution {
    
    public int solution(int n, int k) {
        // n을 k진수 문자열로 변환 후 0을 기준으로 분리
        String[] parts = changeRadix(n, k).split("0");
        
        int answer = 0;
        for (String part : parts) {
            if (part.isEmpty()) 
                continue;
            
            long num = Long.parseLong(part); // 10진수로 변환
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }

    // 10진수를 n진수로 변환하는 함수
    static String changeRadix(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }
        return sb.reverse().toString();
    }

    // 소수 판별 함수
    static boolean isPrime(long num) {
        if (num <= 1) 
            return false;
        
        if (num == 2 || num == 3) 
            return true;
        
        if (num % 2 == 0 || num % 3 == 0) 
            return false;
        
        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        
        return true;
    }

}