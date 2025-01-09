import java.util.*;

class Solution {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		Map<String, String> parentMap = new HashMap<>();
		Map<String, Integer> profitMap = new HashMap<>();

		for (int i = 0; i < enroll.length; i++) {
			parentMap.put(enroll[i], referral[i].equals("-") ? null : referral[i]);
			profitMap.put(enroll[i], 0);
		}

		for (int i = 0; i < seller.length; i++) {
			String currentSeller = seller[i];
			int profit = amount[i] * 100; // 판매 수량 * 100원
			distributeProfit(currentSeller, profit, parentMap, profitMap);
		}

		int[] result = new int[enroll.length];
		for (int i = 0; i < enroll.length; i++) {
			result[i] = profitMap.get(enroll[i]);
		}
		return result;
	}

	private void distributeProfit(String seller, int profit, Map<String, String> parentMap, Map<String, Integer> profitMap) {
		while (seller != null && profit > 0) {
			int 부모전달금액 = profit / 10;
			int 자신의수익 = profit - 부모전달금액;

			profitMap.put(seller, profitMap.get(seller) + 자신의수익);

			seller = parentMap.get(seller);
			profit = 부모전달금액;
		}
	}
}