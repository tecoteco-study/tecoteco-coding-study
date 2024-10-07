import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] answers) {

        int[] one = new int[]{1, 2, 3, 4, 5};
        int[] two = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        //정답 횟수
        int[] answer = new int[3];
        for (int i = 0; i < answers.length; i++) {
            if (one[i % 5] == answers[i]) answer[0]++;
            if (two[i % 8] == answers[i]) answer[1]++;
            if (three[i % 10] == answers[i]) answer[2]++;
        }
        //최댓값
        int max = Arrays.stream(answer).max().getAsInt();

        return IntStream.range(0, 3)
                .filter(i -> answer[i] >= max)
                .map(i -> i + 1) //index
                .toArray();
    }
}