
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int 원소개수 = Integer.parseInt(st.nextToken());
		int 명령어개수 = Integer.parseInt(st.nextToken());

		CircularDeque 큐 = new CircularDeque(원소개수 + 1);
		for (int i = 1; i <= 원소개수; i++) {
			큐.add(i);
		}

		st = new StringTokenizer(br.readLine());
		int 연산횟수 = 0;

		while (명령어개수-- > 0) {
			int 뽑아야할숫자 = Integer.parseInt(st.nextToken());

			int idx = 큐.indexOf(뽑아야할숫자);

			if (idx == 0) {
				큐.poll();
				continue;
			}

			int 왼쪽이동횟수 = idx;
			int 오른쪽이동횟수 = 큐.size() - idx;
			연산횟수 += Math.min(오른쪽이동횟수, 왼쪽이동횟수);

			if (왼쪽이동횟수 <= 오른쪽이동횟수) {
				왼쪽으로이동(왼쪽이동횟수, 큐);
			} else {
				오른쪽으로이동(오른쪽이동횟수, 큐);
			}

			큐.poll();
		}

		System.out.println(연산횟수);
	}

	// 왼쪽으로 큐를 이동시키는 함수
	private static void 왼쪽으로이동(int 왼쪽이동횟수, CircularDeque 큐) {
		for (int i = 0; i < 왼쪽이동횟수; i++) {
			큐.add(큐.poll());
		}
	}

	// 오른쪽으로 큐를 이동시키는 함수
	private static void 오른쪽으로이동(int 오른쪽이동횟수, CircularDeque 큐) {
		for (int i = 0; i < 오른쪽이동횟수; i++) {
			큐.addFront(큐.pollLast());
		}
	}
}

class CircularDeque {
	int[] data;
	int head;
	int tail;
	int capacity;
	int size;

	CircularDeque(int capacity) {
		data = new int[capacity];
		this.capacity = capacity;
		this.head = 0;
		this.tail = 0;
		this.size = 0;
	}

	void add(int x) {
		if (size == capacity) {
			throw new RuntimeException();
		}
		data[tail] = x;
		tail = (tail + 1) % capacity;
		size++;
	}

	void addFront(int x) {
		if (size == capacity) {
			throw new RuntimeException();
		}
		head = (head - 1 + capacity) % capacity;
		data[head] = x;
		size++;
	}

	int poll() {
		if (size == 0) {
			throw new RuntimeException();
		}
		int x = data[head];
		head = (head + 1) % capacity;
		size--;
		return x;
	}

	int pollLast() {
		if (size == 0) {
			throw new RuntimeException();
		}
		tail = (tail - 1 + capacity) % capacity;
		int x = data[tail];
		size--;
		return x;
	}

	int first() {
		if (size == 0) {
			throw new RuntimeException("큐가 비어 있습니다.");
		}
		return data[head];
	}

	int back() {
		if (size == 0) {
			throw new RuntimeException("큐가 비어 있습니다.");
		}
		return data[(tail - 1 + capacity) % capacity];
	}

	int size() {
		return size;
	}

	/**
	 * size에 대해 조회하는 이유는, 삭제된 항목이 있을 경우, 해당 값들은 볼 필요가 없기 때문
	 */
	int indexOf(int x) {
		for (int i = 0; i < size; i++) {
			if (data[(head + i) % capacity] == x) {
				return i;
			}
		}
		return -1;
	}
}