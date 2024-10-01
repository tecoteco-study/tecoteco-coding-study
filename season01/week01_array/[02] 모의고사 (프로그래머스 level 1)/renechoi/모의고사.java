import java.util.*;

class Solution {
	public int[] solution(int[] answers) {

		int[] student1 = new int[]{ 1, 2, 3, 4, 5};
		int[] student2 = new int[]{ 2, 1, 2, 3, 2, 4, 2, 5};
		int[] student3 = new int[]{ 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

		int 학생1정답 = 0;
		int 학생2정답 = 0;
		int 학생3정답 = 0;

		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == student1[i % student1.length]) {
				학생1정답++;
			}
			if (answers[i] == student2[i % student2.length]) {
				학생2정답++;
			}
			if (answers[i] == student3[i % student3.length]) {
				학생3정답++;
			}
		}

		int maxScore = Math.max(학생1정답, Math.max(학생2정답, 학생3정답));

		List<Integer> 정답학생들 = new ArrayList<>();
		if (학생1정답 == maxScore) {
			정답학생들.add(1);
		}
		if (학생2정답 == maxScore) {
			정답학생들.add(2);
		}
		if (학생3정답 == maxScore) {
			정답학생들.add(3);
		}

		return 정답학생들.stream().sorted().mapToInt(i -> i).toArray();
	}
}