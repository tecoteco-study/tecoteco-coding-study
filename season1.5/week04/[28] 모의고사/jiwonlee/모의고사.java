import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] answers) {
        int[] candidate1 = {1, 2, 3, 4, 5};
        int[] candidate2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] candidate3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == candidate1[i%candidate1.length]) {
                scores[0]++;
            }
            if (answers[i] == candidate2[i%candidate2.length]) {
                scores[1]++;
            }
            if (answers[i] == candidate3[i%candidate3.length]) {
                scores[2]++;
            }
        }

        int maxScore = IntStream.of(scores).max().getAsInt();
        List<Integer> tmps = new ArrayList<>();

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                tmps.add(i + 1);
            }
        }

        int[] result = new int[tmps.size()];
        for (int i = 0; i < tmps.size(); i++) {
            result[i] = tmps.get(i);
        }

        return result;
    }
}