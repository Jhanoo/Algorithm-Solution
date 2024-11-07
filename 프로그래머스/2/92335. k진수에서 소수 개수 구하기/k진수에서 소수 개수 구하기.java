import java.util.*;

class Solution {
    
    // 1,000,000(10) = 1,212,210,202,001(3)
	// route(1,212,210,202,001) = 1,101,004.17... < 1,101,005
	static boolean[] isPrime;
	static Set<String> primes;

	public int solution(int n, int k) {
		initPrime();

		String[] nums = changeRadix(n, k).split("0");

		int answer = 0;
		for (String num : nums) {

			if (num.isEmpty())
				continue;

			if (primes.contains(num)) {
				answer++;
			} else {
				long tmp = Long.parseLong(num);

				if (checkPrime(tmp))
					answer++;
			}
		}

		return answer;
	}

	static void initPrime() {
		int n = 1101005;
		isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);

		primes = new HashSet<>();

		// 0, 1 != 소수
		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i <= n; i++) {

			if (isPrime[i]) { // 소수이면
				primes.add(i + ""); // 추가

				// 소수의 배수는 전부 false 갱신
				for (int j = i * i; j <= n && j > 0; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}

	// 10진법 -> n진법 변환
	static String changeRadix(int digit, int radix) {
		String changed = "";

		for (int i = digit; i > 0; i /= radix) {
			changed = (i % radix) + changed;
		}

		return changed;
	}

	static boolean checkPrime(long num) {
		// prime Set에 있는 마지막 소수는 1100977
		if (num <= 1100977) {
			return false;
		}

		for (String prime : primes) {
			if (num % Long.parseLong(prime) == 0) {
				return false;
			}
		}

		return true;
	}

}