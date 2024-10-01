
import java.util.*;

public class Solution {

	boolean solution(String s) {

		Stack<Character> stack = new Stack<>();

		char[] brackets = s.toCharArray();
		for (char bracket : brackets) {

			if (bracket == '(') {
				stack.add(bracket);
				continue;
			}

			if (bracket == ')' && !stack.isEmpty()) {
				stack.pop();
			} else {
				return false;
			}
		}

		return stack.isEmpty();
	}
}