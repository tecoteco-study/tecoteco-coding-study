import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {

        //삭제 유무 확인용
        boolean[] delete = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        //초기 값 세팅
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = (i == 0) ? -1 : i - 1;
            next[i] = (i == n-1) ? -1 : i + 1;
        }

        for(String s : cmd) {
            String[] split = s.split(" ");
            int move = 0;
            switch (split[0]) {
                case "U":
                    k = moving(k, Integer.parseInt(split[1]), prev);
                    break;
                case "D":
                    k = moving(k, Integer.parseInt(split[1]), next);
                    break;
                case "C":
                    delete[k] = true; //현재 위치 삭제
                    stack.push(k); //삭제 위치 저장

                    //이전과 다음 행을 서로 연결
                    if (prev[k] != -1) next[prev[k]] = next[k];
                    if (next[k] != -1) prev[next[k]] = prev[k];

                    //삭제 후 커서 이동
                    k = (next[k] != -1) ? next[k] : prev[k];
                    break;
                case "Z":
                    int pop = stack.pop();
                    delete[pop] = false; //복구

                    //복구된 행의 이전과 다음 연결 복구
                    if (prev[pop] != -1) next[prev[pop]] = pop;
                    if (next[pop] != -1) prev[next[pop]] = pop;
                    break;
            }
        }
        StringBuilder answer = new StringBuilder();
        for (boolean d : delete) answer.append(d ? 'X' : 'O');
        return answer.toString();
    }

    private int moving(int k, int move, int[] arr) {
        for (int i = 0; i < move; i++) {
            if (arr[k] == -1) break;
            k = arr[k];
        }
        return k;
    }
}