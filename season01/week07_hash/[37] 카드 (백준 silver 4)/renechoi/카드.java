import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		HashMap<Long, Long> 카드횟수 = new HashMap<>();
		// O(n)
		while(N-->0){
			long 숫자 = Long.parseLong(br.readLine());
			카드횟수.put(숫자, 카드횟수.getOrDefault(숫자, 0L) + 1);
		}

		long 최빈횟수 =0;
		long 최빈값 =0;

		// O(n)
		for (Long 카드 : 카드횟수.keySet()) {
			Long 횟수 = 카드횟수.get(카드);

			if (횟수 > 최빈횟수 || ( 횟수 == 최빈횟수 && 카드 < 최빈값)){
				최빈횟수 = 횟수;
				최빈값 = 카드;
			}
		}

		System.out.println(최빈값);
	}

}