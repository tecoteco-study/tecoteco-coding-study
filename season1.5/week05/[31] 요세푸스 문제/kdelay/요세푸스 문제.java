import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 초기화
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) queue.add(i);

        // K번째 제거
        StringBuilder sb = new StringBuilder("<");
        while (queue.size() != 1) {
            for (int i = 1; i < K; i++) queue.add(queue.poll());
            sb.append(queue.poll()).append(", ");
        }
        // 마지막 하나 남은 데이터 추가 후 출력
        sb.append(queue.poll()).append(">");
        System.out.println(sb);
    }
}