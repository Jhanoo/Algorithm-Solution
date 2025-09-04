import java.util.*;

class Solution {
    
    static int len = -1;
    
    public int solution(String begin, String target, String[] words) {
        int answer = -1;
        
        len = begin.length();
        int size = words.length;
        boolean[][] edges = new boolean[size][size];
        
        // BFS - Queue<int[index, count]>
        Queue<int[]> q = new ArrayDeque<int[]>();
        
        for (int i = 0; i < size; i++) {       
            String w = words[i];
            
            // target이 words 리스트에 있는지 확인
            if (target.equals(w)) {
                answer = 0;
            }
            
            // begin에서 변환할 수 있는 word를 큐에 삽입
            if (checkConvert(begin, w)) {
                q.offer(new int[] {i, 1});
            }
            
            // 변환 가능한 word 끼리 연결
            for(int prev = 0; prev < i; prev++) {
                String w2 = words[prev];            
                
                if (checkConvert(w, w2)) {
                    edges[prev][i] = true;
                    edges[i][prev] = true;
                }
            }
        }
        
        if (answer == -1) return 0; // words 리스트에 target이 없음.
        
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int idx = t[0];
            int cnt = t[1];
            
            // target에 도달하면 cnt 반환
            if (words[idx].equals(target)) {
                answer = cnt;
                break;
            }
            
            for (int to = 0; to < size; to++) {
                // 변환 가능한 단어를 cnt 증가시킨 후 큐에 삽입
                if (edges[idx][to]) {
                    q.offer(new int[] {to, cnt + 1});
                }
            }
        }
        
        return answer;
    }
    
    static boolean checkConvert(String w1, String w2) {
        // 각 인덱스의 같은 알파벳 개수 세기
        int sameAlphabetNum = 0;
        
        for (int i = 0; i < len; i++) {
            if (w1.charAt(i) == w2.charAt(i))
                sameAlphabetNum++;
        }
        
        // 다른 알파벳이 한 개인 경우 변환 가능
        if (sameAlphabetNum == len - 1) {
            return true;
        }
        
        return false;
    }
}