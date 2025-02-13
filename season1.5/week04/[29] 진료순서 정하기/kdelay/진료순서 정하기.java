import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(int[] emergency) {
        Integer[] sortedEmergency = Arrays.stream(emergency)
                .boxed()
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);

        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < sortedEmergency.length; i++) {
            rankMap.put(sortedEmergency[i], i + 1);
        }

        return Arrays.stream(emergency)
                .map(rankMap::get)
                .toArray();
    }
}

/**
 *         int[] important = Arrays.stream(emergency)
 *                 .boxed()
 *                 .sorted(Collections.reverseOrder())
 *                 .mapToInt(Integer::intValue)
 *                 .toArray();
 *
 *         int[] answer = new int[emergency.length];
 *         for (int i = 0; i < emergency.length; i++) {
 *             for (int j = 0; j < emergency.length; j++) {
 *                 if (emergency[i] == important[j]) {
 *                     answer[i] = j + 1;
 *                     break;
 *                 }
 *             }
 *         }
 *         return answer;
 */