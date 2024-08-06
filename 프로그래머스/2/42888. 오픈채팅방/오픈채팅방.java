import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<List<String>> list = new ArrayList<>();
        Map<String, String> user = new HashMap<>();
        StringTokenizer st;
        
        for(String str: record) {
            st = new StringTokenizer(str);
            String cmd = st.nextToken();
            String uid = st.nextToken();
            
            if(cmd.equals("Enter")) {
                String name = st.nextToken();
                user.put(uid, name);
                
                List<String> tmp = new ArrayList<>();
                tmp.add(uid);
                tmp.add("님이 들어왔습니다.");
                
                list.add(tmp);
                
            } else if(cmd.equals("Leave")) {
                List<String> tmp = new ArrayList<>();
                tmp.add(uid);
                tmp.add("님이 나갔습니다.");
                
                list.add(tmp);
                
            } else if(cmd.equals("Change")) {
                String name = st.nextToken();
                user.put(uid, name);
            }
        }
        
        List<String> ans = new ArrayList<>();
        
        for(List<String> tmp : list) {
            String name = user.get(tmp.get(0));
            ans.add(name + tmp.get(1));
        }
        
        String answer[] = ans.toArray(new String[ans.size()]);
        
        
        return answer;
    }
}