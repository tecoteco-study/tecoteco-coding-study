import java.util.*;

class Solution
{
	public int solution(String s) {
		int lastIndex = s.length();
		char[] chars = s.toCharArray();

		Stack<Character> 스택 = new Stack<>();

		while (lastIndex-- > 0) {
			char 글자 = chars[lastIndex];

			if (보관스택이비어있거나(스택) || 보관스택에서꺼낸값이일치하지않으면(스택, 글자)) {
				스택.add(글자);
				continue;
			}

			스택.pop();
		}

		return 스택.isEmpty() ? 1 : 0;
	}

	private boolean 보관스택에서꺼낸값이일치하지않으면(Stack<Character> stack2, char aChar) {
		return stack2.peek() != aChar;
	}

	private boolean 보관스택이비어있거나(Stack<Character> stack2) {
		return stack2.isEmpty();
	}
}