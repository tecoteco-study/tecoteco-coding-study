import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		String 게임종류 = stringTokenizer.nextToken();

		int 한판당필요사람 = 0;
		if ("Y".equals(게임종류)) {
			한판당필요사람 = 1;
		} else if ("F".equals(게임종류)) {
			한판당필요사람 = 2;
		} else if ("O".equals(게임종류)) {
			한판당필요사람 = 3;
		}

		HashSet<String> 이미플레이한사람 = new HashSet<>();
		int 현재게임에서진행중인사람수 = 0;
		int 게임판수 = 0;
		while (N-- > 0) {
			String 현재사람 = br.readLine();
			if (이미플레이한사람.contains(현재사람)) {
				continue;
			}

			현재게임에서진행중인사람수++;
			이미플레이한사람.add(현재사람);

			if (현재게임에서진행중인사람수 == 한판당필요사람) {
				게임판수++;
				현재게임에서진행중인사람수 = 0;
			}
		}
		System.out.println(게임판수);

	}
}