package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 개수 = Integer.parseInt(br.readLine());

		String 수식 = br.readLine();

		int[] 수식의값배열 = new int[개수];
		int index =0;
		while(개수-->0){
			수식의값배열[index++] = Integer.parseInt(br.readLine());
		}

		double result = 수식계산(수식, 수식의값배열);
		System.out.println(formatResult(result));
	}

	private static String formatResult(double result) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(result);
	}


	public static double 수식계산(String 수식, int[] 수식의값배열) {

		Stack<Double> stack = new Stack<>();

		for (int i = 0; i < 수식.length(); i++) {
			char c = 수식.charAt(i);

			if (Character.isLetter(c)) {
				stack.add((double)수식의값배열[알파벳을값으로환산(c)]);
				continue;
			}

			Double 뒤숫자 = stack.pop();
			Double 앞숫자 = stack.pop();

			double 연산값 = 연산하기(앞숫자,뒤숫자,c);

			stack.add(연산값);
		}
		return stack.pop();

	}

	private static double 연산하기(double 앞숫자, double 뒤숫자, char c) {
		switch (c){
			case '+':
				return 앞숫자+뒤숫자;
			case '-':
				return 앞숫자-뒤숫자;
			case '*':
				return 앞숫자*뒤숫자;
			case '/':
				return 앞숫자/뒤숫자;
		}
		return 0;
	}

	private static int 알파벳을값으로환산(char c) {
		return c - 65;
	}

	@Test
	public void testValidCases() throws IOException {
		Assertions.assertEquals("6.20", formatResult(수식계산("ABC*+DE/-", new int[]{1, 2, 3, 4, 5})));
		Assertions.assertEquals("3.00", formatResult(수식계산("AA+A+", new int[]{1})));
		Assertions.assertEquals("3.33", formatResult(수식계산("AB/", new int[]{10, 3})));
		Assertions.assertEquals("17.50", formatResult(수식계산("ABC*+DE/-F+", new int[]{2, 3, 5, 10, 20, 1})));
	}

}