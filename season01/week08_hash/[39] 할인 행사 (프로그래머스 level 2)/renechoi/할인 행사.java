import java.util.*;

class Solution {
	public int solution(String[] want, int[] number, String[] discount) {

		HashMap<String, Integer> 원하는거맵 = new HashMap<>();

		for (int i = 0; i < want.length; i++) {
			원하는거맵.put(want[i], number[i]);
		}

		int 가능한날짜 = 0;
		HashMap<String, Integer> 할인품목들 = new HashMap<>();

		// 처음 10일 동안의 할인 제품들로 카운트 및 초기화
		for (int i = 0; i < 10; i++) {
			맵에넣기(할인품목들, discount[i]);
		}

		if (할인을다받을수있으면(원하는거맵, 할인품목들)) {
			가능한날짜++;
		}

		// 이후 날짜들에 대해 하나씩 밀면서 검사
		for (int i = 10; i < discount.length; i++) {
			String 이전제품 = discount[i - 10];
			할인품목들.put(이전제품, 할인품목들.get(이전제품) - 1);
			if (할인품목들.get(이전제품) == 0) {
				할인품목들.remove(이전제품);
			}

			String 새제품 = discount[i];
			맵에넣기(할인품목들, 새제품);

			if (할인을다받을수있으면(원하는거맵, 할인품목들)) {
				가능한날짜++;
			}
		}

		return 가능한날짜;
	}

	private static void 맵에넣기(HashMap<String, Integer> 할인품목들, String discount) {
		할인품목들.put(discount, 할인품목들.getOrDefault(discount, 0) + 1);
	}

	private boolean 할인을다받을수있으면(HashMap<String, Integer> 원하는거맵, HashMap<String, Integer> 할인품목들) {
		for (String 품목 : 원하는거맵.keySet()) {
			if (할인품목들.getOrDefault(품목, 0) < 원하는거맵.get(품목)) {
				return false;
			}
		}
		return true;
	}
}