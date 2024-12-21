import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine());
		int[] degree = new int[n + 1];

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			degree[a]++;
			degree[b]++;
		}

		System.out.println(get최대차수(degree, n) + 1);
	}

	private static int get최대차수(int[] degree, int n) {
		int maxDegree = 0;
		for (int i = 1; i <= n; i++) {
			maxDegree = Math.max(maxDegree, degree[i]);
		}
		return maxDegree;
	}
}