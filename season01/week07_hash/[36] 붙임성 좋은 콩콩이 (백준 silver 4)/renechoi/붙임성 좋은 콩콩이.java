
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		HashSet<String> 무지개댄스추는사람 = new HashSet<>();
		무지개댄스추는사람.add("ChongChong");

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String 사람1 = st.nextToken();
			String 사람2 = st.nextToken();

			if (무지개댄스추는사람.contains(사람1) || 무지개댄스추는사람.contains(사람2)) {
				무지개댄스추는사람.add(사람1);
				무지개댄스추는사람.add(사람2);
			}
		}

		System.out.println(무지개댄스추는사람.size());
	}

}