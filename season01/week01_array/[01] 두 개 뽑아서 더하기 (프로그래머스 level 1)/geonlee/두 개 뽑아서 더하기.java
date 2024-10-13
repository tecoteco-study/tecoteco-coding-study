import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] numbers) {

        int[] arr = new int[201];
        int len = numbers.length;

        for (int i = 0; i < len - 1; i++) {
            for(int j = i + 1; j < len; j++) {
                arr[numbers[i] + numbers[j]]++;
            }
        }

        int resLen = 0;
        for(int a: arr) {
            if(a > 0)resLen++;
        }

        int[] res = new int[resLen];
        int index = 0;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > 0)res[index++] = i;
        }

        return res;
    }
}
