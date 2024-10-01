import java.util.*;

class Solution {
	public int solution(String s) {
		int validCount = 0;
		RotatedString rotatedString = new RotatedString(s);

		// 문자열의 길이 만큼 회전 -> 각각의 회전된 문자열에 대해서
		for (int i = 0; i < s.length(); i++) {
			rotatedString.setRotation(i);  // 회전값
			BracketValidator validator = new BracketValidator(rotatedString);

			if (validator.isValid()) {
				validCount++;
			}
		}

		return validCount;
	}

	public static class RotatedString {
		private String original;
		private int length;
		private int rotation;

		public RotatedString(String original) {
			this.original = original;
			this.length = original.length();
			this.rotation = 0;  // 초기 회전값은 0
		}

		public void setRotation(int rotation) {
			this.rotation = rotation;
		}

		public int getLength() {
			return this.length;
		}

		/**
		 * 회전된 문자열에서 i번째 위치에 해당하는 문자를 반환합니다.
		 * <p>
		 * 문자열을 매번 회전시키는 대신, 주어진 회전값과 현재 위치를 수학적으로 계산하여 직접 회전된 위치의 문자를 반환하는 것이 핵심입니다.
		 * <p>
		 * 매번 회전된 문자열을 새로 생성하는 방식으로 처리한다면, 각 회전에 대해
		 * 문자열을 생성하는 데 O(n)의 시간이 소요되며, 이를 n번 반복하므로 전체 시간 복잡도는 O(n²)입니다.
		 * 그러나 현재 방식에서는 수학적 계산을 통해 문자열의 인덱스만 계산하므로, 각 회전마다 O(1)입니다.
		 * 따라서 전체 알고리즘의 시간 복잡도는 O(n)으로 개선됩니다.
		 * </p>
		 * </p>
		 * <p>
		 * 회전된 문자열의 i번째 문자를 반환할 때, 실제로 회전한 문자열의 i번째 문자를 구하는
		 * 로직의 작동 원리를 예시로 설명합니다.
		 * <pre>
		 *    (i + rotation) % length
		 * </pre>
		 * 여기서 `i`는 반환할 문자의 원래 인덱스이고, `rotation`은 현재 회전된 양,
		 * `length`는 문자열의 전체 길이입니다.
		 * </p>
		 * <p><b>예시 1:</b></p>
		 * <p>원본 문자열: "abcde", 회전값: 2, i: 0</p>
		 * <pre>
		 *     (0 + 2) % 5 = 2, 반환 문자: 'c'
		 * </pre>
		 * <p><b>예시 2:</b></p>
		 * <p>원본 문자열: "abcde", 회전값: 3, i: 1</p>
		 * <pre>
		 *     (1 + 3) % 5 = 4, 반환 문자: 'e'
		 * </pre>
		 *
		 * @param i 회전된 문자열에서 반환하고자 하는 인덱스
		 * @return 주어진 회전값을 적용한 후의 i번째 문자
		 */
		public char charAt(int i) {
			return original.charAt((i + rotation) % length);
		}
	}

	public static class BracketValidator {
		private RotatedString rotatedString;

		public BracketValidator(RotatedString rotatedString) {
			this.rotatedString = rotatedString;
		}

		public boolean isValid() {
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < rotatedString.getLength(); i++) {
				char c = rotatedString.charAt(i);

				if (isOpenBracket(c)) {
					stack.push(c);
					continue;
				}
				if (stack.isEmpty() || isBracketMatches(c, stack.pop())) {
					return false;
				}
			}

			return stack.isEmpty();
		}

		private boolean isBracketMatches(char c, char top) {
			return (c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{');
		}

		private boolean isOpenBracket(char c) {
			return c == '(' || c == '[' || c == '{';
		}
	}

}