import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bufferedReader.readLine());

		StringBuilder result = new StringBuilder();

		while (T-- > 0) {
			String[] input = bufferedReader.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);

			int lca = findLCA(a, b);

			result.append(10 * lca).append("\n");
		}

		System.out.print(result);
	}

	private static int findLCA(int a, int b) {
		while (a != b) {
			if (a > b) {
				a /= 2;
			} else {
				b /= 2;
			}
		}
		return a;
	}
}