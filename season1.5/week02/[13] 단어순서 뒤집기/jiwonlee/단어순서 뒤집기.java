import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] array = new String[N];
        
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String[] words = str.split(" ");
            
            Stack<String> stack = new Stack<>();
            for (String word: words) {
                stack.push(word);
            }
            
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
                if (!stack.isEmpty()) sb.append(" ");
            }
            array[i] = sb.toString();
        }
        
        for (int i = 0; i < N; i++) {
            System.out.println("Case #" + (i + 1) + ": " + array[i]);
        }
    }
}