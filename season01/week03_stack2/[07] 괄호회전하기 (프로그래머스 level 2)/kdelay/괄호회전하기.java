import java.util.*;

class Solution {
    public int solution(String s) {
        //deque 초기 값 세팅
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) deque.add(c);

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            //stack이 비어있는 경우 올바른 괄호 문자열 개수 추가
            if (isCorrect(deque)) count++;
            deque.addLast(deque.removeFirst());
        }
        return count;
    }

    private boolean isCorrect(Deque<Character> deque) {

        Stack<Character> stack = new Stack<>();
        for (char d : deque) {
            //stack이 비어있는 경우, 값을 넣는다.
            if (stack.isEmpty()) stack.push(d);
                //stack에 값이 있는 경우
            else {
                //올바른 괄호 문자열인지 확인한다.
                if (stack.peek() == '(' && d == ')') stack.pop();
                else if (stack.peek() == '[' && d == ']') stack.pop();
                else if (stack.peek() == '{' && d == '}') stack.pop();
                    //일치하지 않으면 stack에 값을 넣는다.
                else stack.push(d);
            }
        }
        return stack.isEmpty();
    }
}