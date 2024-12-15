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

import javax.swing.tree.TreeNode;

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


		// 트리 생성
		Index index = new Index();
		TreeNode root = buildTree(전위순회리스트, index, Integer.MIN_VALUE, Integer.MAX_VALUE);

		// 후위 순회 출력
		postOrder(root);
	}

	static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		TreeNode(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}


	private static TreeNode buildTree(List<Integer> 전위순회리스트, Index index, int min, int max) {
		// 종료 조건: 전위 순회 리스트의 끝에 도달
		if (index.value >= 전위순회리스트.size()) {
			return null;
		}

		int rootValue = 전위순회리스트.get(index.value);

		// 현재 값이 허용된 범위 내에 있지 않으면 리턴
		if (rootValue < min || rootValue > max) {
			return null;
		}

		// 현재 값을 사용하고 인덱스를 증가시킴
		index.indexUp();
		TreeNode root = new TreeNode(rootValue);

		// 왼쪽 서브트리 생성 (루트 값보다 작은 값)
		root.left = buildTree(전위순회리스트, index, min, rootValue - 1);

		// 오른쪽 서브트리 생성 (루트 값보다 큰 값)
		root.right = buildTree(전위순회리스트, index, rootValue + 1, max);

		return root;
	}

	private static void postOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.value);
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


