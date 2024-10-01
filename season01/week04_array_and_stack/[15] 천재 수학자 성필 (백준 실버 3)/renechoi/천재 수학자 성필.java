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

		Stack<Integer> 스택 = new Stack<>();

		System.out.println(스택을이용한후위연산(inputString, 스택));
	}

	public static int 스택을이용한후위연산(String inputString, Stack<Integer> 스택) {
		for (char c : inputString.toCharArray()) {
			if (isNumeral(c)) {
				스택.add(숫자로변환하기(c));
				continue;
			}

			if (is연산자(c)) {
				int n2 = 스택.pop();
				int n1 = 스택.pop();
				int result = 연산하기(n1, n2, c);
				스택.add(result);
			}
		}
		return 스택.pop();
	}

	private static boolean isNumeral(char c) {
		return c >= '0' && c <= '9';
	}

	private static int 숫자로변환하기(char c) {
		return c - '0';
	}

	private static boolean is연산자(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private static int 연산하기(int n1, int n2, char 연산자) {
		switch (연산자) {
			case '+':
				return n1 + n2;
			case '-':
				return n1 - n2;
			case '*':
				return n1 * n2;
			case '/':
				return n1 / n2;
			default:
				throw new IllegalArgumentException("유효하지 않은 연산자: " + 연산자);
		}
	}

	@Nested
	class SolutionTestCases {

		@Test
		public void testValidCases() {
			// Test 1: 문제의 예시 케이스 (123*+ -> 1 + (2 * 3) = 7)
			Assertions.assertEquals(7, Main.스택을이용한후위연산("123*+", new Stack<>()));
			// Test 2: 간단한 더하기 (12+ -> 1 + 2 = 3)
			Assertions.assertEquals(3, Main.스택을이용한후위연산("12+", new Stack<>()));
			// Test 3: 더하기와 곱하기 조합 (12*34*+ -> (1*2) + (3*4) = 14)
			Assertions.assertEquals(14, Main.스택을이용한후위연산("12*34*+", new Stack<>()));
			// Test 4: 나누기 (84/ -> 8 / 4 = 2)
			Assertions.assertEquals(2, Main.스택을이용한후위연산("84/", new Stack<>()));
			// Test 5: 빼기 (84- -> 8 - 4 = 4)
			Assertions.assertEquals(4, Main.스택을이용한후위연산("84-", new Stack<>()));
		}

		@Test
		public void testDivisionCases() {
			// Test 6: 나눗셈 (84/ -> 8 / 4 = 2)
			Assertions.assertEquals(2, Main.스택을이용한후위연산("84/", new Stack<>()));
			// Test 7: 연속 나눗셈 (84/2/ -> (8 / 4) / 2 = 1)
			Assertions.assertEquals(1, Main.스택을이용한후위연산("84/2/", new Stack<>()));
			// Test 8: 나눗셈 후 더하기 (84/2+ -> (8 / 4) + 2 = 4)
			Assertions.assertEquals(4, Main.스택을이용한후위연산("84/2+", new Stack<>()));
			// Test 9: 큰 숫자 나눗셈 (96/ -> 9 / 6 = 1)
			Assertions.assertEquals(1, Main.스택을이용한후위연산("96/", new Stack<>()));
		}

		@Test
		public void testEdgeCases() {
			// Test 10: 연속 곱셈 (23*45*+ -> (2*3) + (4*5) = 6 + 20 = 26)
			Assertions.assertEquals(26, Main.스택을이용한후위연산("23*45*+", new Stack<>()));
			// Test 11: 나눗셈과 곱셈 혼합 (84/3* -> (8 / 4) * 3 = 6)
			Assertions.assertEquals(6, Main.스택을이용한후위연산("84/3*", new Stack<>()));
		}
	}
}