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

        Stack<Integer> number = new Stack<>();

        for (char c : input.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                int a = number.pop();
                int b = number.pop();
                int tmp = 0;

                switch (c) {
                    case '+':
                        tmp = (a + b);
                        break;
                    case '-':
                        tmp = (b - a);
                        break;
                    case '*':
                        tmp = (a * b);
                        break;
                    case '/':
                        tmp = (b / a);
                        break;
                }
                //결과를 다시 스택에 넣음
                number.push(tmp);
            } else { //숫자가 나온 경우 stack 에 저장
                number.push(c - '0');
            }
        }
        System.out.println(number.pop());
    }
}