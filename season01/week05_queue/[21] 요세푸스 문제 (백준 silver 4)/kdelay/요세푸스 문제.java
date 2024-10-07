import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //초기 값 세팅
        Deque<Integer> deque = new LinkedList<>();
        for (int i = N; i >= 1; i--) deque.add(i);

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while (deque.size() != 1) {
            //1 ~ K-1 번 만큼 pop 후, push
            for (int i = 1; i <= K - 1; i++) {
                deque.addFirst(deque.pollLast());
            }
            sb.append(deque.pollLast()).append(", ");
        }
        //1개 남은 경우 저장하고 종료
        sb.append(deque.poll()).append(">");
        System.out.println(sb);
    }
}