import java.util.*;

class Solution {

	public int[] solution(int N, int[] stages) {
		int[] 도전한사람수배열 = new int[N + 1];  // 1 베이스
		int[] 실패한사람수배열 = new int[N + 1];
		Arrays.sort(stages);

		for (int stage : stages) {
			if (stage <= N) {  // N+1은 마지막까지 클리어한 사람을 의미함 -> 실패율 계산에서 제외
				실패한사람수배열[stage]++;
			}
		}

		int totalPlayers = stages.length;
		for (int i = 1; i <= N; i++) {
			도전한사람수배열[i] = totalPlayers;
			totalPlayers -= 실패한사람수배열[i];
		}

		// 실패율 계산
		List<StageFailRate> 실패율리스트 = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			실패율리스트.add(new StageFailRate(i, 도전한사람수배열[i], 실패한사람수배열[i]));
		}

		Collections.sort(실패율리스트);

		return createAnswer(N, 실패율리스트);
	}

	private  int[] createAnswer(int N, List<StageFailRate> 실패율리스트) {
		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = 실패율리스트.get(i).stage;
		}

		return answer;
	}

	static class StageFailRate implements Comparable<StageFailRate> {
		int stage;
		double failRate;

		StageFailRate(int stage, int 도전한사람수, int 실패한사람수) {
			this.stage = stage;
			if (도전한사람수 == 0) {
				this.failRate = 0.0;
			} else {
				this.failRate = getFailRate(도전한사람수, 실패한사람수);
			}
		}

		private double getFailRate(int 도전한사람수, double 실패한사람수) {
			return 실패한사람수 / 도전한사람수;
		}

		// 실패율이 같은 경우 오름 차순
		// 기본 내림차순
		@Override
		public int compareTo(StageFailRate other) {
			if (this.failRate == other.failRate) {
				return Integer.compare(this.stage, other.stage);
			}
			return Double.compare(other.failRate, this.failRate);
		}
	}
}