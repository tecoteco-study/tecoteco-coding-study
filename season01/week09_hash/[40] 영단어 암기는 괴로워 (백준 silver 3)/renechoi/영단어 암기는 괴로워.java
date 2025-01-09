import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());

		HashMap<String, Integer> 단어카운트맵 = new HashMap<>();
		while (N-- > 0) {
			String 단어 = br.readLine();
			if (단어.length() < M) {
				continue;
			}
			단어카운트맵.put(단어, 단어카운트맵.getOrDefault(단어, 0) + 1);
		}

		System.out.print(단어순서대로출력(단어카운트맵));

	}

	private static String 단어순서대로출력(HashMap<String, Integer> 단어카운트맵) {
		ArrayList<단어> 단어들 = new ArrayList<>();
		for (Map.Entry<String, Integer> stringIntegerEntry : 단어카운트맵.entrySet()) {
			단어들.add(new 단어(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
		}

		Collections.sort(단어들);

		StringBuilder stringBuilder = new StringBuilder();
		for (단어 단어객체 : 단어들) {
			stringBuilder.append(단어객체.get단어값()).append("\n");
		}

		return stringBuilder.toString();
	}

	public static class 단어 implements Comparable<단어> {
		private String 단어값;
		private int 등장횟수;

		public 단어(String 단어값, int 등장횟수) {
			this.단어값 = 단어값;
			this.등장횟수 = 등장횟수;
		}

		@Override
		public int compareTo(단어 o) {
			// 1. 등장 횟수 비교 (내림차순)
			if (this.등장횟수 != o.등장횟수) {
				return o.등장횟수 - this.등장횟수;
			}
			// 2. 단어 길이 비교 (내림차순)
			if (this.단어값.length() != o.단어값.length()) {
				return o.단어값.length() - this.단어값.length();
			}
			// 3. 알파벳 사전 순 비교 (오름차순)
			return this.단어값.compareTo(o.단어값);
		}

		public String get단어값() {
			return 단어값;
		}
	}


}

