import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


class Main {
    
    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		if (pq.size() == 1) {
			System.out.println(0);
			return;
		}

		int result = 0;
		while (!pq.isEmpty()) {
			int a = pq.poll();
			if (pq.isEmpty()) {
				break;
			}
			int b = pq.poll();

			result += a + b;
			pq.offer(a + b);
		}

		System.out.println(result);
	}
    
}