import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long T = Long.parseLong(br.readLine().trim());

        int n = Integer.parseInt(br.readLine().trim());
        long[] A = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) A[i] = Long.parseLong(st.nextToken());

        int m = Integer.parseInt(br.readLine().trim());
        long[] B = new long[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) B[i] = Long.parseLong(st.nextToken());

        long[] AS = getSubArraySums(A);
        long[] BS = getSubArraySums(B);

        Arrays.sort(AS);
        Arrays.sort(BS);

        long ans = 0;
        int i = 0;
        int j = BS.length - 1;

        while (i < AS.length && j >= 0) {
            long sum = AS[i] + BS[j];
            if (sum == T) {
                long aVal = AS[i], bVal = BS[j];
                long cntA = 0, cntB = 0;
                while (i < AS.length && AS[i] == aVal) { cntA++; i++; }
                while (j >= 0 && BS[j] == bVal) { cntB++; j--; }
                ans += cntA * cntB;
            } else if (sum < T) {
                i++;
            } else {
                j--;
            }
        }

        System.out.println(ans);
    }

    static long[] getSubArraySums(long[] arr) {
        int n = arr.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];

        int size = n * (n + 1) / 2;
        long[] sums = new long[size];
        int idx = 0;
        for (int s = 0; s < n; s++) {
            for (int e = s + 1; e <= n; e++) {
                sums[idx++] = prefix[e] - prefix[s];
            }
        }
        return sums;
    }
}
