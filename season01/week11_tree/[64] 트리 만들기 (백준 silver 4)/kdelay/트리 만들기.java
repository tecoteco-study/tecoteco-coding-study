import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        //비리프 노드부터 체인 형태로 연결
        for (int i = 1; i < n; i++) {
            if (i <= m) System.out.println(0 + " " + i); //루트와 리프 연결
            else System.out.println((i - m) + " " + i); //체인 연결
        }
    }
}