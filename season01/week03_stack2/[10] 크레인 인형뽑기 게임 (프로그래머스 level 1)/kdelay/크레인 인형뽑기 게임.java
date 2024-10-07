import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {

        Stack<Integer> basket = new Stack<>();
        int answer = 0;

        //뽑기 횟수만큼 진행
        for (int move : moves) {
            for (int i = 0; i < board.length; i++) {
                int tmp = board[i][move - 1];
                //인형이 있는 경우만 뽑기 진행
                if (tmp > 0) {
                    //인형 1개만 뽑아서 바구니에 넣음
                    basket.push(tmp);
                    board[i][move - 1] = 0;
                    break;
                }
            }
            //바구니에 연속해서 쌓인 인형이 있을 경우 제거
            if (basket.size() > 1) {
                int pop = basket.pop();
                if (pop == basket.peek()) {
                    answer += 2;
                    basket.pop();
                }
                else basket.push(pop);
            }
        }
        return answer;
    }
}