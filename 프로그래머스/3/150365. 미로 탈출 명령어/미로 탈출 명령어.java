import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    
    public static class P {
        int x, y;
        
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static int N, M; // 격자 크기
    public static String path, suffix;
    public static int cnt;
    
    // down,left,right,up 알파벳 오름차순 순서
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        
        int dx = r - x;
        int dy = c - y;
        
        int dist = Math.abs(dx) + Math.abs(dy); // 맨하탄 거리
        
        // 맨하탄 거리보다 k가 작으면 impossible
        if (k < dist)
            return "impossible";
        // 맨하탄 거리와 k가 홀짝이 다른 경우 impossible
        if ((k - dist) % 2 == 1) {
            return "impossible";
        }
        
        path = "";
        suffix = "";
        
        P p = new P(x, y);
        cnt = (k - dist) / 2;
        
        // 아래로
        if (dx > 0) {
            p.x += dx;
            
            while (dx != 0) {
                path += "d";
                dx--;
            }
        }
        
        goDown(p); // 아래로 더 갈 수 있으면 더 가고 나중에 위로 가기
        
        // 왼쪽으로
        if (dy < 0) {
            p.y += dy;
            while (dy != 0) {
                path += "l";
                dy++;
            }
        }
        
        goLeft(p); // 왼쪽으로 더 갈 수 있으면 더 가고 나중에 오른쪽으로 가기
        
        // 오른쪽으로
        if (dy > 0) {
            goRight(p); // rlrl... 반복
            p.y += dy;
            while (dy != 0) {
                path += "r";
                dy--;
            }
        }
        
        goRight(p);
        
        path += suffix; // 쌓아온거 합치기
        
        // 위로
        if (dx < 0) {
            p.x += dx;
            while (dx != 0) {
                path += "u";
                dx++;
            }
        }
        
        return path;
    }
    
    public static void goDown(P p) {
        while (cnt > 0) {
            if (p.x + 1 <= N) {
                path += "d";
                suffix = "u" + suffix;
                p.x++;
                cnt--;
            } else {
                break;
            }
        }
    }
    
    public static void goLeft(P p) {
        while (cnt > 0) {
            if(p.y - 1 >= 1) {
                path += "l";
                suffix = "r" + suffix;
                p.y--;
                cnt--;
            } else {
                break;
            }
        }
    }
    
    public static void goRight(P p) {
        while (cnt > 0) {
            if(p.y + 1 <= M) {
                path += "rl";
                cnt -= 1;
            } else {
                break;
            }
        }
    }
}