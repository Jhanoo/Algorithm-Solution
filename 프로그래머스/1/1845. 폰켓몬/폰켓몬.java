import java.util.HashSet;
import java.util.Set;

class Solution {
    
    public int solution(int[] nums) {
		Set<Integer> s = new HashSet<>();
        
		for (int num : nums) {
			s.add(num);
		}

		int pick = nums.length / 2;
		int size = s.size();

		return size < pick ? size : pick;
	}
}