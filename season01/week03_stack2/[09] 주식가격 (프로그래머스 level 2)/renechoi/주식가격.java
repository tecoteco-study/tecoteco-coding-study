import java.util.*;

class Solution {
	public int[] solution(int[] prices) {
		int n = prices.length;
		int[] answer = new int[n];
		Stack<Integer> 스택 = new Stack<>();

		// 주식 가격을 정방향으로 순회
		for (int i = 0; i < n; i++) {
			while (스택이비어있지않음(스택) && 가격이떨어짐(prices, i, 스택)) {
				int topIndex = 스택.pop();
				answer[topIndex] = 가격이떨어진시간을계산(i, topIndex);
			}
			스택.push(i);
		}

		// 끝까지 남아 있으면 끝까지 떨어지지 않은 것
		while (스택이비어있지않음(스택)) {
			int topIndex = 스택.pop();
			answer[topIndex] = 가격이떨어진시간을계산(n, topIndex+1);
		}

		return answer;
	}

	/**
	 * <p>
	 * 스택에서 꺼낸 인덱스와 현재 인덱스 간의 차이를 계산하여 가격이 유지된 시간을 반환합니다.
	 * </p>
	 * @param 현재인덱스 현재 시점의 인덱스 (i)
	 * @param topIndex 스택에서 꺼낸 인덱스
	 * @return 가격이 유지된 시간 (i - topIndex)
	 */
	private static int 가격이떨어진시간을계산(int 현재인덱스, int topIndex) {
		return 현재인덱스 - topIndex; // 가격이 유지된 시간 계산
	}

	private static boolean 가격이떨어짐(int[] prices, int i, Stack<Integer> 스택) {
		return prices[i] < prices[스택.peek()];
	}

	private static boolean 스택이비어있지않음(Stack<Integer> 스택) {
		return !스택.isEmpty();
	}
}