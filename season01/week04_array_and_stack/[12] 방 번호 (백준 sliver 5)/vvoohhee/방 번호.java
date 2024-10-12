import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] count = new int[10];

        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }

        sixAndNine(count);

        int max = 0;
        for (int i : count) max = Math.max(max, i);
        System.out.println(max);
    }

    public static void sixAndNine(int[] count) {
        int sixAndNine = count[6] + count[9];
        count[6] = (sixAndNine + 1) / 2;
        count[9] = count[6];
    }
}