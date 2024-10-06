
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 명령어개수 = Integer.parseInt(br.readLine());
		배열로구현한선형큐 큐 = new 배열로구현한선형큐(100000);

		StringBuilder stringBuilder = new StringBuilder();
		while (명령어개수-- > 0) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			String 명령어 = stringTokenizer.nextToken();

			switch (명령어) {
				case "push":
					큐.add(Integer.parseInt(stringTokenizer.nextToken()));
					break;
				case "pop":
					stringBuilder.append(큐.poll()).append("\n");
					break;
				case "size":
					stringBuilder.append(큐.size()).append("\n");
					break;
				case "empty":
					stringBuilder.append(큐.isEmpty() ? 1 : 0).append("\n");
					break;
				case "front":
					stringBuilder.append(큐.front()).append("\n");
					break;
				case "back":
					stringBuilder.append(큐.back()).append("\n");
					break;
			}
		}

		System.out.println(stringBuilder);

	}




	public static class 배열로구현한선형큐 {

		private int front;
		private int rear;
		private int[] 배열;

		private int size;
		private int capacity;

		public 배열로구현한선형큐(int capacity) {
			this.배열 = new int[capacity];
			this.capacity = capacity;
			this.front = 0;
			this.rear = 0;
			this.size = 0;
		}

		public boolean isEmpty() {
			return this.size == 0;
		}

		public boolean isFull() {
			return this.size == capacity;
		}

		public int poll() {
			if (isEmpty()) {
				return -1;
			}
			int value = 배열[front];
			front++;
			size--;
			return value;
		}

		public void add(int x) {
			if (isFull()) {
				throw new RuntimeException();
			}
			배열[rear++] = x;
			size++;
		}

		public int size() {
			return this.size;
		}

		public int front() {
			if (isEmpty()) {
				return -1;
			}
			return 배열[front];
		}

		public int back() {
			if (isEmpty()) {
				return -1;
			}
			return 배열[rear - 1];
		}
	}

}

