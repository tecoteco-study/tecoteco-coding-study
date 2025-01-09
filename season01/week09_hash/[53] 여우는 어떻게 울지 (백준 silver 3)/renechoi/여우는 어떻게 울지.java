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

		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < T; i++) {

			String[] 녹음된소리 = br.readLine().split(" ");
			HashSet<String> 소리셋 = new HashSet<>();

			while (true) {
				String line = br.readLine();
				if (line.equals("what does the fox say?")) {
					break;
				}

				StringTokenizer st = new StringTokenizer(line);
				st.nextToken();
				st.nextToken();
				String 소리 = st.nextToken();
				소리셋.add(소리);
			}

			StringBuilder 여우소리 = new StringBuilder();
			for (String sound : 녹음된소리) {
				if (!소리셋.contains(sound)) {
					여우소리.append(sound).append(" ");
				}
			}

			result.append(여우소리).append("\n");
		}

		System.out.print(result);
	}
}