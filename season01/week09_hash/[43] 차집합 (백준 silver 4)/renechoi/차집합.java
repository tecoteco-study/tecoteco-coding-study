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
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int A개수 = Integer.parseInt(stringTokenizer.nextToken());
		int B개수 = Integer.parseInt(stringTokenizer.nextToken());

		HashSet<Integer> A집합 = new HashSet<>();
		stringTokenizer = new StringTokenizer(br.readLine());
		while (A개수-- > 0) {
			A집합.add(Integer.valueOf(stringTokenizer.nextToken()));
		}

		stringTokenizer = new StringTokenizer(br.readLine());
		while (B개수--> 0) {
			Integer 원소 = Integer.valueOf(stringTokenizer.nextToken());
			A집합.remove(원소);
		}

		ArrayList<Integer> 남은A원소 = new ArrayList<>(A집합);
		Collections.sort(남은A원소);

		if (남은A원소.isEmpty()) {
			System.out.println(0);
		} else {
			System.out.println(남은A원소.size());
			StringBuilder stringBuilder = new StringBuilder();
			for (Integer i : 남은A원소) {
				stringBuilder.append(i).append(" ");
			}
			System.out.println(stringBuilder);
		}
	}
}