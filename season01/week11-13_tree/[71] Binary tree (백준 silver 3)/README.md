import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

	static int[] parent;
	static int[] height;
	static int root;


	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine());
		parent = new int[n + 1];
		height = new int[n + 1];

		부모중심으로노드초기화(n, bufferedReader);

		for (int i = 1; i <= n; i++) {
			height[i] = 각노드의Height계산(i);
		}


		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			stringBuilder.append(height[i]).append("\n");
		}
		System.out.print(stringBuilder);


	}

	private static int 각노드의Height계산(int i) {
		if (parent[i] == -1) {
			return 0;
		}

		// 부모의 높이가 이미 계산되었거나 부모가 루트인 경우
		if (height[parent[i]] != 0 || parent[i] == root) {
			return height[parent[i]] + 1;
		}

		return 각노드의Height계산(parent[i]) + 1;
	}

	private static void 부모중심으로노드초기화(int n, BufferedReader bufferedReader) throws IOException {
		for (int i = 1; i <= n; i++) {
			parent[i] = Integer.parseInt(bufferedReader.readLine());
			if (parent[i] == -1) {
				root = i;
			}
		}
	}
}