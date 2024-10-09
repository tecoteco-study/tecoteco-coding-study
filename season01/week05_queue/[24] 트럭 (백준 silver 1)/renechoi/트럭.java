package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int 주어진트럭의수 = Integer.parseInt(stringTokenizer.nextToken());
		int 다리의길이 = Integer.parseInt(stringTokenizer.nextToken());
		int 최대하중 = Integer.parseInt(stringTokenizer.nextToken());

		다리 다리 = new 다리(다리의길이, 최대하중);
		ArrayList<트럭> 주어진트럭들 = new ArrayList<>();
		stringTokenizer = new StringTokenizer(br.readLine());
		while (주어진트럭의수-- > 0) {
			주어진트럭들.add(new 트럭(Integer.valueOf(stringTokenizer.nextToken())));
		}

		System.out.println(트럭이지나가는최단시간구하기(주어진트럭들, 다리));

	}

	private static int 트럭이지나가는최단시간구하기(ArrayList<트럭> 주어진트럭들, 다리 다리) {

		int 최단시간 = 0;
		int 트럭인덱스 = 0;

		while(트럭이전부지나갈때까지(주어진트럭들, 다리, 트럭인덱스)){
			트럭인덱스 =  다리.트럭추가및업데이트(주어진트럭들, 트럭인덱스);
			최단시간++;
		}

		return 최단시간;

	}

	private static boolean 트럭이전부지나갈때까지(ArrayList<트럭> 주어진트럭들, 다리 다리, int 트럭인덱스) {
		return 다리.현재다리가에트럭이있다면() ||  트럭인덱스 < 주어진트럭들.size();
	}

	public static class 트럭 {
		private int 무게;
		private int 지나가는시간;

		public 트럭(int 무게) {
			this.무게 = 무게;
		}

		public void 시간업데이트() {
			this.지나가는시간++;
		}

		/**
		 * 로직상 마지막 지점에 와 있어도 다음 텀에 지날 것이므로 지나는 것으로 판단
		 * 다리의 길이와 시간은 1:1임
		 */
		public boolean 전부다지남(int 다리의길이) {
			return this.지나가는시간 >= 다리의길이;
		}
	}

	public static class 다리 {
		private final int 다리의길이;
		private final int 최대하중;
		private ArrayDeque<트럭> 현재트럭들 ;

		public 다리(int 다리의길이, int 최대하중) {
			this.다리의길이 = 다리의길이;
			this.최대하중 = 최대하중;
			this.현재트럭들 = new ArrayDeque<>();
		}

		public int 트럭추가및업데이트(ArrayList<트럭> 주어진트럭들, int 트럭인덱스) {

			if (앞트럭이다지났다면()) {
				현재트럭들.poll();
			}

			return  가능한트럭을지나가게다리에올리기(주어진트럭들, 트럭인덱스);
		}

		private int 가능한트럭을지나가게다리에올리기(ArrayList<트럭> 주어진트럭들, int 트럭인덱스) {
			if (트럭인덱스 < 주어진트럭들.size()) {
				트럭 다음트럭 = 주어진트럭들.get(트럭인덱스);
				if (isAvailable(다음트럭)) {
					현재트럭들.add(다음트럭);
					트럭인덱스++;
				}
			}
			다리의트럭들시간업데이트();
			return 트럭인덱스;
		}

		private boolean 앞트럭이다지났다면() {
			return 현재다리가에트럭이있다면() && 현재트럭들.peek().전부다지남(다리의길이);
		}

		private void 다리의트럭들시간업데이트() {
			현재트럭들.forEach(트럭::시간업데이트);
		}

		private boolean isAvailable(트럭 트럭) {
			if (현재다리가비어있다면()) {
				return true;
			}

			if (현재트럭들.peek().전부다지남(다리의길이)) {
				현재트럭들.poll();
			}

			return 현재트럭들.stream().mapToInt(item -> item.무게).sum() + 트럭.무게 <= 최대하중;
		}

		private boolean 현재다리가비어있다면() {
			return 현재트럭들.isEmpty();
		}

		private boolean 현재다리가에트럭이있다면() {
			return !현재트럭들.isEmpty();
		}

	}

	@Test
	public void testEdgeCases() throws IOException {
		ArrayList<트럭> 트럭들1 = new ArrayList<>();
		트럭들1.add(new 트럭(7));
		트럭들1.add(new 트럭(4));
		트럭들1.add(new 트럭(5));
		트럭들1.add(new 트럭(6));
		다리 다리1 = new 다리(2, 10);
		Assertions.assertEquals(8, 트럭이지나가는최단시간구하기(트럭들1, 다리1));

		ArrayList<트럭> 트럭들2 = new ArrayList<>();
		트럭들2.add(new 트럭(10));
		다리 다리2 = new 다리(100, 100);
		Assertions.assertEquals(101, 트럭이지나가는최단시간구하기(트럭들2, 다리2));

		ArrayList<트럭> 트럭들3 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			트럭들3.add(new 트럭(10));
		}
		다리 다리3 = new 다리(100, 100);
		Assertions.assertEquals(110, 트럭이지나가는최단시간구하기(트럭들3, 다리3));

		ArrayList<트럭> 트럭들4 = new ArrayList<>();
		트럭들4.add(new 트럭(7));
		트럭들4.add(new 트럭(5));
		트럭들4.add(new 트럭(3));
		트럭들4.add(new 트럭(6));
		트럭들4.add(new 트럭(4));
		다리 다리4 = new 다리(3, 15);
		Assertions.assertEquals(8, 트럭이지나가는최단시간구하기(트럭들4, 다리4));
	}

}
