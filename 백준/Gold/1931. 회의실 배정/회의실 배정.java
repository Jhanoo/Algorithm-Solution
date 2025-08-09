import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] a = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken()); // start
            a[i][1] = Integer.parseInt(st.nextToken()); // end
        }

        Arrays.sort(a, (o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int cnt = 0;
        int lastEnd = 0;
        for(int i = 0; i < N; i++) {
            if(a[i][0] >= lastEnd) {
                cnt++;
                lastEnd = a[i][1];
            }
        }

        System.out.println(cnt);
    }

}
