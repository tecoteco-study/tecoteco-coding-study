import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] emergency) {
        return Arrays.stream(emergency)
            .map(i -> Arrays.stream(emergency).boxed()
                 .sorted(Comparator.reverseOrder())
                 .collect(Collectors.toList()).indexOf(i) + 1)
            .toArray();
    }
}