
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 자료구조개수 = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] 자료구조종류 = new int[자료구조개수]; // 0: 큐, 1: 스택
		for (int i = 0; i < 자료구조개수; i++) {
			자료구조종류[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int[] 자료구조초기원소 = new int[자료구조개수];
		for (int i = 0; i < 자료구조개수; i++) {
			자료구조초기원소[i] = Integer.parseInt(st.nextToken());
		}

		Deque<Integer> 큐초기원소목록 = new ArrayDeque<>();
		for (int i = 자료구조개수 - 1; i >= 0; i--) {
			if (자료구조종류[i] == 0) { // 큐인 경우
				큐초기원소목록.addLast(자료구조초기원소[i]);
			}
		}

		int 삽입할수열길이 = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] 삽입할수열 = new int[삽입할수열길이];
		for (int i = 0; i < 삽입할수열길이; i++) {
			삽입할수열[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder 결과 = new StringBuilder();
		int idx = 0;
		for (int i = 0; i < 삽입할수열길이; i++) {
			if (!큐초기원소목록.isEmpty()) {
				결과.append(큐초기원소목록.pollFirst()).append(" ");
			} else {
				결과.append(삽입할수열[idx++]).append(" ");
			}
		}

		System.out.println(결과.toString().trim());
	}
}