import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] a;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        explore();

        System.out.println(dp[N - 1][M - 1]);
    }

    static void explore() {
        for (int row = 0; row < N; row++) {
            if (row == 0) {
                dp[0][0] = a[0][0];

                for (int i = 1; i < M; i++) {
                    dp[row][i] = dp[row][i - 1] + a[row][i];
                }
                continue;
            }

            int[] tmp = new int[M];
            for (int col = 0; col < M; col++) {
                tmp[col] = dp[row - 1][col] + a[row][col];
                dp[row][col] = Math.max(dp[row][col], tmp[col]);

                for (int i = col - 1; i >= 0; i--) {
                    tmp[i] = tmp[i + 1] + a[row][i];
                    dp[row][i] = Math.max(dp[row][i], tmp[i]);
                }

                for (int i = col + 1; i < M; i++) {
                    tmp[i] = tmp[i - 1] + a[row][i];
                    dp[row][i] = Math.max(dp[row][i], tmp[i]);
                }
            }

        }
    }

}