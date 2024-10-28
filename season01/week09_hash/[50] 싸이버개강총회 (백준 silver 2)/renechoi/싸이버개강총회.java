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

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

		int 시작시간 = parseTimeAsNumber(stringTokenizer.nextToken());
		int 종료시간 = parseTimeAsNumber(stringTokenizer.nextToken());
		int 스트리밍끝시간 = parseTimeAsNumber(stringTokenizer.nextToken());


		HashMap<String, Boolean> 출석맵 = new HashMap<>();

		while (true){
			String 읽은값 = br.readLine();
			if(읽은값 == null || 읽은값.isBlank()){
				break;
			}

			stringTokenizer = new StringTokenizer(읽은값);
			int 입장시간 = parseTimeAsNumber(stringTokenizer.nextToken());
			String id = stringTokenizer.nextToken();

			if (유효한입장이라면(입장시간, 시작시간)){
				출석맵.put(id, false);
				continue;
			}

			if(유효한퇴장이라면(입장시간, 종료시간, 스트리밍끝시간)){
				if (!출석맵.containsKey(id)){
					continue;
				}

				출석맵.put(id, true);
			}
		}

		System.out.println(출석맵.values().stream().filter(value -> value).count());
	}

	private static boolean 유효한퇴장이라면(int 입장시간, int 종료시간, int 스트리밍끝시간) {
		return 종료시간 <= 입장시간 && 입장시간 <=스트리밍끝시간;
	}

	private static boolean 유효한입장이라면(int 입장시간, int 시작시간) {
		return 입장시간 <= 시작시간;

	}

	private static int parseTimeAsNumber(String s) {
		return Integer.parseInt(s.replace(":",""));
	}

}

