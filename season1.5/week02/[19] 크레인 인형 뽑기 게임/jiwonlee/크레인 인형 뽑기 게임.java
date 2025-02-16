import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> 바구니 = new Stack<>();
        int answer = 0;
        for (int move : moves) {
            int doll = 0;
            // 인형 위치 탐색
            for (int i=0, j=move-1;i < board.length; i++) {
                // 크레인으로 인형 가져오기
                if (board[i][j] != 0) {
                    doll = board[i][j];
                    board[i][j] = 0;
                    break;
                }
            }
            
            if (doll == 0) {
                continue;
            }

            // 인형 넣으면서 바구니 확인하기
            if (!바구니.isEmpty() && doll == 바구니.peek()) {
                바구니.pop();
                answer++;
            } else {
                바구니.push(doll);
            }
        }

        return answer * 2;
    }
}