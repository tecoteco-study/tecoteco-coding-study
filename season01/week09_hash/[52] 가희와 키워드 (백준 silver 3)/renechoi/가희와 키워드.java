import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());

		HashMap<String, Boolean> 키워드들 = new HashMap<>();
		for (int i = 0; i < N; i++) {
			키워드들.put(br.readLine(), true);
		}

		StringBuilder stringBuilder = new StringBuilder();
		int 남은단어 = N;

		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			String[] wordsInPost = line.split(",");

			for (String word : wordsInPost) {
				if (키워드들.containsKey(word) && 키워드들.get(word)) {
					키워드들.put(word, false);
					남은단어--;
				}
			}
			stringBuilder.append(남은단어).append("\n");
		}
		System.out.println(stringBuilder);
	}
}