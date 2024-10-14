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

		HashSet<String> 단어집합 = new HashSet<>();

		StringBuilder stringBuilder = new StringBuilder();
		while(N-->0){
			String 단어 = br.readLine();
			String 뒤집힌단어 = new StringBuilder(단어).reverse().toString();

			if (단어.equals(뒤집힌단어)|| 단어집합.contains(뒤집힌단어)) {
				stringBuilder.append(단어.length()).append(" ").append(단어.charAt(단어.length() / 2));
				break;
			}

			단어집합.add(단어);
		}

		System.out.println(stringBuilder);
	}

}