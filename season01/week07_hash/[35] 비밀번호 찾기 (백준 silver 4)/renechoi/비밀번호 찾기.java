
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int 사이트수 = Integer.parseInt(st.nextToken());
		int 조회수 = Integer.parseInt(st.nextToken());

		HashMap<String, String> 비밀번호목록 = new HashMap<>();

		for (int i = 0; i < 사이트수; i++) {
			st = new StringTokenizer(br.readLine());
			String 사이트 = st.nextToken();
			String 비밀번호 = st.nextToken();
			비밀번호목록.put(사이트, 비밀번호);
		}

		StringBuilder 결과 = new StringBuilder();
		for (int i = 0; i < 조회수; i++) {
			결과.append(비밀번호목록.get( br.readLine())).append("\n");
		}
		System.out.print(결과);
	}
}