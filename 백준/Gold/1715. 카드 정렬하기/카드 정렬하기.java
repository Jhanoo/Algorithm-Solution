import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(N);

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int ans = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();

            ans += a + b;
            pq.offer(a + b);
        }
        
        System.out.println(ans);
    }
}