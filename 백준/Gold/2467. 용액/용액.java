import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        a = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = N - 1;

        int sum = Integer.MAX_VALUE;
        int minL = a[l];
        int minR = a[r];

        while (l < r) {
            if (Math.abs(sum) > Math.abs(a[l] + a[r])) {
                minL = a[l];
                minR = a[r];
                sum = minL + minR;
            }

            if (sum == 0)
                break;

            if (a[l] + a[r] < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.printf("%d %d", minL, minR);
    }

}