import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Arrays.sort(mats); // 오름차순 정렬
        for (int m = mats.length - 1; m >= 0; m--) { // 큰 값부터 검사
            int mat = mats[m];
            for (int i = 0; i <= park.length - mat; i++) {
                for (int j = 0; j <= park[0].length - mat; j++) {
                    if (checkY(j, i, mat, park)) {
                        return mat;
                    }
                }
            }
        }
        return -1;
    }

    public boolean checkY(int x, int y, int limit, String[][] park) {
        if (y + limit > park.length) return false; // 범위 초과 방지
        for (int i = y; i < y + limit; i++) {
            if (!checkX(x, i, limit, park)) return false;
        }
        return true;
    }

    public boolean checkX(int x, int y, int limit, String[][] park) {
        if (x + limit > park[0].length) return false; // 범위 초과 방지
        for (int i = x; i < x + limit; i++) {
            if (!"-1".equals(park[y][i])) return false; // 문자열 값 확인
        }
        return true;
    }
}
