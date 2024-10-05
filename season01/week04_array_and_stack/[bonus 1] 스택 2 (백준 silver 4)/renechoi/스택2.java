import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bufferedReader.readLine());

		StringBuilder output = new StringBuilder();

		스택 stack = new 스택();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
			int command = Integer.parseInt(st.nextToken());

			switch (command) {
				case 1:
					int x = Integer.parseInt(st.nextToken());
					stack.add(x);
					break;
				case 2:
					output.append(stack.pop()).append("\n");
					break;
				case 3:
					output.append(stack.size()).append("\n");
					break;
				case 4:
					output.append(stack.isEmpty()).append("\n");
					break;
				case 5:
					output.append(stack.peek()).append("\n");
					break;
			}
		}

		System.out.print(output);
	}

	public static class 스택 {

		private int[] 배열;
		private int cursor;

		public 스택() {
			배열 = new int[10];
			cursor = 0;
		}

		public void add(int x) {
			if (isFull()) {
				확장();
			}

			배열[cursor++] = x;
		}

		private boolean isFull() {
			return cursor == 배열.length;
		}

		public int pop() {
			if (cursor == 0) {
				return -1;
			}
			return 배열[--cursor];
		}

		public int size() {
			return cursor;
		}

		public int peek() {
			if (cursor == 0) {
				return -1;
			}
			return 배열[cursor - 1];
		}

		public int isEmpty() {
			return cursor == 0 ? 1 : 0;
		}

		private void 확장() {
			int[] 새로운배열 = new int[배열.length * 2];
			System.arraycopy(배열, 0, 새로운배열, 0, 배열.length);
			배열 = 새로운배열;
		}

	}
}