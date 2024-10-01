package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String inputString = bufferedReader.readLine();
		int N = Integer.parseInt(inputString);

		StringBuilder stringBuilder = new StringBuilder();

		while (N-->0){
			stringBuilder.append(각각에대해서검증하기(bufferedReader)).append("\n");
		}

		System.out.println(stringBuilder);
	}

	private static String  각각에대해서검증하기(BufferedReader bufferedReader) throws IOException {
		int[] 알파벳배열 = new int[36];

		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		String 첫번째문자열 = stringTokenizer.nextToken();
		String 두번째문자열 = stringTokenizer.nextToken();

		for (char c : 첫번째문자열.toCharArray()){
			알파벳배열[알파벳을숫자로변환(c)]++;
		}

		for (char c: 두번째문자열.toCharArray()){
			알파벳배열[알파벳을숫자로변환(c)]--;
		}

		if(전부일치한다면(알파벳배열)){
			return "Possible";
		}

		return "Impossible";
	}

	private static int 알파벳을숫자로변환(char c) {
		return c -97;
	}

	private static boolean 전부일치한다면(int[] 알파벳배열) {
		return Arrays.stream(알파벳배열).allMatch(item -> item == 0);
	}

	@Nested
	class SolutionTestCases {

		@Test
		public void testValidCases() throws IOException {
			// Test 1: 동일한 문자열
			Assertions.assertEquals("Possible", Main.각각에대해서검증하기(createBufferedReader("abc abc")));

			// Test 2: 재배열 가능한 문자열
			Assertions.assertEquals("Possible", Main.각각에대해서검증하기(createBufferedReader("ab ba")));
			Assertions.assertEquals("Possible", Main.각각에대해서검증하기(createBufferedReader("ring gnir")));

			// Test 3: 재배열 불가능한 문자열
			Assertions.assertEquals("Impossible", Main.각각에대해서검증하기(createBufferedReader("newt twan")));
		}

		@Test
		public void testEdgeCases() throws IOException {
			// Test 4: 한 글자씩의 문자열 (같으면 가능)
			Assertions.assertEquals("Possible", Main.각각에대해서검증하기(createBufferedReader("a a")));

			// Test 5: 한 글자씩의 문자열 (다르면 불가능)
			Assertions.assertEquals("Impossible", Main.각각에대해서검증하기(createBufferedReader("a b")));

			// Test 6: 길이가 긴 재배열 가능 문자열
			Assertions.assertEquals("Possible", Main.각각에대해서검증하기(createBufferedReader("abcdefg gfedcba")));

			// Test 7: 길이가 긴 재배열 불가능 문자열
			Assertions.assertEquals("Impossible", Main.각각에대해서검증하기(createBufferedReader("abcdefgh ijklmnop")));
		}

		@Test
		public void testComplexCases() throws IOException {
			// Test 8: 매우 긴 문자열 테스트
			String longStr1 = "a".repeat(500) + "b".repeat(500);
			String longStr2 = "b".repeat(500) + "a".repeat(500);
			Assertions.assertEquals("Possible", Main.각각에대해서검증하기(createBufferedReader(longStr1 + " " + longStr2)));

			// Test 9: 긴 문자열, 재배열 불가능한 경우
			String longStr3 = "a".repeat(500) + "b".repeat(499) + "c";
			Assertions.assertEquals("Impossible", Main.각각에대해서검증하기(createBufferedReader(longStr3 + " " + longStr2)));
		}

		// BufferedReader를 생성하는 헬퍼 메서드
		private BufferedReader createBufferedReader(String input) {
			return new BufferedReader(new InputStreamReader(new java.io.ByteArrayInputStream(input.getBytes())));
		}
	}
}