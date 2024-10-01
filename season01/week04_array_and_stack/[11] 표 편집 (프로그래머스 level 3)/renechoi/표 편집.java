import java.util.*;

class Solution {
	public String solution(int n, int k, String[] cmds) {

		Table table = new Table(n, k);

		for (String cmd : cmds) {
			table.handleCommand(new Cmd(cmd));
		}

		return table.getFinalState();
	}

	public static class Table {

		private int n;
		private int currentIndex;
		private int[] prev;
		private int[] next;
		private Stack<Node> backupRows;

		public Table(int totalRows, int startIndex) {
			this.n = totalRows;
			this.currentIndex = startIndex;
			this.prev = new int[n];
			this.next = new int[n];
			this.backupRows = new Stack<>();

			for (int i = 0; i < n; i++) {
				prev[i] = i - 1;
				next[i] = i + 1;
			}
			next[n - 1] = -1;
		}

		public void handleCommand(Cmd cmd) {
			switch (cmd.명령어) {
				case 올라가기:
					moveUp(cmd.이동칸수);
					break;
				case 내려가기:
					moveDown(cmd.이동칸수);
					break;
				case 행지우기:
					deleteRow();
					break;
				case 되돌리기:
					undoDelete();
					break;
			}
		}

		// 현재 행에서 X칸 위로 이동
		private void moveUp(int x) {
			for (int i = 0; i < x; i++) {
				currentIndex = prev[currentIndex];
			}
		}

		// 현재 행에서 X칸 아래로 이동
		private void moveDown(int x) {
			for (int i = 0; i < x; i++) {
				currentIndex = next[currentIndex];
			}
		}

		// 현재 선택된 행을 삭제하고 바로 아래 행 선택 (마지막 행인 경우는 위로)
		private void deleteRow() {
			backupRows.push(new Node(currentIndex, prev[currentIndex], next[currentIndex]));

			// 이전 노드와 다음 노드의 연결을 변경
			if (prev[currentIndex] != -1) {
				next[prev[currentIndex]] = next[currentIndex];
			}
			if (next[currentIndex] != -1) {
				prev[next[currentIndex]] = prev[currentIndex];
			}

			// 현재 인덱스 업데이트
			if (next[currentIndex] != -1) {
				currentIndex = next[currentIndex];
			} else {
				currentIndex = prev[currentIndex];
			}
		}

		private void undoDelete() {
			if (!backupRows.isEmpty()) {
				Node node = backupRows.pop();

				// 이전 노드와 다음 노드의 연결을 복구
				if (node.prev != -1) {
					next[node.prev] = node.index;
				}
				if (node.next != -1) {
					prev[node.next] = node.index;
				}
			}
		}

		// 최종 상태 문자열 반환 -> 삭제되지 않은 행은 O, 삭제된 행은 X
		public String getFinalState() {
			StringBuilder sb = new StringBuilder();
			char[] result = new char[n];
			Arrays.fill(result, 'X');

			int idx = currentIndex;
			while (prev[idx] != -1) {
				idx = prev[idx];
			}

			while (idx != -1) {
				result[idx] = 'O';
				idx = next[idx];
			}

			return new String(result);
		}

		private static class Node {
			int index;
			int prev;
			int next;

			Node(int index, int prev, int next) {
				this.index = index;
				this.prev = prev;
				this.next = next;
			}
		}
	}

	public static class Cmd {

		public 명령어타입 명령어;
		public int 이동칸수;

		public Cmd(String cmd) {
			if (cmd.startsWith("Z")) {
				this.명령어 = 명령어타입.되돌리기;
			} else if (cmd.startsWith("C")) {
				this.명령어 = 명령어타입.행지우기;
			} else if (cmd.startsWith("U")) {
				this.명령어 = 명령어타입.올라가기;
				this.이동칸수 = Integer.parseInt(cmd.split(" ")[1]);
			} else if (cmd.startsWith("D")) {
				this.명령어 = 명령어타입.내려가기;
				this.이동칸수 = Integer.parseInt(cmd.split(" ")[1]);
			}
		}

		public enum 명령어타입 {
			되돌리기("Z"),
			행지우기("C"),
			올라가기("U"),
			내려가기("D");

			private final String cmd;

			명령어타입(String cmd) {
				this.cmd = cmd;
			}
		}
	}


}