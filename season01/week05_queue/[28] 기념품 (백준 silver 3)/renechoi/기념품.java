package template.Boj;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 사람수 = Integer.parseInt(br.readLine());

		System.out.println(당첨자구하기(사람수));

	}

	// 원형 큐 작동 원리를 그대로 구현한 방식
	private static int 당첨자구하기(int 사람수) {

		ArrayDeque<Integer> 원형큐 = new ArrayDeque<>();

		for (int i = 1; i <= 사람수; i++) {
			원형큐.add(i);
		}

		단계 단계 = new 단계(1);
		while (원형큐.size() > 1) {

			단계.단계별횟수차감();
			if (단계.단계별횟수 == 0) {
				원형큐.pop();
				단계.단계업();
			} else {
				원형큐.add(원형큐.pop());
			}
		}

		return 원형큐.pop();
	}

	// 큐와 모듈러 연산을 이용한 방식
	private static int 당첨자구하기(int 사람수) {
		ArrayDeque<Integer> 원형큐 = new ArrayDeque<>();
		for (int i = 1; i <= 사람수; i++) {
			원형큐.add(i);
		}

		long 현재위치 = 0;
		단계 단계 = new 단계(1);

		while (원형큐.size() > 1) {
			현재위치 = (현재위치 + 단계.단계별횟수 - 1) % 원형큐.size();

			// 큐에서 해당 위치만큼 이동한 후 제거 (앞으로 빼오기)
			for (int i = 0; i < 현재위치; i++) {
				원형큐.addLast(원형큐.removeFirst());
			}

			원형큐.poll();

			현재위치 = 0; // 위치가 하나 줄어들었으므로 현재 위치는 0으로 리셋

			단계.단계업();
		}

		return 원형큐.poll();
	}

	// ArrayList와 모듈러 연산을 이용한 방식
	private static int 당첨자구하기(int 사람수) {
		ArrayList<Integer> 참가자 = new ArrayList<>();
		for (int i = 1; i <= 사람수; i++) {
			참가자.add(i);
		}

		int 현재위치 = 0;
		단계 단계 = new 단계(1);

		while (참가자.size() > 1) {
			현재위치 = (int)((현재위치 + 단계.단계별횟수 - 1) % 참가자.size());
			참가자.remove(현재위치); // 해당 위치에 있는 참가자를 바로 빼주기
			단계.단계업();
		}

		return 참가자.get(0);        // 마지막 남은 참가자의 번호 반환
	}

	// 요세푸스 메커니즘에 착안해 수학 + 재귀식을 이용한 방식
	private static int 당첨자구하기(int 사람수) {
		int 당첨자 = 0; // 초기 당첨자 위치 (0 기반 인덱싱)
		for (int n = 2; n <= 사람수; n++) {
			int t = 사람수 - n + 1; // 단계 번호를 1부터 사람수-1까지 증가
			long 단계별횟수 = (long)t * t * t;
			당첨자 = (int)((당첨자 + 단계별횟수) % n);
		}
		return 당첨자 + 1; // 1부터 시작하는 인덱스로 변환하여 반환
	}

	public static class 단계 {
		private int 단계;
		private long 단계별횟수;

		public 단계(int 단계) {
			this.단계 = 단계;
			this.단계별횟수 = get단계별횟수(단계);
		}

		private long get단계별횟수(int 단계) {
			return (long)단계 * 단계 * 단계;
		}

		public void 단계별횟수차감() {
			this.단계별횟수--;
		}

		public void 단계업() {
			this.단계++;
			this.단계별횟수 = get단계별횟수(단계);
		}
	}

	@Test
	public void testCases() {
		assertEquals(1, Main.당첨자구하기(1));
		assertEquals(2, Main.당첨자구하기(2));
		assertEquals(2, Main.당첨자구하기(3));
		assertEquals(2, Main.당첨자구하기(4));
		assertEquals(6, Main.당첨자구하기(6));
		assertEquals(8, Main.당첨자구하기(10));

	}
}