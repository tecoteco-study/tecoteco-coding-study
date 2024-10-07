import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        char[] input = st.nextToken().toCharArray();

        int[] card = new int[9];
        for (char c : input) {
            int num = c - '0';
            if (num == 9 || num == 6) card[6]++;
            else card[num]++;
        }
        card[6] = (card[6] + 1) / 2;
        int max = 0;
        for (int c : card) max = Math.max(max, c);
        System.out.println(max);
    }
}