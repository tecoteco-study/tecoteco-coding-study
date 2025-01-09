import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(stringTokenizer.nextToken());
		int L = Integer.parseInt(stringTokenizer.nextToken());

		LinkedHashSet<String> waitingList = new LinkedHashSet<>();

		for (int i = 0; i < L; i++) {
			String studentId = br.readLine();

			if (waitingList.contains(studentId)) { // O(1) (전부 해시 충돌시 O(n)
				waitingList.remove(studentId); // O(1)
			}
			waitingList.add(studentId); // O(1)
		}

		int count = 0;
		StringBuilder stringBuilder = new StringBuilder();
		for (String student : waitingList) {
			stringBuilder.append(student).append("\n");
			count++;
			if (count == K) {
				break;
			}
		}
		System.out.println(stringBuilder);
	}
}