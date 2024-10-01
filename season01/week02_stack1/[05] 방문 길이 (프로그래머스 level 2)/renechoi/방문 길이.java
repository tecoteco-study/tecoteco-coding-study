import java.util.*;


class Solution {

	public int solution(String dirs) {
		Map map = new Map();

		for (char command : dirs.toCharArray()) {
			map.move(command);
		}

		return map.get처음걸어본길이();
	}

	public static class Map {
		private Position currentPosition;
		private Set<Path> 지나간길들;
		private int 처음걸어본길이;

		public Map() {
			this.currentPosition = new Position(0, 0);
			this.지나간길들 = new HashSet<>();
			this.처음걸어본길이 = 0;
		}

		public void move(char command) {
			Position newPosition = this.currentPosition.calculateNewPosition(command);

			if (!newPosition.isValid()) {
				return;  // "단, 좌표평면의 경계를 넘어가는 명령어는 무시합니다."
			}

			Path newPath = new Path(this.currentPosition, newPosition); // 이전 위치와 새로운 위치 사이의 경로

			if (isSamePath(newPath)) {
				this.지나간길들.add(newPath);
				this.처음걸어본길이++;
			}

			this.currentPosition = newPosition;
		}

		private boolean isSamePath(Path newPath) {
			return this.지나간길들.stream().noneMatch(path -> path.isSamePath(newPath));
		}

		public int get처음걸어본길이() {
			return this.처음걸어본길이;
		}
	}

	public static class Position {
		private int x;
		private int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position calculateNewPosition(char command) {
			int newX = this.x;
			int newY = this.y;

			switch (command) {
				case 'U': newY += 1; break;
				case 'D': newY -= 1; break;
				case 'L': newX -= 1; break;
				case 'R': newX += 1; break;
			}

			return new Position(newX, newY);
		}

		public boolean isValid() {
			return this.x >= -5 && this.x <= 5 && this.y >= -5 && this.y <= 5;
		}

		public boolean isSamePosition(Position other) {
			return this.x == other.x && this.y == other.y;
		}
	}

	public static class Path {
		private Position start;
		private Position end;

		public Path(Position start, Position end) {
			this.start = start;
			this.end = end;
		}

		/**
		 * 시작 혹은 끝 위치가 같거나, 시작과 끝 위치가 뒤바뀌어도 같은 객체로 취급
		 */
		public boolean isSamePath(Path other) {
			return (this.start.isSamePosition(other.start) && this.end.isSamePosition(other.end)) ||
				(this.start.isSamePosition(other.end) && this.end.isSamePosition(other.start));
		}
	}
}