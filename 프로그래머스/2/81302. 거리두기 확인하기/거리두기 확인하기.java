class Solution {
    // 대기실 5x5
    // 맨해튼 거리 2이하 x (파티션 허용)
    // P(앉음), O(빈테이블), X(파티션)
    public int[] solution(String[][] places) {
        int[] answer = { 1, 1, 1, 1, 1 }; // 1로 초기화
        
        for (int k = 0; k < 5; k++) {
            answer[k] = checkPlace(places[k]); // 앉을 수 없으면 0으로 갱신
        }
        
        return answer;
    }
    
    public static int checkPlace(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // 현재 위치에 앉을 수 있는 지
                if (place[i].charAt(j) == 'P') {
                    if (!canSit(place, j, i)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    public static boolean canSit(String[] place, int x, int y) {
        // 오른쪽, 아래, 왼쪽 탐색
        int[][] d = { { 1, 0 }, { 2, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 0, 2 } };
        
        for (int i = 0; i < 6; i++) {
            int rx = x + d[i][0];
            int ry = y + d[i][1];
            
            // 범위 밖으로 나가면 스킵
            if (rx < 0 || rx >= 5 || ry < 0 || ry >= 5)
                continue;
            
            if (place[ry].charAt(rx) == 'P') {
                if (x == rx) {
                    if (place[ry - 1].charAt(rx) == 'X') {
                        continue;
                    } else {
                        return false;
                    }
                } else if (y == ry) {
                    if (place[ry].charAt(rx - 1) == 'X') {
                        continue;
                    } else {
                        System.out.println(ry + " " + rx);
                        return false;
                    }
                } else if (rx > x) {
                    if (place[ry].charAt(rx - 1) == 'X' && place[ry - 1].charAt(rx) == 'X')
                        continue;
                    else {
                        return false;
                    }
                } else if (rx < x) {
                    if (place[ry].charAt(rx + 1) == 'X' && place[ry - 1].charAt(rx) == 'X')
                        continue;
                    else {
                        return false;
                    }
                }
            }
        }
         
        return true;
    }
}