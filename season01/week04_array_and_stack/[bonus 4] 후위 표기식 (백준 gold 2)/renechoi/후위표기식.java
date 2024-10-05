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

	public static String createAnswer(String expression) {
		Stack<Character> stack = new Stack<>();
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (피연산자라면(c)) {
				output.append(c);
			} else if (여는괄호이면(c)) {
				stack.push(c);
			} else if (닫는괄호이면(c)) {
				while (여는괄호를만날때까지(stack)) {
					output.append(stack.pop());
				}
				스택에서여는괄호제거(stack);
			} else { //연산자이면
				우선순위가더높은연산자를꺼내서더해준다(stack, c, output);

				stack.push(c);
			}
		}

		while (!stack.isEmpty()) {
			output.append(stack.pop());
		}

		return output.toString();
	}

	private static void 우선순위가더높은연산자를꺼내서더해준다(Stack<Character> stack, char c, StringBuilder output) {
		while (!stack.isEmpty() && 현재우선순위가더낮음(c, stack)) {
			output.append(stack.pop());
		}
	}

	private static void 스택에서여는괄호제거(Stack<Character> stack) {
		if (!stack.isEmpty() && stack.peek() == '(') {
			stack.pop();
		}
	}

	private static boolean 현재우선순위가더낮음(char c, Stack<Character> stack) {
		return 우선순위(c) <= 우선순위(stack.peek());
	}

	private static boolean 여는괄호를만날때까지(Stack<Character> stack) {
		return !stack.isEmpty() && stack.peek() != '(';
	}

	private static boolean 닫는괄호이면(char c) {
		return c == ')';
	}

	private static boolean 피연산자라면(char c) {
		return Character.isLetter(c);
	}

	private static boolean 여는괄호이면(char c) {
		return c == '(';
	}

	public static int 우선순위(char operator) {
		switch (operator) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			default:
				return -1;
		}
	}

	@Test
	public void testValidCases() throws IOException {
		Assertions.assertEquals("ABC+*", Main.createAnswer("A*(B+C)"));
		Assertions.assertEquals("AB+", Main.createAnswer("A+B"));
		Assertions.assertEquals("ABC*+", Main.createAnswer("A+B*C"));
		Assertions.assertEquals("AB*C+", Main.createAnswer("A*B+C"));
		Assertions.assertEquals("ABC*+DE/-", Main.createAnswer("A+B*C-D/E"));
		Assertions.assertEquals("AB+CD*E/-", Main.createAnswer("A+B-C*D/E"));
		Assertions.assertEquals("AB+C*DE/-", Main.createAnswer("(A+B)*C-(D/E)"));
		Assertions.assertEquals("A", Main.createAnswer("A"));
		Assertions.assertEquals("ABC*+DE/+", Main.createAnswer("A+B*C+D/E"));
		Assertions.assertEquals("AB+CD-*", Main.createAnswer("(A+B)*(C-D)"));
	}

}