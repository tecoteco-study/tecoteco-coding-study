
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		ArrayList<String> 채팅기록 = new ArrayList<>();
		while(n-->0){
			채팅기록.add(br.readLine());
		}

		System.out.print(곰곰이모티콘구하기(채팅기록));

	}

	private static int 곰곰이모티콘구하기(ArrayList<String> 채팅기록) {

		HashSet<String>  채팅아이디들 = new HashSet<>();
		int 현재까지카운트 = 0;
		for (String 채팅 : 채팅기록) {
			if("ENTER".equals(채팅)){
				현재까지카운트 += 채팅아이디들.size();
				채팅아이디들.clear();
				continue;
			}

			채팅아이디들.add(채팅);
		}

		현재까지카운트 += 채팅아이디들.size();

		return 현재까지카운트;
	}
}