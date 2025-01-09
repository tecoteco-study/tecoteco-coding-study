
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


class Solution {


	public String[] solution(String[] orders, int[] course) {
		List<String> answer = new ArrayList<>();

		List<HashSet<Character>> 각사람별주문메뉴 = 각사람의메뉴정보를초기화(orders); // O(n * m)  n = 최대 20, m = 최대 10
		for (int 코스크기 : course) {
			Map<String, Integer> 해당메뉴카운팅 = new HashMap<>();
			for (HashSet<Character> 시킨메뉴 : 각사람별주문메뉴) {
				List<String> 메뉴조합들 = get모든조합들(시킨메뉴, 코스크기); // O(C(m, 코스크기)) <- 각 주문마다 메뉴 조합 생성, 최대 C(10, 코스크기)개의 조합 생성,  m 최대 10, 코스크기 최대 10 일때 C(10,5)=252
				for (String 메뉴 : 메뉴조합들) {
					해당메뉴카운팅.put(메뉴, 해당메뉴카운팅.getOrDefault(메뉴, 0) + 1);
				}
			}

			// 위 반복문 전체 시간 복잡도: O(n * 총 조합 수) = O(n * ∑_{k ∈ course} C(m, k))
			// 총 조합 수는 각 코스크기에 대한 조합 수의 합으로, 이항정리에 따라 최대 O(2^m)
			// 따라서, 이 부분의 전체 시간 복잡도는 최악의 경우 O(n * 2^m)
			// n 최대 20, m 최대 10 -> 최대 20 * 1024 = 20,480

			int maxFrequency = 가장많이주문된메뉴조합구하기(해당메뉴카운팅); // O(k) 여기서 k는 위의 수치

			정답에추가하기(maxFrequency, 해당메뉴카운팅, answer); // O(k)
		}

		Collections.sort(answer); // O(nlogn)

		return answer.toArray(new String[0]);
	}

	private static void 정답에추가하기(int maxFrequency, Map<String, Integer> 해당메뉴카운팅, List<String> answer) {
		if (maxFrequency >= 2) {
			for (Map.Entry<String, Integer> entry : 해당메뉴카운팅.entrySet()) {
				if (entry.getValue() == maxFrequency) {
					answer.add(entry.getKey());
				}
			}
		}
	}

	private static int 가장많이주문된메뉴조합구하기(Map<String, Integer> 해당메뉴카운팅) {
		int maxFrequency = 0;
		for (int frequency : 해당메뉴카운팅.values()) {
			maxFrequency = Math.max(maxFrequency, frequency);
		}
		return maxFrequency;
	}

	private List<String> get모든조합들(HashSet<Character> 시킨메뉴, int n) {
		List<String> combinations = new ArrayList<>();
		List<Character> 메뉴리스트 = new ArrayList<>(시킨메뉴);
		Collections.sort(메뉴리스트); // 메뉴 정렬로 순서 보장
		조합계산(메뉴리스트, n, 0, new StringBuilder(), combinations);
		return combinations;
	}

	private void 조합계산(List<Character> 메뉴리스트, int n, int start, StringBuilder sb, List<String> combinations) {
		if (sb.length() == n) {
			combinations.add(sb.toString());
			return;
		}

		for (int i = start; i < 메뉴리스트.size(); i++) {
			sb.append(메뉴리스트.get(i));
			조합계산(메뉴리스트, n, i + 1, sb, combinations);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private static List<HashSet<Character>> 각사람의메뉴정보를초기화(String[] orders) {
		List<HashSet<Character>> 각사람별주문메뉴 = new ArrayList<>();
		for (String order : orders) {
			HashSet<Character> 메뉴 = new HashSet<>();
			for (char c : order.toCharArray()) {
				메뉴.add(c);
			}
			각사람별주문메뉴.add(메뉴);
		}
		return 각사람별주문메뉴;
	}

}