package template.Boj;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String 입력문자열 = br.readLine();
		String 폭발문자열 = br.readLine();

		System.out.println(removeBombs(입력문자열, 폭발문자열));
	}

	private static String  removeBombs(String 입력문자열, String 폭발문자열) {
		int bombLength = 폭발문자열.length();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < 입력문자열.length(); i++) {

			stack.push(입력문자열.charAt(i));

			if (스택의문자열이폭발문자열보다같거나긴경우에만(stack, bombLength)) {
				boolean isBomb = true;

				for (int j = 0; j < bombLength; j++) {
					if (폭발문자열길이만큼앞으로돌아가서j번째(stack, bombLength, j) != 폭발문자열의j번째(폭발문자열, j)) {
						isBomb = false;
						break;
					}
				}

				if (isBomb) {
					for (int j = 0; j < bombLength; j++) {
						stack.pop();
					}
				}
			}
		}

		StringBuilder result = new StringBuilder();
		for (Character c : stack) {
			result.append(c);
		}

		if (result.length()==0) {
			return "FRULA";
		} else {
			return result.toString();
		}
	}

	private static char 폭발문자열의j번째(String 폭발문자열, int j) {
		return 폭발문자열.charAt(j);
	}

	private static Character 폭발문자열길이만큼앞으로돌아가서j번째(Stack<Character> stack, int bombLength, int j) {
		return stack.get(stack.size() - bombLength + j);
	}

	private static boolean 스택의문자열이폭발문자열보다같거나긴경우에만(Stack<Character> stack, int bombLength) {
		return stack.size() >= bombLength;
	}

	@Test
	public void testValidCases() throws IOException {
		Assertions.assertEquals("mirkovniz", removeBombs("mirkovC4nizCC44", "C4"));

		Assertions.assertEquals("FRULA", removeBombs("12ab112ab2ab", "12ab"));

		Assertions.assertEquals("aaa", removeBombs("abcabcabc", "bc"));
		Assertions.assertEquals("abcdef", removeBombs("abcdef", "gh"));

		Assertions.assertEquals("ef", removeBombs("abcdef", "abcd"));
		Assertions.assertEquals("abcd", removeBombs("abcdef", "ef"));

		Assertions.assertEquals("FRULA", removeBombs("abc", "abc"));

		Assertions.assertEquals("ba", removeBombs("bbbbba", "bb"));

		Assertions.assertEquals("acac", removeBombs("abcabcabcabc", "bcab"));

		Assertions.assertEquals("abcdef", removeBombs("abcdef", ""));

		Assertions.assertEquals("abcdef", removeBombs("abcdef", "ghij"));
	}


	private BufferedReader createBufferedReader(String input) {
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
	}
}

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String 입력문자열 = br.readLine();
		String 폭발문자열 = br.readLine();

		System.out.println(removeBombs(입력문자열, 폭발문자열));
	}

	private static String removeBombs(String 입력문자열, String 폭발문자열) {
		int bombLength = 폭발문자열.length();
		StringBuilder result = new StringBuilder(); // 스택 역할을 하는 StringBuilder

		for (int i = 0; i < 입력문자열.length(); i++) {
			result.append(입력문자열.charAt(i));

			if (result.length() >= bombLength) {
				boolean isBomb = true;
				for (int j = 0; j < bombLength; j++) {
					if (result.charAt(result.length() - bombLength + j) != 폭발문자열.charAt(j)) {
						isBomb = false;
						break;
					}
				}

				if (isBomb) {
					result.delete(result.length() - bombLength, result.length());
				}
			}
		}

		return result.length() == 0 ? "FRULA" : result.toString();
	}
}