import java.util.Stack;

class Solution {
    public int solution(String s) {
	    int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            // 임시로 괄호를 담을 stack 생성
            Stack<Character> stack = new Stack<>();
            // 치환시킬 char array 생성
            char[] array = new char[s.length()];
            // 왼쪽으로 돌리기
            for (int j = 0; j < s.length(); j++) {
                array[(j + s.length() - 1) % s.length()] = s.charAt(j);
            }
            s = String.valueOf(array);
            for (char a : array) {
                if (a == '[' || a == '{' || a == '(') {
                    stack.add(a);
                } else if (a == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        stack.add(a);
                    }
                } else if (a == '}') {
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        stack.add(a);
                    }
                } else if (a == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        stack.add(a);
                    }
                }
            }
            // stack 이 비어있으면 answer++
            if (stack.isEmpty()) {
                answer++;
            }
        }
        return answer;
    }
}