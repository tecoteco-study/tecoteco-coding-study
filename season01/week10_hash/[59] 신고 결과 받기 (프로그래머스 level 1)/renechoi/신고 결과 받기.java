import java.util.HashMap;
import java.util.HashSet;
class Solution {
	public int[] solution(String[] id_list, String[] report, int k) {

		HashMap<String, HashSet<String>> 신고당한사람별신고자들 = new HashMap<>();
		HashMap<String, Integer> 메일수신횟수 = new HashMap<>();

		for (String id : id_list) {
			메일수신횟수.put(id, 0);
			신고당한사람별신고자들.put(id, new HashSet<>());
		}

		for (String 신고정보 : report) {
			String[] s = 신고정보.split(" ");
			String 신고자 = s[0];
			String 신고당한사람 = s[1];
			신고당한사람별신고자들.get(신고당한사람).add(신고자);
		}

		for (String 신고당한사람 : 신고당한사람별신고자들.keySet()) {
			HashSet<String> 신고자들 = 신고당한사람별신고자들.get(신고당한사람);
			if (신고자들.size() >= k) {
				for (String 신고자 : 신고자들) {
					메일수신횟수.put(신고자, 메일수신횟수.get(신고자) + 1);
				}
			}
		}

		int[] answer = new int[id_list.length];
		for (int i = 0; i < id_list.length; i++) {
			answer[i] = 메일수신횟수.get(id_list[i]);
		}

		return answer;
	}
}