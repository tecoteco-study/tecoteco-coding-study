import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int 아는노래의개수 = Integer.parseInt(stringTokenizer.nextToken());
		int 맞힐노래의개수 = Integer.parseInt(stringTokenizer.nextToken());

		String[] 아는노래들 = new String[아는노래의개수];
		for (int i = 0; i < 아는노래의개수; i++) {
			아는노래들[i] = br.readLine();
		}

		String[] 맞혀야하는노래들 = new String[맞힐노래의개수];
		for (int i = 0; i < 맞힐노래의개수; i++) {
			맞혀야하는노래들[i] = br.readLine();
		}

		System.out.println(정답맞히기(아는노래들, 맞혀야하는노래들));

	}

	private static String 정답맞히기(String[] 아는노래들, String[] 맞혀야하는노래들) {

		HashMap<String, String> 음과노래제목 = new HashMap<>();
		for (String 노래정보 : 아는노래들) {
			StringTokenizer stringTokenizer = new StringTokenizer(노래정보);
			int 노래의길이 = Integer.parseInt(stringTokenizer.nextToken());
			String 노래의제목 = stringTokenizer.nextToken();
			StringBuilder stringBuilder = new StringBuilder();

			// 첫 음 3개
			for (int i = 0; i < 3; i++) {
				stringBuilder.append(stringTokenizer.nextToken());
			}

			// 키가 있으면 "?"로, 없으면 노래 제목
			음과노래제목.compute(stringBuilder.toString(), (key, value) -> value == null ? 노래의제목 : "?");
		}

		StringBuilder answer = new StringBuilder();
		for (String 첫소절 : 맞혀야하는노래들) {
			answer.append(음과노래제목.getOrDefault(첫소절.replace(" ", ""), "!")).append("\n");
		}

		return answer.toString();
	}


}

