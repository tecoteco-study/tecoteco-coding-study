import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public int[] solution(int[] progresses, int[] speeds) {

		ArrayDeque<작업> 큐 = new ArrayDeque<>();
		for (int i = 0; i < progresses.length; i++) {
			큐.add(new 작업(progresses[i], speeds[i]));
		}

		int next배포일 = 큐.poll().get작업일();
		List<Integer> result = new ArrayList<>();
		int count = 1;

		while (!큐.isEmpty()) {
			작업 작업 = 큐.poll();
			if (작업.get작업일() <= next배포일) {
				count++;
			} else {
				result.add(count);  // 이전까지의 배포를 저장
				count = 1;  // 새로운 배포 시작
				next배포일 = 작업.get작업일();
			}
		}
		result.add(count);

		return result.stream().mapToInt(i -> i).toArray();
	}

	public static class 작업 {
		private int progress;
		private int speed;

		public 작업(int progress, int speed) {
			this.progress = progress;
			this.speed = speed;
		}

		public int get작업일() {
			return (int)Math.ceil((100.0 - progress) / speed);
		}

	}
}