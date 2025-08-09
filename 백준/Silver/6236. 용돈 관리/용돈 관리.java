import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        // N일 동안, M번, K원 인출
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int K = -1;

        // i번째 날에 이용할 금액
        int[] a = new int[N];
        // low는 금액 중 가장 큰 값, high는 금액의 합
        int low = 0, high = 0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
            low = Math.max(low, a[i]);
            high += a[i];
        }

        if (M == 1) {
            System.out.println(high);
            return;
        }
        if (M == N) {
            System.out.println(low);
            return;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int tmp = mid;
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                if (tmp < a[i]) {
                    cnt++;
                    tmp = mid - a[i];
                } else {
                    tmp -= a[i];
                }
            }

            if (cnt > M) {
                low = mid + 1;
            } else {
                K = mid;
                high = mid - 1;
            }
        }

        System.out.println(K);
    }
}