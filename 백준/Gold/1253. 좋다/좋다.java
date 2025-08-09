import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        if (n <= 2) {
            System.out.println(0);
        }

        int goodCnt = 0;
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = n - 1;

            while (l < r) {
                if (l == i) {
                    l++;
                    continue;
                }
                if (r == i) {
                    r--;
                    continue;
                }

                int sum = a[l] + a[r];
                if (a[i] > sum) l++;
                else if (a[i] < sum) r--;
                else {
                    goodCnt++;
                    break;
                }
            }
        }

        System.out.println(goodCnt);
    }
}