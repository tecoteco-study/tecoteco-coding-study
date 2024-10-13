
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 사람수 = Integer.parseInt(br.readLine());

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		ArrayDeque<사람> 큐 = new ArrayDeque<>();
		for (int i = 0; i < 사람수; i++) {
			큐.add(new 사람(i, Integer.valueOf(stringTokenizer.nextToken())));
		}

		System.out.println(피자다받는시간구하기(큐, 사람수));

	}

	private static String 피자다받는시간구하기(ArrayDeque<사람> 큐, int 사람수) {
		int 시간초 = 0;
		int[] 사람별시간초배열 = new int[사람수];

		while (!큐.isEmpty()) {
			시간초++;
			사람 사람 = 큐.pop();
			사람.피자배급();
			;

			if (사람.다받았다면()) {
				사람별시간초배열[사람.처음인덱스] = 시간초;
			} else {
				큐.add(사람);
			}

		}

		StringBuilder stringBuilder = new StringBuilder();

		for (int i : 사람별시간초배열) {
			stringBuilder.append(i).append(" ");
		}
		return stringBuilder.toString();

	}

	public static class 사람 {
		private int 처음인덱스;
		private int 받고싶은피자개수;

		public 사람(int 처음인덱스, int 받고싶은피자개수) {
			this.처음인덱스 = 처음인덱스;
			this.받고싶은피자개수 = 받고싶은피자개수;
		}

		public boolean 다받았다면() {
			return 받고싶은피자개수 == 0;
		}

		public void 피자배급() {
			this.받고싶은피자개수--;
		}
	}

}