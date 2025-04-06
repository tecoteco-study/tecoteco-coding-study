
import java.util.*;

class Solution {
	public int solution(int[] nums) {
		return Math.min((int) Arrays.stream(nums).distinct().count(), nums.length/2);
	}
}