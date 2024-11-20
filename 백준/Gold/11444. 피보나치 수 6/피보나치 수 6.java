import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	// 0 1 2 3 4 5 6 ... ==> index
	// 0 1 1 2 3 5 8 ... ==> n번째 피보나치 수
	// f(n) = 1f(n-1) + 1f(n-2) = f(2)f(n-1) + f(1)f(n-2)
	//      = 2f(n-2) + 1f(n-3) = f(3)f(n-2) + f(2)f(n-3)
	//      = 3f(n-3) + 2f(n-4) = f(4)f(n-3) + f(3)f(n-4)
	//      = 5f(n-4) + 3f(n-5) = f(5)f(n-4) + f(4)f(n-5)
	//      =        ...
	// f(n) = f(k+1)f(n-k) + f(k)f(n-k-1) (단, k >=0 )
	
	// i) n=2k (짝수), ii) n=2k+1 (홀수) 의 경우를 살펴보자
	
	// i) n=2k 짝수일 때,
	//    f(2k) = f(k+1)f(k) + f(k)f(k-1)
	//          = f(k) * { f(k+1) + f(k-1) }
	//          = f(k) * { 2f(k-1) + f(k) }
	
	// ii) n=2k+1 홀수일 때,
	//  f(2k+1) = f(k+1)f(k+1) + f(k)f(k)
	//          = f(k)^2 + f(k+1)^2
	
	static HashMap<Long, Long> dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		dp = new HashMap<>();

		System.out.println(fibo(n));
	}

	static long fibo(long n) {

		if (n <= 0)
			return 0;

		if (n == 1 || n == 2)
			return 1;

        // 이전에 구했으면 바로 리턴(같은 연산 안하도록)
		Long result = dp.get(n);
		if (result != null)
			return result;

		if (n % 2 == 1) { // n = 2k+1 (홀수)
			long k = (n - 1) / 2;

			long a = fibo(k);
			long b = fibo(k + 1);
			result = (a * a + b * b); // f(2k+1) = f(k)^2 + f(k+1)^2

			dp.put(n, result % 1000000007);
		} else { // n = 2k (짝수)
			long k = n / 2;

			long a = fibo(k - 1);
			long b = fibo(k);
			result = b * (a * 2 + b); // f(2k) = f(k) * { 2f(k-1) + f(k) }

			dp.put(n, result % 1000000007);
		}

		return dp.get(n);
	}

}