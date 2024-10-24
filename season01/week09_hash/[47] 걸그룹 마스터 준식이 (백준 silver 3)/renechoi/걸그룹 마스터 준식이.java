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
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());

		HashMap<String, TreeSet<String>> 팀이름과멤버 = new HashMap<>();
		HashMap<String, String> 멤버이름과팀 = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String 팀이름 = br.readLine();
			int 멤버수 = Integer.parseInt(br.readLine());

			TreeSet<String> members = new TreeSet<>();
			for (int j = 0; j < 멤버수; j++) {
				String memberName = br.readLine();
				members.add(memberName);
				멤버이름과팀.put(memberName, 팀이름);
			}
			팀이름과멤버.put(팀이름, members);
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String 문제 = br.readLine();
			int 퀴즈종류 = Integer.parseInt(br.readLine());
			if (퀴즈종류 == 0) {
				TreeSet<String> members = 팀이름과멤버.get(문제);
				for (String member : members) {
					stringBuilder.append(member).append("\n");
				}
			} else if (퀴즈종류 == 1) {
				stringBuilder.append(멤버이름과팀.get(문제)).append("\n");
			}
		}
		System.out.println(stringBuilder);
	}
}