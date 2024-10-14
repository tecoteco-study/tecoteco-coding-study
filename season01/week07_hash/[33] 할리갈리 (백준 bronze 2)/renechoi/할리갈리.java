import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		HashMap<String , Integer> 할리갈리맵 = new HashMap<>();
		while(N-->0){
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			String 과일 = stringTokenizer.nextToken();
			int 개수 = Integer.parseInt(stringTokenizer.nextToken());

			할리갈리맵.put(과일, 할리갈리맵.getOrDefault(과일, 0) + 개수);
		}

		System.out.println(정답구하기(할리갈리맵));
	}

	private static String  정답구하기(HashMap<String, Integer> 할리갈리맵) {
		for (Integer value : 할리갈리맵.values()) {
			if(value==5){
				return "YES";
			}
		}
		return "NO";
	}


}