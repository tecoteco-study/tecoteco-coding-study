import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            //비어있는 경우, 데이터 추가
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            if (stack.peek() == c) stack.pop(); //짝인 경우 제거
            else stack.push(c); //짝이 아닌 경우 추가
        }
        return stack.isEmpty() ? 1 : 0;
    }
}