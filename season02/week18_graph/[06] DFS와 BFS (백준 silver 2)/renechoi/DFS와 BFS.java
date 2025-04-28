
import static java.lang.Integer.*;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	private static List<Integer>[] graph;
	private static boolean[] visited;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parseInt(st.nextToken());
		int m = parseInt(st.nextToken());
		int v = parseInt(st.nextToken());

		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();

		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = parseInt(st.nextToken());
			int w = parseInt(st.nextToken());
			graph[u].add(w);
			graph[w].add(u);
		}


		for (int i = 1; i <= n; i++) {
			Collections.sort(graph[i]);
		}


		sb = new StringBuilder();
		visited = new boolean[n + 1];
		dfs(v);
		sb.append('\n');


		visited = new boolean[n + 1];
		bfs(v);


		System.out.print(sb.toString());
	}

	private static void dfs(int v) {
		visited[v] = true;
		sb.append(v).append(' ');
		for (int nxt : graph[v]) {
			if (!visited[nxt]) {
				dfs(nxt);
			}
		}
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int v = queue.poll();
			sb.append(v).append(' ');
			for (int nxt : graph[v]) {
				if (!visited[nxt]) {
					visited[nxt] = true;
					queue.add(nxt);
				}
			}
		}
	}

}