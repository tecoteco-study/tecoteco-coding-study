import java.util.HashMap;
import java.util.Arrays;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        // 1. 이름 - 인덱스 map 생성
        HashMap<String, Integer> friendsMap = new HashMap<>();
        for (int i = 0;i < n; i++) {
            friendsMap.put(friends[i], i);
        }
        // 2. 주고 받은 선물 개수 matrix 생성
        int[][] giveTakes = new int[n][n];
        // 3. 사람 별 준 선물 받은 통계 표 생성
        int[][] giftStats = new int[n][2];

        for (String gift : gifts) {
            String[] giverTakers = gift.split(" ");
            int giver = friendsMap.get(giverTakers[0]);
            int taker = friendsMap.get(giverTakers[1]);
            giveTakes[giver][taker] += 1;
            giftStats[giver][0] += 1;
            giftStats[taker][1] += 1;
        }

        // 4. 결과 배열 생성
        int[] result = new int[n];

        // matrix 바탕으로 결과 도출
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                // 선물을 더 많이 준 친구 찾기
                if (giveTakes[i][j] > giveTakes[j][i]) {
                    result[i] += 1;
                } else if (giveTakes[i][j] == giveTakes[j][i]) {
                    // 지수 높은 친구 찾기
                    int iStat = giftStats[i][0] - giftStats[i][1];
                    int jStat = giftStats[j][0] - giftStats[j][1];
                    if (iStat > jStat) {
                        result[i] += 1;
                    }
                }
            }
        }

        return Arrays.stream(result).max().getAsInt();
    }
}