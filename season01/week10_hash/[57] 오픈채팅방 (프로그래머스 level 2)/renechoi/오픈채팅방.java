import java.util.*;

class Solution {

	public String[] solution(String[] record) {

		HashMap<String, String> 입장과아이디맵 = new HashMap<>();
		List<String[]> events = new ArrayList<>();
		for (String 기록 : record) {

			String[] 기록정보 = 기록.split(" ");
			String 입장정보 = 기록정보[0];
			String id = 기록정보[1];
			String 닉네임 = get닉네임(기록정보);

			if ("Enter".equals(입장정보)) {
				입장과아이디맵.put(id, 닉네임);
				events.add(new String[]{입장정보, id});
			} else if ("Change".equals(입장정보)) {
				입장과아이디맵.put(id, 닉네임);
			} else{
				입장과아이디맵.put(입장정보, id);
				events.add(new String[]{입장정보, id});
			}
		}

		List<String> result = new ArrayList<>();
		for (String[] 이벤트 : events) {
			String action = 이벤트[0];
			String id = 이벤트[1];
			String 닉네임 = 입장과아이디맵.get(id);

			if ("Enter".equals(action)) {
				result.add(닉네임 + "님이 들어왔습니다.");
			} else if ("Leave".equals(action)) {
				result.add(닉네임 + "님이 나갔습니다.");
			}
		}
		return result.toArray(new String[0]);

	}

	private static String get닉네임(String[] 기록정보) {
		if (기록정보.length == 3) {
			return 기록정보[2];
		}
		return null;
	}
}