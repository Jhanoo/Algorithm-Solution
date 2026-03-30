import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        while (!line.equals("#")) {
            // A~Z = 65~90, a~z = 97~122
            boolean[] alphabet = new boolean[26];
            for (char c : line.toCharArray()) {
                if (c <= 90 && c >= 65)
                    alphabet[c - 'A'] = true;
                else if (c <= 122 && c >= 97)
                    alphabet[c - 'a'] = true;
            }

            int cnt = 0;
            for (int i = 0; i < 26; i++) {
                if (alphabet[i]) cnt++;
            }

            System.out.println(cnt);

            line = br.readLine();
        }

    }
}