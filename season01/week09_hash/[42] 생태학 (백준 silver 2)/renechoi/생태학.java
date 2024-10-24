
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<String> 입력 = new ArrayList<>();
		while (true){
			String 입력값 = br.readLine();

			if(입력값 == null || 입력값.isBlank()){
				break;
			}
			입력.add(입력값);
		}

		System.out.print(나무퍼센트구하기(입력));

	}

	private static String 나무퍼센트구하기(ArrayList<String> 입력) {
		HashMap<String, Integer> 나무맵 = new HashMap<>();
		int 총나무수 = 0;

		for (String 나무 : 입력) {
			나무맵.put(나무, 나무맵.getOrDefault(나무, 0) + 1);
			총나무수++;
		}

		ArrayList<String> 나무이름들 = new ArrayList<>(나무맵.keySet());
		Collections.sort(나무이름들);

		StringBuilder 결과 = new StringBuilder();

		for (String 나무이름 : 나무이름들) {
			int 나무등장횟수 = 나무맵.get(나무이름);
			double 비율 = (나무등장횟수 / (double) 총나무수) * 100;
			결과.append(나무이름).append(" ").append(String.format("%.4f", 비율)).append("\n");
		}

		return 결과.toString();
	}

	public static class 나무 implements Comparable<나무> {
		private String 나무이름;
		private int 나무등장횟수;

		public 나무(String 나무이름, int 나무등장횟수) {
			this.나무이름 = 나무이름;
			this.나무등장횟수 = 나무등장횟수;
		}

		@Override
		public int compareTo(나무 o) {
			if (this.나무등장횟수 == o.나무등장횟수){
				return 나무등장횟수 - o.나무등장횟수;
			}

			return this.나무이름.compareTo(o.나무이름);
		}
	}
}