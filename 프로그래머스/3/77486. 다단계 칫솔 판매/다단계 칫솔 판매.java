import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public static class Person {
		String referral;
		int profit;

		public Person(String referral) {
			super();
			this.referral = referral;
		}

		@Override
		public String toString() {
			return "Person [referral=" + referral + ", profit=" + profit + "]\n";
		}

		public void divideProfit(int money) {
			if(money == 0)
				return;
			
			int tenPercent = money / 10;
			profit += money - tenPercent;

			if ("-".equals(referral))
				return;

			people.get(referral).divideProfit(tenPercent);
		}
	}

	public static Map<String, Person> people;

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int n = enroll.length + 1;

		people = new HashMap<>();
		people.put("-", new Person("-"));

		for (int i = 1; i < n; i++) {
			people.put(enroll[i - 1], new Person(referral[i - 1]));
		}

		for (int i = 0; i < seller.length; i++) {
			people.get(seller[i]).divideProfit(amount[i] * 100);
		}

		int[] answer = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			answer[i] = people.get(enroll[i]).profit;
		}

		return answer;
	}
}