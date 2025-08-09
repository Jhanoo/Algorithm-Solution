import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();

        Stack<Character> stack = new Stack<>();
        boolean isTag = false;
        for (char c : s.toCharArray()) {
            if (isTag) {
                if (c == '>')
                    isTag = false;
                sb.append(c);
                continue;
            }

            if (c == '<') {
                isTag = true;
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(c);
                continue;
            }

            if (c == ' ') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(c);
                continue;
            }

            stack.push(c);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

}
