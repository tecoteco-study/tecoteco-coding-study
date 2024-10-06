import java.util.*;

class Solution {
    boolean solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (char b : s.toCharArray()) {
            if (b == '(') {
                stack.push(b);
            } else {
                //비어있는 경우 pop 할 수 없다.
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}