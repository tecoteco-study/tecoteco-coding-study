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

		while (true) {
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);

			if (n == 0) {
				break;
			}

			int[] heights = new int[n];
			for (int i = 0; i < n; i++) {
				heights[i] = Integer.parseInt(input[i + 1]);
			}

			System.out.println(getMaxRectangleArea(heights));
		}
	}

	public static long getMaxRectangleArea(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		long maxArea = 0;
		int n = heights.length;

		// 히스토그램 높이를 순회하면서
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
				int height = heights[stack.pop()];
				int width = stack.isEmpty() ? i : i - stack.peek() - 1;
				maxArea = Math.max(maxArea, (long)height * width);
			}

			stack.push(i);
		}

		// 남아 있는 직사각형 처리
		while (!stack.isEmpty()) {
			int height = heights[stack.pop()];
			int width = stack.isEmpty() ? n : n - stack.peek() - 1;
			maxArea = Math.max(maxArea, (long)height * width);
		}

		return maxArea;
	}

	@Test
	public void testValidCases() throws IOException {
		// 테스트 케이스 1: 예제 입력 1 (7개의 높이가 주어진 경우)
		Assertions.assertEquals(8, getMaxRectangleArea(new int[]{2, 1, 4, 5, 1, 3, 3}));

		// 테스트 케이스 2: 모든 직사각형의 높이가 동일한 경우
		Assertions.assertEquals(4000, getMaxRectangleArea(new int[]{1000, 1000, 1000, 1000}));

		// 테스트 케이스 3: 직사각형의 높이가 모두 1인 경우
		Assertions.assertEquals(7, getMaxRectangleArea(new int[]{1, 1, 1, 1, 1, 1, 1}));

		// 테스트 케이스 4: 단일 직사각형
		Assertions.assertEquals(5, getMaxRectangleArea(new int[]{5}));

		// 테스트 케이스 5: 높이가 0인 직사각형 포함된 경우
		Assertions.assertEquals(9, getMaxRectangleArea(new int[]{0, 3, 3, 3, 0}));

		// 테스트 케이스 6: 높이가 다양한 경우
		Assertions.assertEquals(12, getMaxRectangleArea(new int[]{6, 2, 5, 4, 5, 1, 6}));

		// 테스트 케이스 7: 증가하다가 감소하는 패턴
		Assertions.assertEquals(12, getMaxRectangleArea(new int[]{1, 2, 3, 4, 5, 3, 2}));

		// 테스트 케이스 8: 모두 0인 경우
		Assertions.assertEquals(0, getMaxRectangleArea(new int[]{0, 0, 0, 0, 0}));

		// 테스트 케이스 9: 큰 값이 포함된 경우
		Assertions.assertEquals(12, getMaxRectangleArea(new int[]{4, 4, 4, 1, 1, 1, 12}));
	}

}



