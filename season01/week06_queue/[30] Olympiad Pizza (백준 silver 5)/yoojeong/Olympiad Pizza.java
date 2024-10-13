import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Olympiad Pizza
// https://www.acmicpc.net/problem/15235
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bufferedReader.readLine());
		String[] secondLine = bufferedReader.readLine().split(" ");
		int[] pizzas = Arrays.stream(secondLine).mapToInt(Integer::parseInt).toArray();

		System.out.println(Arrays.toString(timeToFinishPizza(N, pizzas)).replaceAll("[\\[\\],]", ""));
	}

	public static int[] timeToFinishPizza(int N, int[] pizzas) {

		Queue<People> queue = new LinkedList<>();
		int[] result = new int[N];
		int time = 0;

		// 피자 줄 초기화
		for (int i = 0; i < pizzas.length; i ++) {
			queue.add(new People(pizzas[i], i));
		}

		// 피자 배분 시작
		while (!queue.isEmpty()) {
			time++;             // 순회마다 경과시간 증가
			People people = queue.poll();
			people.pizza --;    // 피자조각 한개 소모

			// 피자를 다 먹었을 경우 result 에 시간 기록
			if (people.pizza == 0) result[people.index] = time;
				// 피자가 남아있을 경우 다시 줄서기
			else queue.add(people);
		}
		return result;
	}

	public static class People {
		int pizza;      // 필요한 피자개수
		int index;      // 인덱스

		public People (int pizza, int index) {
			this.pizza = pizza;
			this.index = index;
		}
	}
}