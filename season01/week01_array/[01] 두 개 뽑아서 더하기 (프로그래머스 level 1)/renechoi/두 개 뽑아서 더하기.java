import java.util.*;
import java.util.stream.IntStream;

class Solution {
	public int[] solution(int[] numbers) {

		Set<Integer> results = new HashSet<>();

		IntStream.range(0, numbers.length).forEach(i ->
			IntStream.range(i + 1, numbers.length).forEach(j ->
				results.add(numbers[i] + numbers[j])
			)
		);

		return results.stream().sorted().mapToInt(Integer::intValue).toArray();
	}
}