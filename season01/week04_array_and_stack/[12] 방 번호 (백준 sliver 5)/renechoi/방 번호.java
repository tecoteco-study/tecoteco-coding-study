package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String  N = bufferedReader.readLine();

		int[] counts = new int[10];
		for (char c : N.toCharArray()) {
			counts[parseInt(c)]++;
		}

		processSixNine(counts); // 6과 9는 서로 뒤집어서 사용할 수 있으므로 합산 후 절반 처리

		System.out.println(Arrays.stream(counts).max().orElse(0));
	}

	private static void processSixNine(int[] counts) {
		int sixNineCount = counts[6] + counts[9];
		counts[6] = (sixNineCount + 1) / 2;
		counts[9] = counts[6];
	}

	private static int parseInt(char c) {
		return c - '0';
	}

}