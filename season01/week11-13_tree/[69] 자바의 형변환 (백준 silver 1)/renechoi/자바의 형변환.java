import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine());

		Map<String, String> 관계쌍들 = new HashMap<>();

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String child = stringTokenizer.nextToken();
			String parent = stringTokenizer.nextToken();

			관계쌍들.put(child, parent);
		}

		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		String 첫번째대상 = stringTokenizer.nextToken();
		String 두번째대상 = stringTokenizer.nextToken();

		if (get겹치는지파악하기(관계쌍들, 첫번째대상, 두번째대상, new HashSet<>()) == 1 || get겹치는지파악하기(관계쌍들, 두번째대상, 첫번째대상, new HashSet<>()) == 1) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}

	}

	private static int get겹치는지파악하기(Map<String, String> 관계쌍들, String 첫번째대상, String 두번째대상, Set<String> 방문한노드) {
		if (첫번째대상 == null || 방문한노드.contains(첫번째대상)) {
			return 0;
		}

		방문한노드.add(첫번째대상);

		if (첫번째대상.equals(두번째대상)) {
			return 1;
		}

		return get겹치는지파악하기(관계쌍들, 관계쌍들.get(첫번째대상), 두번째대상, 방문한노드);
	}
}