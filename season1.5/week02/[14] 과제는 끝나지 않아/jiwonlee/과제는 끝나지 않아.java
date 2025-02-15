import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.Stack;  
import java.util.StringTokenizer;  
  
public class Extra03IHateHomework {  
    public static void main(String[] args) throws IOException {  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer st;  
        int N = Integer.parseInt(bufferedReader.readLine());  
        Stack<Homework> stack = new Stack<>();  
        int answer = 0;  
        for (int i = 0; i < N; i++) {  
            st  = new StringTokenizer(bufferedReader.readLine());  
            int code = Integer.parseInt(st.nextToken());  
            if (code == 1) {  
                int score = Integer.parseInt(st.nextToken());  
                int duration = Integer.parseInt(st.nextToken());
                stack.push(new Homework(score, duration - 1));  
            } else {  
                if (!stack.isEmpty()) {  
                    Homework homework = stack.peek();  
                    homework.doHomework();  
                }  
            }  
            if (!stack.isEmpty() && 0 == stack.peek().getDuration()) {  
                answer += stack.pop().getScore();  
            }  
        }  
        System.out.println(answer);  
    }  
  
    public static class Homework {  
        private int score;  
        private int duration;  
  
        public Homework(int score, int duration) {  
            this.score = score;  
            this.duration = duration;  
        }  
        public int getScore() {  
            return this.score;  
        }  
        public int getDuration() {  
            return this.duration;  
        }  
        public void doHomework() {  
            this.duration--;  
        }    }  
}