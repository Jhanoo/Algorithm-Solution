import java.util.List;
import java.util.ArrayList;

class Solution {
    
    public static class Date {
        int yyyymmdd;
        
        public Date(String date) {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));
            
            // privacy의 경우
            if (date.length() > 10) {
                int term = date.charAt(11) - 'A';
                month += termArr[term];
                
                if (day == 1) {
                    day = 28;
                    month--;
                } else {
                    day--;
                }
                
                year += (month - 1) / 12;
                month = month % 12 == 0 ? 12 : month % 12;
            }
            
            yyyymmdd = year * 10000 + month * 100 + day;
        }
    }
    
    public static int[] termArr;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> a = new ArrayList<>(); // 파기해야 할 개인정보 인덱스 리스트
        termArr = new int[26]; // term의 값 배열 (인덱스: "A"=0 ~ "Z"=25) 
        
        // termArr에 보관할 수 있는 달 수 초기화
        for (String term : terms) {
            String[] split = term.split(" ");
            termArr[split[0].charAt(0) - 'A'] = Integer.parseInt(split[1]);
        }
        
        Date t = new Date(today); // 오늘
        for (int i = 0; i < privacies.length; i++) {
            Date tmp = new Date(privacies[i]); // 개인정보
            
            // 파기해야하면 리스트에 삽입
            if (t.yyyymmdd > tmp.yyyymmdd) {
                a.add(i + 1);
            }
        }
        
        // 리스트 -> 배열로 변환
        int[] answer = a.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    
}