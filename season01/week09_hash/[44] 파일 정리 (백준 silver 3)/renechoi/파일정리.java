import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Map<String, Integer> extensionCount = new HashMap<>();

		while(N-->0) {
			String fileName = br.readLine();
			String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
			extensionCount.put(extension, extensionCount.getOrDefault(extension, 0) + 1);
		}

		Map<String, Integer> sortedMap = new TreeMap<>(extensionCount);

		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			stringBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
		}

		System.out.println(stringBuilder);
	}
}