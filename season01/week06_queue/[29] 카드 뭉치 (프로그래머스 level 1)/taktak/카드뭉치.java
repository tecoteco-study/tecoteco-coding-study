import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution { // 큐_카드뭉치
    public String solution(String[] cards1, String[] cards2, String[] goal){
        Queue<String> cardQueue1 = new ArrayDeque<>(Arrays.asList(cards1));
        Queue<String> cardQueue2 = new ArrayDeque<>(Arrays.asList(cards2)); // 굳이 할필요옶다
        String answer = "";

        for (String s : goal) {
            if (!cardQueue1.isEmpty() && s.equals(cardQueue1.peek())) {
                cardQueue1.poll();
            } else if (!cardQueue2.isEmpty() && s.equals(cardQueue2.peek())) {
                cardQueue2.poll();
            } else {
                answer = "No";
                break;
            }
        }
        if(answer.isEmpty()) {
            answer = "Yes";
        }
        return answer;
    }


}

