package template.programmers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Solution {
	public int solution(int[][] maps) {
		Node start = new Node(0, 0);
		Node end = new Node( maps.length - 1, maps[0].length - 1);

		return bfs(maps, start, end);
	}

	public int bfs(int[][] maps, Node start, Node end) {
		int n = maps.length;
		int m = maps[0].length;
		boolean[][] visited = new boolean[n][m];
		Queue<Node> queue = new LinkedList<>();
		start.setDistance(1); // 시작하는 즉시 1칸임
		visited[start.x][start.y] = true;
		queue.offer(start);

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();

			if (currentNode.equals(end)) {
				return currentNode.getDistance();
			}

			for (Node nextNode : currentNode.getNextNodes()) {
				if (nextNode.isValid(maps) && !visited[nextNode.x][nextNode.y] && 벽이없다면(maps, nextNode)) {
					visited[nextNode.x][nextNode.y] = true;
					nextNode.setDistance(currentNode.getDistance() + 1);
					queue.offer(nextNode);
				}
			}
		}

		return -1;
	}

	private static boolean 벽이없다면(int[][] maps, Node nextNode) {
		return maps[nextNode.x][nextNode.y] == 1;
	}

	public static class Node {
		int x, y;
		private int distance; // 시작점에서부터의 거리

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.distance = -1; // 초기값은 -1로 설정
		}

		public Node goLeft() {
			return new Node(x - 1, y);
		}

		public Node goRight() {
			return new Node(x + 1, y);
		}

		public Node goUp() {
			return new Node(x, y - 1);
		}

		public Node goDown() {
			return new Node(x, y + 1);
		}

		public List<Node> getNextNodes() {
			List<Node> nextNodes = new ArrayList<>();
			nextNodes.add(goLeft());
			nextNodes.add(goRight());
			nextNodes.add(goUp());
			nextNodes.add(goDown());
			return nextNodes;
		}

		public boolean isValid(int[][] maps) {
			return x >= 0 && y >= 0 && x < maps.length && y < maps[0].length;
		}

		public boolean equals(Node other) {
			return this.x == other.x && this.y == other.y;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}
	}

	@Nested
	class WordChainTests {

		@Test
		public void test() {
			int[][] maps = {
				{1, 0, 1, 1, 1},
				{1, 0, 1, 0, 1},
				{1, 0, 1, 1, 1},
				{1, 1, 1, 0, 1},
				{0, 0, 0, 0, 1}
			};
			int result = solution(maps);
			assertEquals(11, result);
		}

	}

}

