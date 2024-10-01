package template.Boj;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bufferedReader.readLine());

		System.out.println(풀이메서드(bufferedReader, N));
	}

	private static StringBuilder 풀이메서드(BufferedReader bufferedReader, int N) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		Stack<Top> 스택 = new Stack<>();

		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int index = 0;
		while (N--> 0) {

			Top top = new Top(Integer.parseInt(stringTokenizer.nextToken()), ++index);

			if (스택이비어있음(스택)) {
				없는경우0기록하고자신을스택에추가(stringBuilder, 스택, top);
				continue;
			}

			while (스택이비어있지않음(스택) && 현재의탑이왼쪽탑보다크면(스택, top)) {
				왼쪽탑을스택에서제거(스택);
			}

			if (스택이비어있음(스택)) {
				없는경우0기록하고자신을스택에추가(stringBuilder, 스택, top);
				continue;
			}

			있는경우해당인덱스기록하고자신을스택에추가(stringBuilder, 스택, top);
		}

		return stringBuilder;
	}

	private static boolean 스택이비어있음(Stack<Top> 스택) {
		return 스택.isEmpty();
	}

	private static boolean 스택이비어있지않음(Stack<Top> 스택) {
		return !스택.isEmpty();
	}

	private static void 있는경우해당인덱스기록하고자신을스택에추가(StringBuilder stringBuilder, Stack<Top> 스택, Top top) {
		stringBuilder.append(스택.peek().index).append(" ");
		스택.add(top);
	}

	private static void 없는경우0기록하고자신을스택에추가(StringBuilder stringBuilder, Stack<Top> 스택, Top top) {
		stringBuilder.append(0).append(" ");
		스택.add(top);
	}

	public static class Top {
		private int height;
		private int index;

		public Top(int height, int index) {
			this.height = height;
			this.index = index;
		}

		public boolean isShorterThan(Top top) {
			return this.height < top.height;
		}

		public boolean isEqualOrTallerThan(Top top) {
			return !isShorterThan(top);
		}
	}

	private static void 왼쪽탑을스택에서제거(Stack<Top> 스택) {
		스택.pop();
	}

	private static boolean 현재의탑이왼쪽탑보다크면(Stack<Top> 스택, Top top) {
		return 스택.peek().isShorterThan(top);
	}

	@Nested
	class SolutionTestCases {

		@Test
		public void testValidCases() throws IOException {
			// Test 1: 기본 케이스
			BufferedReader reader1 = createBufferedReader("6 9 5 7 4");
			Assertions.assertEquals("0 0 2 2 4 ", 풀이메서드(reader1, 5).toString());

			// Test 2: 모든 탑이 점점 더 높아지는 경우
			BufferedReader reader2 = createBufferedReader("1 2 3 4 5");
			Assertions.assertEquals("0 0 0 0 0 ", 풀이메서드(reader2, 5).toString());

			// Test 3: 첫 번째 탑이 가장 큰 경우
			BufferedReader reader3 = createBufferedReader("9 1 1 1 1");
			Assertions.assertEquals("0 1 2 3 4 ", 풀이메서드(reader3, 5).toString());
		}

		@Test
		public void testEdgeCases() throws IOException {
			// Test 4: N이 1인 경우 (탑이 하나뿐인 경우)
			BufferedReader reader4 = createBufferedReader("10");
			Assertions.assertEquals("0 ", 풀이메서드(reader4, 1).toString());

			// Test 5: 모든 탑이 같은 높이인 경우
			BufferedReader reader5 = createBufferedReader("5 5 5 5 5");
			Assertions.assertEquals("0 1 2 3 4 ", 풀이메서드(reader5, 5).toString());

			// Test 6: 내림차순으로 정렬된 경우
			BufferedReader reader6 = createBufferedReader("5 4 3 2 1");
			Assertions.assertEquals("0 1 2 3 4 ", 풀이메서드(reader6, 5).toString());

			// Test 7: 혼합된 케이스
			BufferedReader reader7 = createBufferedReader("3 9 7 5 8");
			Assertions.assertEquals("0 0 2 3 2 ", 풀이메서드(reader7, 5).toString());

			// Test 8: 최대 입력 테스트 (최대 500,000개의 탑)
			StringBuilder largeInput = new StringBuilder();
			for (int i = 0; i < 500000; i++) {
				largeInput.append(100000000).append(" ");
			}
			BufferedReader reader8 = createBufferedReader(largeInput.toString().trim());
			StringBuilder expectedOutput = new StringBuilder();
			for (int i = 0; i < 500000; i++) {
				expectedOutput.append(i).append(" ");
			}
			Assertions.assertEquals(expectedOutput.toString(), 풀이메서드(reader8, 500000).toString());
		}

		// BufferedReader를 생성하는 헬퍼 메서드
		private BufferedReader createBufferedReader(String input) {
			return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		}
	}
}