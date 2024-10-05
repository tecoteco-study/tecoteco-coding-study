package template.Boj;

import java.io.BufferedReader;
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

		int N = Integer.parseInt(br.readLine());
		int count = 0;

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			if (좋은문장(input)) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static boolean 좋은문장(String 문장) {
		Stack<Character> stack = new Stack<>();


		for (int i =0; i< 문장.length(); i++){
			char 알파벳 = 문장.charAt(i);

			if(stack.isEmpty()){
				stack.add(알파벳);
				continue;
			}

			if(stack.peek() == 알파벳){
				stack.pop();
				continue;
			}
			stack.add(알파벳);
		}
		return stack.isEmpty();
	}

	@Test
	public void testValidCases() throws IOException {
		Assertions.assertEquals(2, runTest(new String[]{"ABAB", "AABB", "ABBA"}));
		Assertions.assertEquals(1, runTest(new String[]{"AAA", "AA", "AB"}));
		Assertions.assertEquals(1, runTest(new String[]{"ABBABB"}));
		Assertions.assertEquals(2, runTest(new String[]{"AABB", "BBAA", "ABAB"}));
		Assertions.assertEquals(0, runTest(new String[]{"AAABBB", "BBAAB", "AB"}));
		Assertions.assertEquals(2, runTest(new String[]{"ABABAB", "ABBA", "BBAABB"}));
	}

	private int runTest(String[] inputs) {
		int count = 0;
		for (String input : inputs) {
			if (좋은문장(input)) {
				count++;
			}
		}
		return count;
	}

}