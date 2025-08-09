import java.io.*;
import java.util.*;

public class Main {

    static int[][] gear = new int[4][8];
    static int[] head = new int[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            char[] s = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++)
                gear[i][j] = s[j] - '0'; // 0: N극, 1: S극
        }

        // 회전 횟수(1..100)
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken()); // 1: 시계, -1: 반시계

            int[] dirs = new int[4];
            dirs[g] = direction;

            // 왼쪽으로
            for (int l = g - 1; l >= 0; l--) {
                if (get(l, 2) != get(l + 1, 6)) {
                    dirs[l] = -dirs[l + 1];
                } else break;
            }
            // 오른쪽으로
            for (int r = g + 1; r < 4; r++) {
                if (get(r - 1, 2) != get(r, 6)) {
                    dirs[r] = -dirs[r - 1];
                } else break;
            }

            for (int i = 0; i < 4; i++) {
                if (dirs[i] != 0)
                    rotate(i, dirs[i]);
            }
        }

        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (get(i, 0) == 1) {
                score += (1 << i);
            }
        }

        System.out.println(score);
    }

    // 톱니바퀴 i의 k번 위치(0..7) 값 읽기
    static int get(int i, int k) {
        return gear[i][(head[i] + k) & 7]; // 비트 연산
    }

    // dir = 1: 시계, -1: 반시계
    static void rotate(int i, int dir) {
        if (dir == 1) {
            head[i] = (head[i] + 7) & 7;
        } else if (dir == -1) {
            head[i] = (head[i] + 1) & 7;
        }
    }

}
