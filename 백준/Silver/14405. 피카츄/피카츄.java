import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        S = S.replaceAll("pi", " ");
        S = S.replaceAll("ka", " ");
        S = S.replaceAll("chu", " ");

        if (S.isBlank())
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}