package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class Main {


	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String inputString = bufferedReader.readLine();

		System.out.println(getRequiredBracketCount(inputString));
	}

	public static long getRequiredBracketCount(String inputString) {
		Stack<Character> stack = new Stack<>();
		int 매칭안된괄호 = 0;

		for (char bracket : inputString.toCharArray()) {

			if (stack.isEmpty() || isBracketOpen(bracket)) {
				stack.add(bracket);
				continue;
			}

			if (isBracketMatch(bracket,stack) ) {
				stack.pop();
				continue;
			}
			매칭안된괄호++;

		}

		return (long) stack.size() + 매칭안된괄호;
	}

	private static boolean isBracketOpen(char bracket) {
		return bracket == '(';
	}

	private static boolean isBracketMatch(char bracket, Stack<Character> stack) {
		return (stack.peek() == '(' && bracket == ')');
	}


	@Nested
	class SolutionTestCases {

		@Test
		public void testValidCases() {
			// Test 1: 기본적인 케이스
			Assertions.assertEquals(0, Main.getRequiredBracketCount("()"));
			// Test 2: 이미 올바른 괄호열
			Assertions.assertEquals(0, Main.getRequiredBracketCount("(()(()))"));
			// Test 3: 예제 케이스
			Assertions.assertEquals(3, Main.getRequiredBracketCount(")))()"));
			Assertions.assertEquals(2, Main.getRequiredBracketCount(")(()"));
			Assertions.assertEquals(4, Main.getRequiredBracketCount("))()(("));
			Assertions.assertEquals(1, Main.getRequiredBracketCount(")(()(()))"));
		}

		@Test
		public void testEdgeCases() {
			// Test 7: 빈 문자열 (올바른 괄호열)
			Assertions.assertEquals(0, Main.getRequiredBracketCount(""));
			// Test 8: 전체가 올바른 짝이 맞는 괄호
			Assertions.assertEquals(0, Main.getRequiredBracketCount("((((()))))"));
			// Test 9: 전체가 짝이 맞지 않는 괄호
			Assertions.assertEquals(4, Main.getRequiredBracketCount("))))"));
			Assertions.assertEquals(4, Main.getRequiredBracketCount("(((("));
		}
	}

}