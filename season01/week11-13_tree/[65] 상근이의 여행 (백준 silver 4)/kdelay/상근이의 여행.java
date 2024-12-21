import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); //테스트 케이스 수

        StringBuilder sb = new StringBuilder(); //결과 한 번에 모아서 출력
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]); //국가 수
            int M = Integer.parseInt(input[1]); //비행기 수

            //비행기 정보를 읽을 필요가 없음
            for (int j = 0; j < M; j++) br.readLine();

            //주어지는 비행 스케줄은 항상 연결 리스트이므로, 최대 개수는 N-1 개이다.
            sb.append(N - 1).append("\n");
        }
        System.out.println(sb);
    }
}