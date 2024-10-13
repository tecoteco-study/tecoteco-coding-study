import java.util.*;

class Solution {
	public String solution(String[] cards1, String[] cards2, String[] goal) {
		ArrayDeque<String> 카드1 = new ArrayDeque<>();
		ArrayDeque<String> 카드2 = new ArrayDeque<>();

		for (String 카드 : cards1) {
			카드1.add(카드);
		}

		for (String 카드 :cards2) {
			카드2.add(카드);
		}

		for (String 단어 : goal) {
			if(카드가맞다면(카드1, 단어)){
				카드1.pop();
				continue;
			}

			if(카드가맞다면(카드2, 단어)){
				카드2.pop();
				continue;
			}

			return "No";
		}

		return "Yes";
	}


	private boolean 카드가맞다면(ArrayDeque<String> 카드, String 단어) {
		return !카드.isEmpty() && 카드.peek().equals(단어);
	}


}