import static java.lang.Integer.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

	private static StringTokenizer receiveInput(BufferedReader bufferedReader) throws IOException {
		return new StringTokenizer(bufferedReader.readLine());
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer stringTokenizer = receiveInput(bufferedReader);
		int N = parseInt(stringTokenizer.nextToken());
		int K = parseInt(stringTokenizer.nextToken());

		List<Integer> circle = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			circle.add(i);
		}

		StringBuilder result = new StringBuilder();
		result.append("<");

		int index = 0;
		while (!circle.isEmpty()) {
			index = (index + K - 1) % circle.size();
			result.append(circle.remove(index));
			if (!circle.isEmpty()) {
				result.append(", ");
			}
		}
		result.append(">");

		System.out.println(result);
	}
}