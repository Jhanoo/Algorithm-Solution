import java.util.*;

class Solution {
    
    class P {
        String name;
        Map<String, Integer> receive, send;
        int giftScore;
        int giftCnt;
        
        P (String name) {
            this.name = name;
            this.receive = new HashMap<>();
            this.send = new HashMap<>();
        }
        
        void receiveGift(String from) {
            int cnt = receive.getOrDefault(from, 0);
            receive.put(from, cnt + 1);
            giftScore -= 1;
        }
        
        void sendGift(String to) {
            int cnt = send.getOrDefault(to, 0);
            send.put(to, cnt + 1);
            giftScore += 1;
        }
        
        void calcGift(P p) {
            int sendCnt = send.getOrDefault(p.name, 0);
            int receiveCnt = receive.getOrDefault(p.name, 0);
            if (sendCnt < receiveCnt) {
                p.giftCnt++;
                return;
            }
            else if (sendCnt == receiveCnt) {
                if (giftScore < p.giftScore) {
                    p.giftCnt++;
                    return;
                }
                else if (giftScore == p.giftScore)
                    return;
            }
            
            giftCnt++;
        }
    }
    
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length; // 2 ~ 50
        Map<String, P> m = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            m.put(friends[i], new P(friends[i]));
        }
        
        for (String gift : gifts) {
            String[] name = gift.split(" ");
            P from = m.get(name[0]);
            P to = m.get(name[1]);
            
            from.sendGift(to.name);
            to.receiveGift(from.name);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                P p1 = m.get(friends[i]);
                P p2 = m.get(friends[j]);
                
                p1.calcGift(p2);
            }
        }
        
        int answer = 0;
        for (P p : m.values()) {
            answer = Math.max(answer, p.giftCnt);
        }
    
        return answer;
    }
}