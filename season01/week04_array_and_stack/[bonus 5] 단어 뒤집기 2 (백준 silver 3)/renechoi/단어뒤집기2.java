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

		String input = br.readLine();

		System.out.println(createAnswer(input));
	}

	public static String createAnswer(String 문장) {
		Stack<Character> stack = new Stack<>();
		StringBuilder output = new StringBuilder();


		boolean 태그오픈 = false;
		for(int i = 0; i<문장.length(); i++){
			char 알파벳 = 문장.charAt(i);

			if(알파벳 == '<'){
				스택에서꺼내서결과문장에추가(stack, output);
				태그오픈 = true;
				output.append(알파벳);
				continue;
			}

			if (알파벳 =='>'){
				태그오픈 = false;
				output.append(알파벳);
				continue;
			}

			if(태그오픈){
				output.append(알파벳);
				continue;
			}

			if (알파벳 == ' ') {
				while (!stack.isEmpty()) {
					output.append(stack.pop());
				}
				output.append(알파벳);
				continue;
			}

			stack.add(알파벳);
		}

		문장끝에남은단어를처리(stack, output);

		return output.toString();

	}

	private static void 문장끝에남은단어를처리(Stack<Character> stack, StringBuilder output) {
		while (!stack.isEmpty()) {
			output.append(stack.pop());
		}
	}

	private static void 스택에서꺼내서결과문장에추가(Stack<Character> stack, StringBuilder output) {
		while (!stack.isEmpty()) {
			output.append(stack.pop());
		}
	}

	@Test
	public void testValidCases() throws IOException {
		Assertions.assertEquals("noojkeab enilno egduj", Main.createAnswer("baekjoon online judge"));
		Assertions.assertEquals("<open>gat<close>", Main.createAnswer("<open>tag<close>"));
		Assertions.assertEquals("<ab cd>fe hg<ij kl>", Main.createAnswer("<ab cd>ef gh<ij kl>"));
		Assertions.assertEquals("1eno 2owt 3eerht rruof4 evif5 xis6", Main.createAnswer("one1 two2 three3 4fourr 5five 6six"));
		Assertions.assertEquals("<int><max>7463847412<long long><max>7085774586302733229", Main.createAnswer("<int><max>2147483647<long long><max>9223372036854775807"));
		Assertions.assertEquals("<problem>31471<is hardest>melborp reve<end>", Main.createAnswer("<problem>17413<is hardest>problem ever<end>"));
		Assertions.assertEquals("<   space   >ecaps ecaps ecaps<    spa   c e>", Main.createAnswer("<   space   >space space space<    spa   c e>"));
	}

}