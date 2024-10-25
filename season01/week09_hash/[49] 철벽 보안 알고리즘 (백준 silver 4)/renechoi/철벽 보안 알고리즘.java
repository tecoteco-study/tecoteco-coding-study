import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 테스트케이스 = Integer.parseInt(br.readLine());

		while (테스트케이스-- > 0) {
			int n = Integer.parseInt(br.readLine());

			HashMap<String, Integer> 제1공개키 = 제1공개키구하기(br, n);
			HashMap<String, Integer> 제2공개키 = 제2공개키구하기(br, n);
			HashMap<Integer, Integer> 규칙 = 규칙구하기(제1공개키, 제2공개키);

			String[] 풀어야하는문장 = 암호문받기(br, n);
			System.out.println(평문구하기(풀어야하는문장, 규칙));
		}
	}

	private static HashMap<Integer, Integer> 규칙구하기(HashMap<String, Integer> 제1공개키, HashMap<String, Integer> 제2공개키) {
		HashMap<Integer, Integer> 규칙 = new HashMap<>();
		for (String 단어 : 제2공개키.keySet()) {
			규칙.put(제2공개키.get(단어), 제1공개키.get(단어));
		}
		return 규칙;
	}


	private static String[] 암호문받기(BufferedReader br, int n) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] 풀어야하는문장 = new String[n];
		for (int i = 0; i < n; i++) {
			풀어야하는문장[i] = st.nextToken();
		}
		return 풀어야하는문장;
	}

	private static HashMap<String, Integer> 제2공개키구하기(BufferedReader br, int n) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<String, Integer> key2Map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			key2Map.put(st.nextToken(), i);
		}
		return key2Map;
	}

	private static HashMap<String, Integer> 제1공개키구하기(BufferedReader br, int n) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<String, Integer> key1Map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			key1Map.put(st.nextToken(), i);
		}
		return key1Map;
	}

	private static String 평문구하기(String[] 풀어야하는문장, HashMap<Integer, Integer> 규칙) {
		String[] 평문 = new String[풀어야하는문장.length];

		for (int i = 0; i < 풀어야하는문장.length; i++) {
			평문[규칙.get(i)] = 풀어야하는문장[i];
		}

		return String.join(" ", 평문);
	}
}