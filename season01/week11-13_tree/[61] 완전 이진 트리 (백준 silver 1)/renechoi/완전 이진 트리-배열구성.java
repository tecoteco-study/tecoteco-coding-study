package template.Boj;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Main {



	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> 전위순회리스트 = new ArrayList<>();
		while(true) {
			String input = bufferedReader.readLine();
			if(input == null) {
				break;
			}
			전위순회리스트.add(Integer.parseInt(input));
		}


		// 트리 배열 크기 계산 (1-based 인덱스 사용)
		int[] tree = new int[getMaxNodes(전위순회리스트) + 1];
		buildTree(1, 전위순회리스트, tree, new Index(), Integer.MIN_VALUE, Integer.MAX_VALUE);
		postOrder(tree, 1);
	}



	private static void buildTree(int 노드위치, List<Integer> 전위순회리스트, int[] tree, Index 전위순회리스트의인덱스, int min, int max) {
		// 종료 조건: 전위 순회 리스트의 끝에 도달
		if (전위순회리스트의인덱스.value >= 전위순회리스트.size()) {
			return;
		}

		int rootValue = 전위순회리스트.get(전위순회리스트의인덱스.value);

		// 현재 값이 허용된 범위 내에 있지 않으면 리턴
		if (rootValue < min || rootValue > max) {
			return;
		}

		// 현재 값을 사용하고 인덱스를 증가시킴
		tree[노드위치] = rootValue;
		전위순회리스트의인덱스.indexUp();

		// 왼쪽 서브트리
		buildTree(노드위치 * 2, 전위순회리스트, tree, 전위순회리스트의인덱스, min, rootValue - 1);

		// 오른쪽 서브트리
		buildTree(노드위치 * 2 + 1, 전위순회리스트, tree, 전위순회리스트의인덱스, rootValue + 1, max);
	}


	private static void postOrder(int[] tree, int 노드위치) {
		if (노드위치 >= tree.length || tree[노드위치] == 0) {
			return;
		}
		postOrder(tree, 노드위치 * 2);
		postOrder(tree, 노드위치 * 2 + 1);
		System.out.println(tree[노드위치]);
	}


	private static int getMaxNodes(List<Integer> 전위순회리스트) {
		return (1 << ((int)Math.ceil(Math.log(전위순회리스트.size() + 1) / Math.log(2)))) - 1;
	}

	static class Index {
		int value = 0;

		public Index indexUp() {
			value++;
			return this;
		}
	}

	@Test
	void testCase1() throws IOException {

	}

	private String runTest(String input) throws IOException {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		Main.main(new String[] {});

		return out.toString().trim();
	}

}


