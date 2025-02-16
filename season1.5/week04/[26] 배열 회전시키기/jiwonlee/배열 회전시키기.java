import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers, String direction) {
        int n = numbers.length;
        int[] answer = new int[n];
        int point = direction.equals("right") ? n - 1 : 1;
        for (int i = 0; i < n; i++) {
            answer[i] = numbers[(point + i) % n];
        }
        return answer;
    }
}

/*
class Solution {
    public int[] solution(int[] numbers, String direction) {
        int n = numbers.length;
        int[] answer = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            answer[i] = numbers[i % n];
        }
        int point = direction.equals("right") ? n - 1 : 1;
        return Arrays.copyOfRange(answer, point, point + n);
    }
}
*/