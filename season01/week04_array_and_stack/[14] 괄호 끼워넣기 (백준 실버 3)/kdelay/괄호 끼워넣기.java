import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        String input = st.nextToken();

        Stack<Character> stack = new Stack<>();
        for (char i : input.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (stack.peek() == '(' && i == ')') stack.pop();
            else stack.push(i);
        }
        System.out.println(stack.size());
    }
}
