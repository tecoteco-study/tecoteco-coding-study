package template.programmers;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Solution {

	public int[][] solution(int[][] nodeinfo) {

		노드[] nodes = new 노드[nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++) {
			nodes[i] = new 노드();
			nodes[i].x = nodeinfo[i][0];
			nodes[i].y = nodeinfo[i][1];
			nodes[i].value = i + 1;
		}

		Arrays.sort(nodes);
		노드 root = nodes[0];
		for (int i = 1; i < nodes.length; i++) {
			root.노드초기화(nodes[i]);
		}

		return new int[][] { traverseTree(root, "preorder"), traverseTree(root, "postorder") };
	}


	public static class 노드 implements Comparable<노드> {
		int x;
		int y;
		int value;
		노드 left;
		노드 right;

		/**
		 * y 값 기준 내림차순 정렬
		 * y 값이 같으면 x 값을 기준으로 오름차순 정렬
		 */
		@Override
		public int compareTo(노드 other) {
			if (this.y != other.y) {
				return other.y - this.y;
			}
			return this.x - other.x;
		}

		public void 노드초기화(노드 child) {
			if (child.x < this.x) {
				if (this.left == null) {
					this.left = child;
				} else {
					this.left.노드초기화(child);
				}
			} else {
				if (this.right == null) {
					this.right = child;
				} else {
					this.right.노드초기화(child);
				}
			}
		}
	}

	public void preorder(노드 node, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		list.add(node.value);
		preorder(node.left, list);
		preorder(node.right, list);
	}

	public void postorder(노드 node, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		postorder(node.left, list);
		postorder(node.right, list);
		list.add(node.value);
	}

	private int[] traverseTree(노드 root, String order) {
		ArrayList<Integer> result = new ArrayList<>();
		if ("preorder".equals(order)) {
			preorder(root, result);
		} else if ("postorder".equals(order)) {
			postorder(root, result);
		}
		return result.stream().mapToInt(i -> i).toArray(); // ArrayList -> int[]
	}



	@Nested
	class SolutionTestCases {

		Solution solution = new Solution();

		@Test
		public void testCase1() {
			int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 }, { 2, 2 } };
			int[][] result = solution.solution(nodeinfo);
			int[][] expected = { { 7, 4, 6, 9, 1, 8, 5, 2, 3 }, { 9,6,5,8,1,4,3,2,7} };
			Assertions.assertArrayEquals(expected, result);

		}

	}
}
