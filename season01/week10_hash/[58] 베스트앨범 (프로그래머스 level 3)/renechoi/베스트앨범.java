import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] genres, int[] plays) {

		HashMap<String, Integer> 장르별횟수 = new HashMap<>();
		HashMap<String, ArrayList<노래>> 노래맵 = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {

			String genre = genres[i];
			if(!노래맵.containsKey(genre)){
				노래맵.put(genre, new ArrayList<>());
			}

			노래맵.get(genre).add(new 노래(genre, plays[i], i));
			장르별횟수.put(genre, 장르별횟수.getOrDefault(genre, 0) + plays[i]);
		}

		List<Map.Entry<String, Integer>> collect = 장르별횟수.entrySet().stream().sorted(((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))).collect(Collectors.toList());

		ArrayList<Integer> answer  = new ArrayList<>();
		for (int i = 0; i < collect.size(); i++) {
			노래맵.get(collect.get(i).getKey()).stream().sorted().limit(2).forEach(item -> answer.add(item.고유번호));
		}


		return answer.stream().mapToInt(Integer::intValue).toArray();
	}

	public static class 노래 implements Comparable<노래> {
		String 장르이름;
		int play;
		int 고유번호;

		public 노래(String 장르이름, int play, int 고유번호) {
			this.장르이름 = 장르이름;
			this.play = play;
			this.고유번호 = 고유번호;
		}

		@Override
		public int compareTo(노래 o) {
			if (this.play- o.play == 0) {
				return this.고유번호-o.고유번호;
			}

			return o.play -this.play;
		}
	}
}