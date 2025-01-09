package template.programmers;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Solution {


	public String solution(String[] participant, String[] completion) {

		HashMap<String, Integer> 완주자맵  = new HashMap<>();

		for (String 완주자 : completion) {
			완주자맵.put(완주자, 완주자맵.getOrDefault(완주자, 0)+1);
		}

		for (String 참가자 : participant) {

			if(!완주자맵.containsKey(참가자) || 완주자맵.get(참가자) == 0){
				return 참가자;
			}

			완주자맵.put(참가자, 완주자맵.get(참가자)-1);
		}

		return "Notfound";
	}


	@Nested
	class SolutionTestCases {

		@Test
		public void testCase1() {
			Solution solution = new Solution();
			Assertions.assertSame("leo", solution.solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
		}

	}
}
