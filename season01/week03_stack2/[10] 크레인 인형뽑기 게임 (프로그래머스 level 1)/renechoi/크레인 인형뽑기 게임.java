import java.util.*;

class Solution {
	public int solution(int[][] board, int[] moves) {

		int 터진횟수 = 0;
		Stack<Integer>[] 스택보드 = 보드를스택으로초기화(board);
		Stack<Integer> basket = new Stack<>();

		for (int move : moves) {

			if(꺼낼인형이없음(move, 스택보드)){
				continue;
			}

			Integer 꺼낸인형 = 스택보드[move-1].pop(); // board는 0베이스, move는 1베이스

			if(바스켓이비어있음(basket) || 꺼낸인형과일치하지않음(basket, 꺼낸인형)){
				basket.add(꺼낸인형);
				continue;
			}

			//즉 일치하면
			basket.pop();
			터진횟수++;
		}

		return 터진횟수 * 2; // 인형은 pair로 사라지므로
	}

	private static boolean 꺼낼인형이없음(int move, Stack<Integer>[] 스택보드) {
		return 스택보드[move - 1].isEmpty();
	}

	private static boolean 꺼낸인형과일치하지않음(Stack<Integer> basket, Integer 꺼낸인형) {
		return basket.peek() != 꺼낸인형;
	}

	private static boolean 바스켓이비어있음(Stack<Integer> basket) {
		return basket.isEmpty();
	}

	private static Stack<Integer>[] 보드를스택으로초기화(int[][] board) {
		int n = board.length;
		Stack<Integer>[] 스택보드 = new Stack[n];

		for (int i = 0; i < n; i++) {
			스택보드[i] = new Stack<>();
			for (int j = n - 1; j >= 0; j--) {
				if (인형이존재하면(i, board[j])){
					스택보드[i].add(board[j][i]);
				}
			}
		}

		return 스택보드;
	}

	private static boolean 인형이존재하면(int i, int[] board) {
		return board[i] != 0;
	}
}