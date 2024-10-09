
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 카드개수 = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> 큐 = new ArrayDeque<>();

		System.out.println(남은카드구하기(카드개수, 큐));
	}

	private static String 남은카드구하기(int 카드개수, ArrayDeque<Integer> 큐) {

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= 카드개수; i++) {
			큐.add(i);
		}

		while (큐.size() > 1) {
			sb.append(큐.poll()).append(" ");
			큐.add(큐.poll());
		}

		return sb.append(큐.poll()).toString();
	}


}