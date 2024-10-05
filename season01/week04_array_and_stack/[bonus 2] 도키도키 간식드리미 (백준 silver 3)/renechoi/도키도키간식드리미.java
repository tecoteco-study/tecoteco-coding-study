package template.Boj;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bufferedReader.readLine());

		Stack<Integer> stack = new Stack<>();
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		int 순번 = 1;

		while (N-- > 0) {
			int 학생 = Integer.parseInt(stringTokenizer.nextToken());

			while (!stack.isEmpty() && stack.peek() == 순번) {
				stack.pop();
				순번++;
			}

			if (학생 == 순번) {
				순번++;
			} else {
				stack.add(학생);
			}
		}

		while (!stack.isEmpty() && stack.peek() == 순번) {
			stack.pop();
			순번++;
		}

		System.out.println(stack.isEmpty() ? "Nice" : "Sad");
	}

	// JUnit 테스트를 위한 solution 메서드 (테스트 코드에서 사용)
	public static String solution(BufferedReader bufferedReader) throws IOException {
		int N = Integer.parseInt(bufferedReader.readLine());

		Stack<Integer> stack = new Stack<>();
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		int 순번 = 1;

		while (N-- > 0) {
			int 학생 = Integer.parseInt(stringTokenizer.nextToken());

			while (!stack.isEmpty() && stack.peek() == 순번) {
				stack.pop();
				순번++;
			}

			if (학생 == 순번) {
				순번++;
			} else {
				stack.add(학생);
			}
		}

		while (!stack.isEmpty() && stack.peek() == 순번) {
			stack.pop();
			순번++;
		}

		return stack.isEmpty() ? "Nice" : "Sad";
	}

	@Test
	public void testValidCases() throws IOException {
		BufferedReader reader1 = createBufferedReader("5\n5 4 1 3 2\n");
		assertEquals("Nice", solution(reader1));

		BufferedReader reader2 = createBufferedReader("5\n5 1 2 4 3\n");
		assertEquals("Sad", solution(reader2));

		BufferedReader reader3 = createBufferedReader("1\n1\n");
		assertEquals("Nice", solution(reader3));

		BufferedReader reader4 = createBufferedReader("4\n1 2 3 4\n");
		assertEquals("Nice", solution(reader4));

		BufferedReader reader5 = createBufferedReader("3\n3 2 1\n");
		assertEquals("Nice", solution(reader5));

		BufferedReader reader6 = createBufferedReader("7\n7 1 6 2 5 3 4\n");
		assertEquals("Sad", solution(reader6));

		BufferedReader reader7 = createBufferedReader("6\n6 4 5 3 1 2\n");
		assertEquals("Nice", solution(reader7));
	}

	private BufferedReader createBufferedReader(String input) {
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
	}
}