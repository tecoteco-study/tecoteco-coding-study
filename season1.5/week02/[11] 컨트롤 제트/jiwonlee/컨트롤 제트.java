import java.util.Stack;
class Solution {
    public int solution(String s) {
        Stack<Integer> stack = new Stack<>();
        String[] array = s.split(" ");
        for (String a : array) {
            if (a.equals("Z")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(Integer.parseInt(a));
            }
        }
        
        int answer = 0;
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }
        return answer;
    }
}