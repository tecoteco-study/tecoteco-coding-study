
public class SegmentTreeTest {

	private int[] tree; // 세그먼트 트리 배열
	private int n;      // 입력 배열의 크기

	// 세그먼트 트리 생성자
	public SegmentTreeTest(int[] array) {
		this.n = array.length;
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		int maxSize = 2 * (int) Math.pow(2, height) - 1;
		tree = new int[maxSize];
		buildTree(array, 0, 0, n - 1);
	}

	// 세그먼트 트리 생성
	private void buildTree(int[] array, int treeIndex, int left, int right) {
		if (left == right) {
			tree[treeIndex] = array[left];
			return;
		}
		int mid = left + (right - left) / 2;
		buildTree(array, 2 * treeIndex + 1, left, mid);
		buildTree(array, 2 * treeIndex + 2, mid + 1, right);
		tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
	}

	// 값 업데이트
	public void updateValue(int[] array, int n, int index, int newValue) {
		if (index < 0 || index >= n) {
			System.out.println("잘못된 인덱스");
			return;
		}
		int diff = newValue - array[index];
		array[index] = newValue;
		updateValueUtil(0, 0, n - 1, index, diff);
	}

	private void updateValueUtil(int treeIndex, int left, int right, int index, int diff) {
		if (index < left || index > right) return;
		tree[treeIndex] += diff;
		if (left != right) {
			int mid = left + (right - left) / 2;
			updateValueUtil(2 * treeIndex + 1, left, mid, index, diff);
			updateValueUtil(2 * treeIndex + 2, mid + 1, right, index, diff);
		}
	}

	// 구간 합 쿼리
	public int query(int queryLeft, int queryRight) {
		return queryUtil(0, 0, n - 1, queryLeft, queryRight);
	}

	private int queryUtil(int treeIndex, int left, int right, int queryLeft, int queryRight) {
		if (queryLeft <= left && right <= queryRight) {
			return tree[treeIndex];
		}
		if (right < queryLeft || left > queryRight) {
			return 0; // 합 기준 초기값
		}
		int mid = left + (right - left) / 2;
		return queryUtil(2 * treeIndex + 1, left, mid, queryLeft, queryRight) +
			queryUtil(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight);
	}

	// 테스트 실행
	public static void main(String[] args) {
		int[] array = {1, 3, 5, 7, 9};
		SegmentTreeTest st = new SegmentTreeTest(array);

		// 구간 합 쿼리
		System.out.println("구간 합 (2~4): " + st.query(1, 3));

		// 값 업데이트
		st.updateValue(array, array.length, 2, 6);
		System.out.println("업데이트 후 구간 합 (2~4): " + st.query(1, 3));
	}
}
