import java.util.*;

public class MyFile implements Comparable<MyFile> {
    String file;
    String head = "";
    Integer number;
    String tail = "";
    
    public MyFile(String file) {
        this.file = file;
        separate();
    }
    
    @Override
    public int compareTo(MyFile o) {
        int compareHead = this.head.toUpperCase().compareTo(o.head.toUpperCase());
        
        if (compareHead != 0) 
            return compareHead;
        
        int compareNum = this.number.compareTo(o.number);
        if (compareNum != 0)
            return compareNum;
        
        return 0;
    }
    
    private void separate() {
        boolean foundNum = false;
        int numStartIdx = -1;
        int tailStartIdx = -1;
        
        for (int i = 1; i < file.length(); i++) {
            char c = file.charAt(i);

            if (!foundNum && c >= '0' && c <= '9') {
                foundNum = true;
                head += file.substring(0, i);
                numStartIdx = i;
            }
            else if (foundNum && tailStartIdx == -1 && (c < '0' || c > '9')) {
                number = Integer.parseInt(file.substring(numStartIdx, i));
                tailStartIdx = i;
            }
        }

        if (tailStartIdx == -1) {
            number = Integer.parseInt(file.substring(numStartIdx, file.length()));
        } else {
            tail = file.substring(tailStartIdx, file.length());
        }
    }
    
    @Override
    public String toString() {
        return file;
    }
}

class Solution {
    public String[] solution(String[] files) {
        int n = files.length;
        MyFile[] fileArr = new MyFile[n];
        
        for (int i = 0; i < n; i++) {
            fileArr[i] = new MyFile(files[i]);
        }
        
        Arrays.sort(fileArr);
        
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = fileArr[i].toString();
        }
        return answer;
    }
}