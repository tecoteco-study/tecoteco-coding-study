import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		HashMap<String, Integer> 참가자들 = new HashMap<>();
		for (int i =0;i<N;i++){
			String 참가자 = br.readLine();
			참가자들.put(참가자, 참가자들.getOrDefault(참가자, 0)+1);
		}

		for(int i =0; i<N-1;i++){
			String 완주자 = br.readLine();
			참가자들.put(완주자, 참가자들.get(완주자)-1);
		}

		for (Map.Entry<String, Integer> stringIntegerEntry : 참가자들.entrySet()) {
			if(stringIntegerEntry.getValue() != 0){
				System.out.println(stringIntegerEntry.getKey());
				break;
			}
		}
	}
}