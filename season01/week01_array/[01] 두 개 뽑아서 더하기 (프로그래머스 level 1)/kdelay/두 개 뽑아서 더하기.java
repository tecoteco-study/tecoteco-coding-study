import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] numbers) {

        Set<Integer> set = new HashSet<>();

        IntStream.range(0, numbers.length-1).forEach(i ->
                IntStream.range(i+1, numbers.length).forEach(j ->
                        set.add(numbers[i] + numbers[j])
                )
        );
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}